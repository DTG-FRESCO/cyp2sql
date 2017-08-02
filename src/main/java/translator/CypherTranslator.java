package translator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import intermediate_rep.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class for translating the Cypher input to its internal representation.
 * The representation includes extracting the nodes and relationships, finding
 * out what needs to be returned, seeing if they have any important constraints
 * etc. A DecodedQuery object is returned containing all the relevant information
 * for the SQL generation module.
 */
class CypherTranslator {
    private static final JsonParser parser = new JsonParser();

    static DecodedQuery generateDecodedQuery(ArrayList<String> tokenList, CypherWalker cypherQ) throws Exception {
        // find positions of the tokens in the query (-1 means not found)
        int posOfMatch = tokenList.indexOf("match");
        int posOfWhere = tokenList.indexOf("where");
        int posOfReturn = tokenList.indexOf("return");

        // if no RETURN keyword is present, then the Cypher input must contain a CREATE OR DELETE keyword.
        if (posOfReturn == -1) {
            return generateDQCreateDelete(tokenList, cypherQ, posOfMatch, posOfWhere);
        } else {
            int posOfOrder = tokenList.indexOf("order");
            int posOfSkip = tokenList.indexOf("skip");
            int posOfLimit = tokenList.indexOf("limit");

            // MATCH and RETURN always present
            List<String> matchClause;
            List<String> returnClause;

            // ORDER BY may or may not be present, hence must be initialised to NULL.
            List<String> orderClause = null;

            if (cypherQ.doesCluaseHaveWhere())
                matchClause = tokenList.subList(posOfMatch + 1, posOfWhere);
            else
                matchClause = tokenList.subList(posOfMatch + 1, posOfReturn);

            if (posOfOrder == -1) {
                if (posOfSkip == -1 && posOfLimit == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 +
                            ((cypherQ.hasDistinct()) ? 1 : 0), tokenList.size());
                } else if (posOfLimit == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypherQ.hasDistinct()) ? 1 : 0), posOfSkip);
                } else if (posOfSkip == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypherQ.hasDistinct()) ? 1 : 0), posOfLimit);
                } else {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypherQ.hasDistinct()) ? 1 : 0), posOfSkip);
                }
            } else {
                returnClause = tokenList.subList(posOfReturn + 1 + ((cypherQ.hasDistinct()) ? 1 : 0), posOfOrder);

                if (posOfSkip == -1 && posOfLimit == -1) {
                    orderClause = tokenList.subList(posOfOrder + 2, tokenList.size());
                } else if (posOfLimit == -1) {
                    orderClause = tokenList.subList(posOfOrder + 2, posOfSkip);
                } else if (posOfSkip == -1) {
                    orderClause = tokenList.subList(posOfOrder + 2, posOfLimit);
                } else {
                    orderClause = tokenList.subList(posOfOrder + 2, posOfSkip);
                }
            }

            MatchClause matchC = matchDecode(matchClause);
            ReturnClause returnC = returnDecode(returnClause, matchC, cypherQ);

            // if ORDER BY is present in the query
            OrderClause orderC = null;
            if (orderClause != null) {
                orderC = orderDecode(orderClause);
            }

            int skipAmount = (posOfSkip != -1) ? cypherQ.getSkipAmount() : -1;
            int limitAmount = (posOfLimit != -1) ? cypherQ.getLimitAmount() : -1;

            WhereClause whereC = null;
            if (cypherQ.doesCluaseHaveWhere()) {
                whereC = whereDecode(matchC, cypherQ);
            }

            return new DecodedQuery(matchC, returnC, orderC, whereC, skipAmount, limitAmount, cypherQ);
        }
    }

    /**
     * Generate a DecodedQuery object for Cypher statements with CREATE or DELETE keywords.
     *
     * @param tokenList  List of tokens in the Cypher query.
     * @param cypherQ    CypherWalker object.
     * @param posOfMatch Index in token list where MATCH keyword is present (-1 if not).
     * @param posOfWhere Index in token list where WHERE keyword is present (-1 if not).
     * @return DecodedQuery object with all relevant information.
     * @throws Exception Something goes wrong.
     */
    private static DecodedQuery generateDQCreateDelete(ArrayList<String> tokenList, CypherWalker cypherQ,
                                                       int posOfMatch, int posOfWhere) throws Exception {
        int posOfCreate = tokenList.indexOf("create");
        int posOfDelete = tokenList.indexOf("delete");

        MatchClause createOrDelete;

        if (posOfCreate == -1) {
            // delete statement.
            List<String> deleteClause;
            deleteClause = tokenList.subList(posOfMatch + 1, posOfDelete);
            createOrDelete = matchDecode(deleteClause);
        } else {
            List<String> matchCreate;

            if (cypherQ.doesCluaseHaveWhere()) {
                matchCreate = tokenList.subList(posOfCreate + 1, posOfWhere);
                createOrDelete = matchDecode(matchCreate);
                whereDecode(createOrDelete, cypherQ);
            } else {
                matchCreate = tokenList.subList(posOfCreate + 1, tokenList.size());
                createOrDelete = matchDecode(matchCreate);
            }
        }

        return new DecodedQuery(createOrDelete, null, null, null, -1, -1, cypherQ);
    }

    /**
     * Translate MATCH part of the Cypher input.
     *
     * @param matchClause The tokens comprising the MATCH clause of Cypher.
     * @return MatchClause object with all the important information inside it.
     * @throws Exception Error in the clause.
     */
    private static MatchClause matchDecode(List<String> matchClause) throws Exception {
        MatchClause m = new MatchClause();

        // extract the nodes from the match clause
        m.setNodes(extractNodes(matchClause, m));

        // reset ID between method calls
        m.resetInternalID();

        // extract any relationships from the match clause
        m.setRels(extractRels(matchClause, m));
        return m;
    }

    /**
     * Extract nodes from the MATCH clause.
     *
     * @param clause Tokens that formed original MATCH part of Cypher.
     * @param m      Needed for the internal ID representation.
     * @return List of internal node objects extracted.
     */
    private static ArrayList<CypNode> extractNodes(List<String> clause, MatchClause m) {
        // nodes to return at the end of the function.
        ArrayList<CypNode> nodes = new ArrayList<>();

        // keep track of current nodes if node of the same ID reappears later on in the query.
        Map<String, Integer> nodeIDS = new HashMap<>();

        JsonObject o;
        List<String> nodeString;
        List<String> propsString;

        String id;
        String labels;

        while (!clause.isEmpty()) {
            nodeString = null;
            propsString = null;
            o = null;

            // find nodes in the clause
            int lBrack = clause.indexOf("(");
            int rBrack = clause.indexOf(")");

            // check if node structure valid
            if (lBrack != -1 && rBrack != -1) {
                // extract the inner node tokens
                nodeString = clause.subList(lBrack + 1, rBrack);

                // keep rest of clause safe
                clause = clause.subList(rBrack + 1, clause.size());

                while (!nodeString.isEmpty()) {
                    // extract any properties from the nodes
                    int lCurly = nodeString.indexOf("{");
                    int rCurly = nodeString.indexOf("}");

                    if (lCurly != -1 && rCurly != -1) {
                        propsString = nodeString.subList(lCurly + 1, rCurly);
                        nodeString = nodeString.subList(0, lCurly);
                    } else {
                        break;
                    }
                }
            }

            if (nodeString != null) {
                if (propsString != null) {
                    o = getJSONProps(propsString);
                }

                String[] idAndLabels = extractIdAndLabels(nodeString);
                id = idAndLabels[0];
                labels = idAndLabels[1];

                // add the formatted node object to list of nodes
                // associated with the match clause
                int internalID = m.getInternalID();

                if (id != null && !nodeIDS.containsKey(id)) {
                    nodeIDS.put(id, internalID);
                } else if (nodeIDS.containsKey(id)) {
                    labels = nodes.get(nodeIDS.get(id) - 1).getType();
                    o = nodes.get(nodeIDS.get(id) - 1).getProps();
                }
                nodes.add(new CypNode(internalID, id, labels, o));
            }
        }
        return nodes;
    }

    /**
     * Extract the relationships from the MATCH clause.
     *
     * @param clause Token version of the clause.
     * @param m      Internal representation of the MATCH clause.
     * @return An ArrayList of CypRel objects that correspond to the relationships (if any) described by Cypher.
     * @throws Exception Error in the clause.
     */
    private static ArrayList<CypRel> extractRels(List<String> clause, MatchClause m) throws Exception {
        ArrayList<CypRel> rels = new ArrayList<>();

        /*
          types of relationships
          -- & -[]-        (id: null     type: null      props: null     direction : none)
          <-- & <-[]-      (id: null     type: null      props: null     direction : left)
          --> & -[]->      (id: null     type: null      props: null     direction : right)
          -[:a]-           (id: null     type: a         props: null     direction : none)
          -[b:a]-          (id: b        type: a         props: null     direction : none)
          <-[:a]-          (id: null     type: a         props: null     direction : left)
          <-[b:a]-         (id: b        type: a         props: null     direction : left)
          -[:a]->          (id: null     type: a         props: null     direction : right)
          -[b:a]->         (id: b        type: a         props: null     direction : right)
          -[:a {c}]-       (id: null     type: a         props: c        direction : none)
          -[b:a {c}]-      (id: b        type: a         props: c        direction : none)
          <-[:a {c}]-      (id: null     type: a         props: c        direction : left)
          <-[b:a {c}]-     (id: b        type: a         props: c        direction : left)
          -[:a {c}]->      (id: null     type: a         props: c        direction : right)
          -[b:a {c}]->     (id: b        type: a         props: c        direction : right)

          -[*1..2]->        (id: null     type: null      props: null     direction : var1-2)
          <-[*]-            (id: null     type: null      props: null     direction : var)
          -[*1..4]->        (id: null     type: null      props: null     direction : var1-4)
          <-[*3..4]-        (id: null     type: null      props: null     direction : var3-4)
         */

        String id;
        String type;
        JsonObject o;
        String direction;
        List<String> relString;
        List<String> propsString;

        while (!clause.isEmpty()) {
            id = null;
            type = null;
            propsString = null;
            o = null;
            direction = null;
            boolean varAdded = false;

            if (!clause.contains("-")) {
                // no relationships left to consider.
                break;
            } else {
                int posOfHyphen = clause.indexOf("-");

                if (clause.get(posOfHyphen + 2).equals("*")) {
                    String varD = "none";
                    m.setVarRel();

                    int posOfLHypher = clause.indexOf("-");
                    if (clause.get(posOfLHypher - 1).equals("<")) varD = "left";
                    int posOfRSq = clause.indexOf("]");
                    if (clause.get(posOfRSq + 2).equals(">")) varD = (varD.equals("left")) ? "none" : "right";

                    List<String> varRel = clause.subList(posOfLHypher + 2, posOfRSq);
                    if (varD.equals("right")) {
                        clause = clause.subList(posOfRSq + 3, clause.size());
                    } else {
                        clause = clause.subList(posOfRSq + 2, clause.size());
                    }

                    // add the variable to the set of relationships.
                    rels.add(extractVarRel(varRel, m, varD));
                    varAdded = true;
                } else if (clause.get(posOfHyphen - 1).equals("<")) {
                    direction = "left";

                    int lSq = clause.indexOf("[");
                    int rSq = clause.indexOf("]");

                    if (lSq != -1 && rSq != -1) {
                        relString = clause.subList(lSq + 1, rSq);

                        if (relString.contains("{")) {
                            int lCurly = relString.indexOf("{");
                            int rCurly = relString.indexOf("}");

                            if (lCurly != -1 && rCurly != -1) {
                                propsString = relString.subList(lCurly + 1, rCurly);
                                relString = relString.subList(0, lCurly);
                            }
                        }

                        String[] temp = extractIdAndLabels(relString);
                        id = temp[0];
                        type = temp[1];

                        if (propsString != null) {
                            o = getJSONProps(propsString);
                        }
                        clause = clause.subList(rSq + 2, clause.size());
                    } else {
                        clause = clause.subList(posOfHyphen + 2, clause.size());
                    }
                } else if (clause.get(posOfHyphen + 1).equals("-") &&
                        clause.get(posOfHyphen + 2).equals("(")) {
                    direction = "none";
                    clause = clause.subList(posOfHyphen + 2, clause.size());
                } else if (clause.get(posOfHyphen + 1).equals("-") &&
                        clause.get(posOfHyphen + 2).equals(">")) {
                    direction = "right";
                    clause = clause.subList(posOfHyphen + 3, clause.size());
                } else if (clause.get(posOfHyphen + 1).equals("[")) {
                    int lSq = clause.indexOf("[");
                    int rSq = clause.indexOf("]");

                    if (lSq != -1 && rSq != -1) {
                        relString = clause.subList(lSq + 1, rSq);

                        if (relString.contains("{")) {
                            int lCurly = relString.indexOf("{");
                            int rCurly = relString.indexOf("}");

                            if (lCurly != -1 && rCurly != -1) {
                                propsString = relString.subList(lCurly + 1, rCurly);
                                relString = relString.subList(0, lCurly);
                            }
                        }

                        String[] temp = extractIdAndLabels(relString);
                        id = temp[0];
                        type = temp[1];

                        if (propsString != null) {
                            o = getJSONProps(propsString);
                        }

                        if (clause.get(rSq + 1).equals("-") && clause.get(rSq + 2).equals(">")) {
                            direction = "right";
                            clause = clause.subList(rSq + 3, clause.size());
                        } else if (clause.get(rSq + 1).equals("-")) {
                            direction = "none";
                            clause = clause.subList(rSq + 2, clause.size());
                        }
                    }
                } else {
                    throw new Exception("RELATIONSHIP STRUCTURE IS INVALID");
                }
                if (!varAdded) rels.add(new CypRel(m.getInternalID(), id, type, o, direction));
            }
        }
        return rels;
    }

    /**
     * Takes token list of JSON based properties string, and convert this to an actual JSON object.
     *
     * @param propsString Token list in form similar to ["{", "key", ":", "value", "}"]
     * @return JSONObject matching the propsString argument.
     */
    private static JsonObject getJSONProps(List<String> propsString) {
        StringBuilder json = new StringBuilder();

        json.append("{");

        boolean listSyntax = propsString.contains("[");

        int i = 0;

        for (String a : propsString) {
            if (a.equals(",")) {
                continue;
            } else if (a.equals("[")) {
                json.append("[");
                continue;
            } else if (a.equals("]")) {
                json.setLength(json.length() - 2);
                json.append("]").append(", ");
            } else if (i % 3 == 0) {
                json.append("\"").append(a).append("\"");
            } else if (i % 3 == 1) {
                json.append(":");
            } else if (i % 3 == 2) {
                json.append(a).append(", ");
                if (listSyntax) continue;
            }
            i++;
        }

        // remove trailing comma
        json.setLength(json.length() - 2);
        json.append("}");
        return parser.parse(json.toString()).getAsJsonObject();
    }

    private static String[] extractIdAndLabels(List<String> tokens) {
        String[] idAndLabels = {null, null};

        if (tokens.isEmpty()) return idAndLabels;

        if (tokens.get(0).equals(":")) {
            // no id, just label(s)
            for (String label : tokens) {
                if (!label.equals(":")) {
                    if (idAndLabels[1] == null) {
                        idAndLabels[1] = label;
                    } else {
                        idAndLabels[1] = idAndLabels[1] + ", " + label;
                    }
                }
            }
            return idAndLabels;
        } else {
            idAndLabels[0] = tokens.get(0);
            if (tokens.size() == 1) {
                return idAndLabels;
            } else {
                tokens = tokens.subList(1, tokens.size());
                for (String label : tokens) {
                    if (!label.equals(":")) {
                        if (idAndLabels[1] == null) {
                            idAndLabels[1] = label;
                        } else {
                            idAndLabels[1] = idAndLabels[1] + ", " + label;
                        }
                    }
                }
                return idAndLabels;
            }
        }
    }

    private static WhereClause whereDecode(MatchClause matchC, CypherWalker cypherQ) throws Exception {
        WhereClause wc = new WhereClause(cypherQ.getWhereClause());
        extractWhere(wc.getClause(), matchC, wc);
        return wc;
    }

    private static WhereClause extractWhere(String allClause, MatchClause matchC, WhereClause wc) throws Exception {
        allClause = allClause.toLowerCase();
        ArrayList<String> whereComponents = new ArrayList<>();
        Map<String, String> whereMapping = new HashMap<>();

        while (!allClause.isEmpty()) {
            int posOfOr = (!allClause.contains(" or ")) ? 100000 : allClause.indexOf(" or ");
            int posOfAnd = (!allClause.contains(" and ")) ? 100000 : allClause.indexOf(" and ");

            if ((posOfAnd == 100000) && (posOfOr == 100000)) {
                whereComponents.add(allClause);
                allClause = "";
            } else if (posOfAnd < posOfOr) {
                String comps[] = allClause.split(" and ");
                whereComponents.add(comps[0]);
                whereMapping.put(comps[0], "and");
                allClause = allClause.substring(comps[0].length() + 5);
            } else {
                String comps[] = allClause.split(" or ");
                whereComponents.add(comps[0]);
                whereMapping.put(comps[0], "or");
                allClause = allClause.substring(comps[0].length() + 4);
            }
        }

        wc.setComponents(whereComponents);
        wc.setWhereMappings(whereMapping);

        for (String clause : wc.getComponents()) {
            int posInWhere = wc.getComponents().indexOf(clause);
            String typeBooleanA = null;
            if (posInWhere > 0) {
                typeBooleanA = wc.getWhereMappings().get(wc.getComponents().get(posInWhere - 1));
            }

            if (clause.contains("id(")) {
                String[] idAndValue = clause.split("\\) ");
                addIDWhere(idAndValue, matchC, typeBooleanA);
            } else if (clause.contains("exists(")) {
                String idAndProp = clause.substring(7, clause.length() - 1);
                addExistsWhere(idAndProp, matchC, typeBooleanA);
            } else if (clause.contains("labels(")) {
                String[] idAndValue = clause.split(" in ");
                addLabelsWhere(idAndValue, matchC, typeBooleanA);
            } else if (clause.contains(" = ")) {
                String[] idAndValue = clause.split(" = ");
                addCondition(idAndValue, matchC, "equals", typeBooleanA);
            } else if (clause.contains(" <> ")) {
                String[] idAndValue = clause.split(" <> ");
                addCondition(idAndValue, matchC, "nequals", typeBooleanA);
            } else if (clause.contains(" < ")) {
                String[] idAndValue = clause.split(" < ");
                addCondition(idAndValue, matchC, "lt", typeBooleanA);
            } else if (clause.contains(" > ")) {
                String[] idAndValue = clause.split(" > ");
                addCondition(idAndValue, matchC, "gt", typeBooleanA);
            } else if (clause.contains(" <= ")) {
                String[] idAndValue = clause.split(" <= ");
                addCondition(idAndValue, matchC, "le", typeBooleanA);
            } else if (clause.contains(" >= ")) {
                String[] idAndValue = clause.split(" >= ");
                addCondition(idAndValue, matchC, "ge", typeBooleanA);
            }
        }

        return wc;
    }

    private static void addLabelsWhere(String[] idAndValue, MatchClause matchC, String typeBool) throws Exception {
        String op;
        if (idAndValue[0].startsWith("not")) {
            op = "<>";
            idAndValue[0] = idAndValue[0].substring(4);
        } else op = "=";
        String val = idAndValue[0].replace("'", "");
        String id = idAndValue[1].substring(7, idAndValue[1].length() - 1);

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cN.getProps(), "label", val, op, typeBool);
                cN.setProps(obj);
                return;
            }
        }

        throw new Exception("WHERE CLAUSE MALFORMED");
    }

    private static void addExistsWhere(String clause, MatchClause matchC, String typeBool) throws Exception {
        String[] idAndProp = clause.split("\\.");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cN.getProps(), idAndProp[1], "null", "exists", typeBool);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cR.getProps(), idAndProp[1], "null", "exists", typeBool);
                cR.setProps(obj);
                return;
            }
        }

        throw new Exception("WHERE CLAUSE MALFORMED");
    }

    private static void addIDWhere(String[] idAndValue, MatchClause matchC, String typeBool) throws Exception {
        String id = idAndValue[0].substring(3);
        String opAndValue[] = idAndValue[1].split(" ");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cN.getProps(), "id", opAndValue[1], opAndValue[0], typeBool);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cR.getProps(), "id", opAndValue[1], opAndValue[0], typeBool);
                cR.setProps(obj);
                return;
            }
        }

        throw new Exception("WHERE CLAUSE MALFORMED");
    }

    private static void addCondition(String[] idAndValue, MatchClause matchC, String op,
                                     String typeBoolean) throws Exception {
        String[] idAndProp = idAndValue[0].split("\\.");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cN.getProps(), idAndProp[1], idAndValue[1], op, typeBoolean);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cR.getProps(), idAndProp[1], idAndValue[1], op, typeBoolean);
                cR.setProps(obj);
                return;
            }
        }

        throw new Exception("WHERE CLAUSE MALFORMED");
    }

    private static JsonObject addToJSONObject(JsonObject origProps, String prop, String value, String op,
                                              String typeBoolean) {
        JsonObject obj = origProps;
        if (origProps == null) obj = new JsonObject();

        String valueToAdd = "";
        if (obj.has(prop)) {
            valueToAdd = obj.get(prop).getAsString() + "~" + typeBoolean + "~";
        }
        switch (op) {
            case "equals":
            case "=":
                valueToAdd += "eq#" + value.replace("\"", "").toLowerCase() + "#qe";
                obj.addProperty(prop, valueToAdd);
                break;
            case "nequals":
            case "<>":
                valueToAdd += "ne#" + value.replace("\"", "").toLowerCase() + "#en";
                obj.addProperty(prop, valueToAdd);
                break;
            case "lt":
            case "<":
                valueToAdd += "lt#" + value.replace("\"", "").toLowerCase() + "#tl";
                obj.addProperty(prop, valueToAdd);
                break;
            case "gt":
            case ">":
                valueToAdd += "gt#" + value.replace("\"", "").toLowerCase() + "#tg";
                obj.addProperty(prop, valueToAdd);
                break;
            case "le":
            case "<=":
                valueToAdd += "le#" + value.replace("\"", "").toLowerCase() + "#el";
                obj.addProperty(prop, valueToAdd);
                break;
            case "ge":
            case ">=":
                valueToAdd += "ge#" + value.replace("\"", "").toLowerCase() + "#eg";
                obj.addProperty(prop, valueToAdd);
                break;
            case "exists":
                valueToAdd += "ex#" + value.replace("\"", "").toLowerCase() + "#xe";
                obj.addProperty(prop, valueToAdd);
                break;
        }
        return obj;
    }

    private static CypRel extractVarRel(List<String> varRel, MatchClause m, String varD) {
        String direction;

        if (varRel.size() == 1 && varRel.get(0).equals("*")) {
            // hack that deals with arbitrary long paths
            direction = "var1-100" + "#" + varD;
        } else {
            varRel = varRel.subList(1, varRel.size());

            if (varRel.size() == 1) {
                direction = "var" + varRel.get(0) + "#" + varD;
            } else {
                direction = "var" + varRel.get(0) + "-" + varRel.get(2) + "#" + varD;
            }
        }

        return new CypRel(m.getInternalID(), null, null, null, direction);
    }

    private static ReturnClause returnDecode(List<String> returnClause,
                                             MatchClause matchC, CypherWalker cypherQ) throws Exception {
        ReturnClause r = new ReturnClause();
        List<CypReturn> items = new ArrayList<>();

        List<String> currentWorking;

        // find all the separate parts of the return clause
        while (returnClause.contains(",")) {
            int posComma = returnClause.indexOf(",");
            currentWorking = returnClause.subList(0, posComma);
            returnClause = returnClause.subList(posComma + 1, returnClause.size());

            items.add(extractReturn(currentWorking, matchC, cypherQ));
        }

        if (!returnClause.isEmpty()) {
            items.add(extractReturn(returnClause, matchC, cypherQ));
        }

        r.setItems(items);
        return r;
    }

    private static CypReturn extractReturn(List<String> clause, MatchClause matchC, CypherWalker cypherQ)
            throws Exception {
        if (clause.size() == 3 && clause.contains(".")) {
            return new CypReturn(clause.get(0), clause.get(2), false, false, null, matchC);
        } else if (clause.size() == 1)
            if (clause.get(0).equals("*"))
                return new CypReturn(null, "*", false, false, null, matchC);
            else
                return new CypReturn(clause.get(0), null, false, false, null, matchC);
        else if (cypherQ.hasCount()) {
            String field = (clause.size() == 6) ? clause.get(4) : null;
            return new CypReturn(clause.get(2), field, true, false, null, matchC);
        } else if (cypherQ.hasCollect()) {
            String field = (clause.size() == 6) ? clause.get(4) : null;
            return new CypReturn(clause.get(2), field, false, true, null, matchC);
        } else if (cypherQ.hasCase()) {
            StringBuilder caseString = new StringBuilder();
            for (String s : clause) {
                if (s.equals(".")) {
                    caseString.setLength(caseString.length() - 1);
                    caseString.append(".");
                } else {
                    caseString.append(s).append(" ");
                }
            }
            return new CypReturn(clause.get(1), clause.get(3),
                    false, false, caseString.toString(), matchC);
        } else throw new Exception("RETURN CLAUSE MALFORMED" + clause);
    }

    private static OrderClause orderDecode(List<String> orderClause) throws Exception {
        OrderClause o = new OrderClause();

        List<CypOrder> items = new ArrayList<>();

        List<String> currentWorking;

        // find all the separate parts of the order by clause
        while (orderClause.contains(",")) {
            int posComma = orderClause.indexOf(",");
            currentWorking = orderClause.subList(0, posComma);
            orderClause = orderClause.subList(posComma + 1, orderClause.size());
            items.add(extractOrder(currentWorking));
        }

        if (!orderClause.isEmpty()) {
            items.add(extractOrder(orderClause));
        }

        o.setItems(items);
        return o;
    }

    private static CypOrder extractOrder(List<String> clause) throws Exception {
        if (clause.size() == 4 && clause.contains(".")) {
            return new CypOrder(clause.get(0), clause.get(2), clause.get(3));
        } else if (clause.size() == 3 && clause.contains(".")) {
            return new CypOrder(clause.get(0), clause.get(2), "asc");
        } else if (clause.size() == 5 && clause.contains("count")) {
            return new CypOrder(clause.get(2), "count(n)", clause.get(4));
        } else throw new Exception("ORDER CLAUSE MALFORMED");
    }

}
