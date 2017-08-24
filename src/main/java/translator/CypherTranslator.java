/*
 * Copyright (c) 2017.
 *
 * Oliver Crawford <o.crawford@hotmail.co.uk>
 * Lucian Carata <lc525@cam.ac.uk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package translator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.DQInvalidException;
import intermediate_rep.*;
import production.C2SMain;

import java.util.*;

import static intermediate_rep.CypAggFuncs.AGG_COLLECT;
import static intermediate_rep.CypAggFuncs.AGG_NONE;
import static intermediate_rep.CypCount.*;

/**
 * Main class for translating the Cypher input to its internal representation.
 * The representation includes extracting the nodes and relationships, finding
 * out what needs to be returned, seeing if they have any important constraints
 * etc. A DecodedQuery object is returned containing all the relevant information
 * for the SQL generation module.
 */
class CypherTranslator {
    // Static parser for JSON properties.
    private static final JsonParser parser = new JsonParser();

    /**
     * Creates a DecodedQuery object for the Cypher input.
     *
     * @param tokenList Token list generated from the ANTLR framework.
     * @param cypWalker CypherWalker object containing additional info about the Cypher input.
     * @return DecodedQuery object representing the Cypher input.
     */
    static DecodedQuery generateDecodedQuery(ArrayList<String> tokenList, CypherWalker cypWalker) {
        // find positions of the tokens in the query (-1 means not found)
        int posOfMatch = tokenList.indexOf("match");
        int posOfWhere = tokenList.indexOf("where");
        int posOfReturn = tokenList.indexOf("return");

        // if no RETURN keyword is present, then the Cypher input must contain a CREATE OR DELETE keyword.
        if (posOfReturn == -1) {
            try {
                return generateDQCreateDelete(tokenList, cypWalker, posOfMatch, posOfWhere);
            } catch (DQInvalidException ex) {
                return null;
            }
        } else {
            int posOfOrder = tokenList.indexOf("order");
            int posOfSkip = tokenList.indexOf("skip");
            int posOfLimit = tokenList.indexOf("limit");

            // MATCH and RETURN always present
            List<String> matchClause;
            List<String> returnClause;

            // ORDER BY may or may not be present, hence must be initialised to NULL.
            List<String> orderClause = null;

            // Depending on whether or not the keyword WHERE is included, the tokens for each part of the input
            // need to extracted and stored.
            if (cypWalker.doesCluaseHaveWhere()) {
                matchClause = tokenList.subList(posOfMatch + 1, posOfWhere);
            } else {
                matchClause = tokenList.subList(posOfMatch + 1, posOfReturn);
            }

            if (posOfOrder == -1) {
                if (posOfSkip == -1 && posOfLimit == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 +
                            ((cypWalker.hasDistinct()) ? 1 : 0), tokenList.size());
                } else if (posOfLimit == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypWalker.hasDistinct()) ? 1 : 0), posOfSkip);
                } else if (posOfSkip == -1) {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypWalker.hasDistinct()) ? 1 : 0), posOfLimit);
                } else {
                    returnClause = tokenList.subList(posOfReturn + 1 + ((cypWalker.hasDistinct()) ? 1 : 0), posOfSkip);
                }
            } else {
                returnClause = tokenList.subList(posOfReturn + 1 + ((cypWalker.hasDistinct()) ? 1 : 0), posOfOrder);

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

            try {
                MatchClause matchC = matchDecode(matchClause);
                ReturnClause returnC = returnDecode(returnClause, matchC, cypWalker);

                // if ORDER BY is present in the query
                OrderClause orderC = null;
                if (orderClause != null) {
                    orderC = orderDecode(orderClause, cypWalker);
                }

                int skipAmount = (posOfSkip != -1) ? cypWalker.getSkipAmount() : -1;
                int limitAmount = (posOfLimit != -1) ? cypWalker.getLimitAmount() : -1;

                if (cypWalker.doesCluaseHaveWhere()) whereDecode(matchC, cypWalker);

                return new DecodedQuery(matchC, returnC, orderC, skipAmount, limitAmount, cypWalker, null);
            } catch (DQInvalidException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Generate a DecodedQuery object for Cypher statements with CREATE or DELETE keywords.
     *
     * @param tokenList  List of tokens in the Cypher query.
     * @param cypWalker  CypherWalker object.
     * @param posOfMatch Index in token list where MATCH keyword is present (-1 if not).
     * @param posOfWhere Index in token list where WHERE keyword is present (-1 if not).
     * @return DecodedQuery object with all relevant information.
     */
    private static DecodedQuery generateDQCreateDelete(ArrayList<String> tokenList, CypherWalker cypWalker,
                                                       int posOfMatch, int posOfWhere) throws DQInvalidException {
        int posOfCreate = tokenList.indexOf("create");
        int posOfDelete = tokenList.indexOf("delete");

        MatchClause matchC_Create_Delete;

        if (posOfCreate == -1 && posOfDelete == -1) throw new DQInvalidException("Cypher input malformed.");

        if (posOfCreate == -1) {
            // delete statement.
            List<String> deleteClause;
            deleteClause = tokenList.subList(posOfMatch + 1, posOfDelete);
            matchC_Create_Delete = matchDecode(deleteClause);
        } else {
            // create statement.
            List<String> matchCreate;
            if (cypWalker.doesCluaseHaveWhere()) {
                matchCreate = tokenList.subList(posOfCreate + 1, posOfWhere);
                matchC_Create_Delete = matchDecode(matchCreate);
                whereDecode(matchC_Create_Delete, cypWalker);
            } else {
                matchCreate = tokenList.subList(posOfCreate + 1, tokenList.size());
                matchC_Create_Delete = matchDecode(matchCreate);
            }
        }

        return new DecodedQuery(matchC_Create_Delete, null, null, -1, -1, cypWalker, null);
    }

    /**
     * Translate MATCH part of the Cypher input.
     *
     * @param matchClause The tokens comprising the MATCH clause of Cypher.
     * @return MatchClause object with all the important information inside it.
     */
    private static MatchClause matchDecode(List<String> matchClause) throws DQInvalidException {
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
    private static ArrayList<CypNode> extractNodes(List<String> clause, MatchClause m) throws DQInvalidException {
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
            } else
                throw new DQInvalidException("Node structure invalid in the original Cypher MATCH clause.");

            if (propsString != null) {
                o = getJSONProps(propsString);
            }

            String[] idAndLabels = extractIdAndLabels(nodeString);
            id = idAndLabels[0];
            labels = idAndLabels[1];

            // add the formatted node object to list of nodes
            // associated with the match clause
            // calling m.getInternalID() will force the internalID property of the object to automatically
            // be incremented.
            int internalID = m.getInternalID();

            if (id != null && !nodeIDS.containsKey(id)) {
                nodeIDS.put(id, internalID);
            } else if (nodeIDS.containsKey(id)) {
                labels = nodes.get(nodeIDS.get(id) - 1).getType();
                o = nodes.get(nodeIDS.get(id) - 1).getProps();
            }
            nodes.add(new CypNode(internalID, id, labels, o));
        }
        return nodes;
    }

    /**
     * Extract the relationships from the MATCH clause.
     *
     * @param clause Token version of the clause.
     * @param m      Internal representation of the MATCH clause.
     * @return An ArrayList of CypRel objects that correspond to the relationships (if any) described by Cypher.
     * @throws DQInvalidException Relationship structure invalid in some way.
     */
    private static ArrayList<CypRel> extractRels(List<String> clause, MatchClause m) throws DQInvalidException {
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

                    int posOfLHyphen = clause.indexOf("-");
                    if (clause.get(posOfLHyphen - 1).equals("<")) varD = "left";

                    int posOfRSq = clause.indexOf("]");
                    if (clause.get(posOfRSq + 2).equals(">")) varD = (varD.equals("left")) ? "none" : "right";

                    List<String> varRel = clause.subList(posOfLHyphen + 2, posOfRSq);
                    if (varD.equals("right")) {
                        clause = clause.subList(posOfRSq + 3, clause.size());
                    } else {
                        clause = clause.subList(posOfRSq + 2, clause.size());
                    }

                    // add the variable to the set of relationships.
                    rels.add(extractVarRel(varRel, m.getInternalID(), varD));
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
                } else if (clause.get(posOfHyphen + 1).equals("-") && clause.get(posOfHyphen + 2).equals("(")) {
                    direction = "none";
                    clause = clause.subList(posOfHyphen + 2, clause.size());
                } else if (clause.get(posOfHyphen + 1).equals("-") && clause.get(posOfHyphen + 2).equals(">")) {
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
                    throw new DQInvalidException("Relationship structure is invalid or cannot currently be translated.");
                }
                if (!varAdded) rels.add(new CypRel(m.getInternalID(), id, type, o, direction));
            }
        }
        return rels;
    }

    /**
     * Takes token list of JSON based properties string, and converts this to an actual JSON object.
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

    /**
     * Extracts the id and label(s) from a list of tokens. This is returned in a size 2 String array.
     *
     * @param tokens Token list being examined for id and label(s).
     * @return String array; [0] containing the id and [1] containing the label(s).
     */
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

    /**
     * Method to calculate the direction for a variable path length relationship. The # separates the lengths
     * specified for the variable length (the -[*1..9]- part), and the direction of the path (whether it is
     * 'left', 'right', or 'none'. The latter part is defined by the argument varDirection.
     *
     * @param varRel       The token list describing the variable length path.
     * @param internalID   ID of the relationship in the context of the MatchClause object.
     * @param varDirection The direction of the path: none, left, or right.
     * @return A CypRel object to be stored.
     */
    private static CypRel extractVarRel(List<String> varRel, int internalID, String varDirection) {
        String direction;

        if (varRel.size() == 1 && varRel.get(0).equals("*")) {
            // hack that deals with arbitrary long paths
            direction = "var1-100" + "#" + varDirection;
        } else {
            varRel = varRel.subList(1, varRel.size());

            if (varRel.size() == 1) {
                direction = "var" + varRel.get(0) + "#" + varDirection;
            } else {
                direction = "var" + varRel.get(0) + "-" + varRel.get(2) + "#" + varDirection;
            }
        }

        return new CypRel(internalID, null, null, null, direction);
    }

    /**
     * Translate the RETURN part of the Cypher input.
     *
     * @param returnClause Token list for the RETURN part of the Cypher input.
     * @param matchC       MatchClause object for the same Cypher input (the MATCH part of the input MUST BE decoded before
     *                     the RETURN part can be).
     * @param cypWalker    Additional information about the query.
     * @return ReturnClause object
     * @throws DQInvalidException Error in creating the ReturnClause object (exception thrown in extractReturn method).
     */
    private static ReturnClause returnDecode(List<String> returnClause, MatchClause matchC, CypherWalker cypWalker)
            throws DQInvalidException {
        ReturnClause r = new ReturnClause();

        List<CypReturn> items = new ArrayList<>();

        List<String> currentWorkingTokens;

        // find all the separate parts of the return clause
        while (returnClause.contains(",")) {
            int posComma = returnClause.indexOf(",");
            currentWorkingTokens = returnClause.subList(0, posComma);
            returnClause = returnClause.subList(posComma + 1, returnClause.size());
            items.add(extractReturn(currentWorkingTokens, matchC, cypWalker));
        }

        if (!returnClause.isEmpty()) {
            items.add(extractReturn(returnClause, matchC, cypWalker));
        }

        r.setItems(items);

        return r;
    }

    /**
     * Extracts a CypReturn object from a list of tokens describing an individual component from the RETURN part
     * of the original Cypher input.
     *
     * @param clause    List of tokens describing the individual return component.
     * @param matchC    MatchClause of same Cypher input.
     * @param cypWalker CypherWalker object used in the parsing process.
     * @return CypReturn object generated from the list of tokens passed as an argument to this function.
     */
    private static CypReturn extractReturn(List<String> clause, MatchClause matchC, CypherWalker cypWalker)
            throws DQInvalidException {
        /*

        In order below of the types of return items that can be successfully parsed, and te string representation
        of what they are parsed into when they become CypReturn objects. The type and pos of the CypReturn
        object are determined in the constructor of the CypReturn object.
        NOTE: Exception is thrown otherwise.

        1.  id(n) = [id, (, n, )] --> ID: n, FIELD: id, COUNT: false, COLLECT: false, CASE: null
        2.  a.name = [a, ., name] --> ID: a, FIELD: name, COUNT: false, COLLECT: false, CASE: null
        4.  a = [a] --> ID: a, FIELD: null, COUNT: false, COLLECT: false, CASE: null
        5.  count(a) = [count, (, a, )] --> ID: a, FIELD: null, COUNT: true, COLLECT: false, CASE: null
        6.  count(a.name) = [count, (, a, ., name, )] --> ID: a, FIELD: name, COUNT: true, COLLECT: false, CASE: null
        6a. count (distinct a.name) = [count, (, distinct, a, ., name, )] -->
                ID: a, FIELD: name, COUNT: distinct, COLLECT: false, CASE: null
        7.  collect(a) = [collect, (, a, )] --> ID: a, FIELD: null, COUNT: false, COLLECT: true, CASE: null
        8.  collect(a.name) = [collect, (, a, ., name, )] --> ID: a, FIELD: name, COUNT: false, COLLECT: true, CASE: null
        9.  case a.status WHEN a.status = 1 THEN 'good' ELSE 'bad' END = [case, a, ., status, when, a, ., status, =, 1,
                then, 'good', else, 'bad', end] --> ID: a, FIELD: status, COUNT: false, COLLECT: false,
                CASE: case when a.status = 1 THEN 'good' ELSE 'bad' END
        10. aggregating functions (see point 8 above).

         */
        // 1.
        if (clause.size() == 4 && clause.get(0).equals("id") && clause.get(1).equals("(")) {
            C2SMain.needToPrintID = true;
            return new CypReturn(clause.get(2), "id", COUNT_FALSE, AGG_NONE, null, matchC);
        }
        // 2.
        else if (clause.size() == 3 && clause.contains(".")) {
            return new CypReturn(clause.get(0), clause.get(2), COUNT_FALSE, AGG_NONE, null, matchC);
        }
        // 4.
        else if (clause.size() == 1) {
            return new CypReturn(clause.get(0), null, COUNT_FALSE, AGG_NONE, null, matchC);
        }
        // 5. and 6. and 6a.
        else if (cypWalker.hasCount()) {
            if (clause.size() == 7 && clause.get(2).equals("distinct"))
                return new CypReturn(clause.get(3), clause.get(5), COUNT_DISTINCT, AGG_NONE, null, matchC);
            String field = (clause.size() == 6) ? clause.get(4) : null;
            return new CypReturn(clause.get(2), field, COUNT_TRUE, AGG_NONE, null, matchC);
        }
        // 7. and 8.
        else if (cypWalker.hasCollect()) {
            String field = (clause.size() == 6) ? clause.get(4) : null;
            return new CypReturn(clause.get(2), field, COUNT_FALSE, AGG_COLLECT, null, matchC);
        }
        // 9.
        else if (cypWalker.hasCase()) {
            StringBuilder caseString = new StringBuilder();
            for (String s : clause) {
                if (s.equals(".")) {
                    caseString.setLength(caseString.length() - 1);
                    caseString.append(".");
                } else {
                    caseString.append(s).append(" ");
                }
            }
            return new CypReturn(clause.get(1), clause.get(3), COUNT_FALSE, AGG_NONE, caseString.toString(), matchC);
        }
        // 10a.
        else if (cypWalker.hasAverage() || cypWalker.hasSum() || cypWalker.hasMin() || cypWalker.hasMax()) {
            String field = (clause.size() == 6) ? clause.get(4) : null;
            int typeAgg = CypAggFuncs.convert(clause.get(0));
            return new CypReturn(clause.get(2), field, COUNT_FALSE, typeAgg, null, matchC);
        } else throw new DQInvalidException("Return clause is malformed: " + clause);
    }

    /**
     * Translate the ORDER BY part of the Cypher input (if one is present).
     *
     * @param orderClause Tokenised form of the ORDER BY part of the Cypher input.
     * @param cypWalker
     * @return OrderClause representing the tokenised input.
     */
    private static OrderClause orderDecode(List<String> orderClause, CypherWalker cypWalker) throws DQInvalidException {
        OrderClause o = new OrderClause();

        List<CypOrder> items = new ArrayList<>();

        List<String> currentWorking;

        // find all the separate parts of the order by clause
        while (orderClause.contains(",")) {
            int posComma = orderClause.indexOf(",");
            currentWorking = orderClause.subList(0, posComma);
            orderClause = orderClause.subList(posComma + 1, orderClause.size());
            items.add(extractOrder(currentWorking, cypWalker));
        }

        if (!orderClause.isEmpty()) {
            items.add(extractOrder(orderClause, cypWalker));
        }

        o.setItems(items);
        return o;
    }

    /**
     * Extracts a CypOrder object from the tokenised input.
     *
     * @param clause    Token list describing the ORDER BY part of the original Cypher query.
     * @param cypWalker CypherWalker object.
     * @return CypOrder object matching the inputted token list.
     */
    private static CypOrder extractOrder(List<String> clause, CypherWalker cypWalker) throws DQInvalidException {
        /*
        In order below of the types of ORDER BY structures that can be successfully parsed, and the
        string representation of what they are parsed into when they become CypOrder objects.
        NOTE: Exception is thrown otherwise.

        NOTE: the third type may need fixing...

        1. a.name desc = [a, ., name, asc] --> ID: a, FIELD: name, ORDER_TYPE: desc
        2. a.name = [a, ., name] --> ID: a, FIELD: name, ORDER_TYPE: asc (this is the default setting)
        3. count(n.name) asc = [count, n, ., name, asc] --> ID: a, FIELD: count(n), ORDER_TYPE: asc
        4. some alias is used
        */
        // 1.
        if (clause.size() == 4 && clause.contains(".")) {
            return new CypOrder(clause.get(0), clause.get(2), clause.get(3));
        }
        // 2.
        else if (clause.size() == 3 && clause.contains(".")) {
            return new CypOrder(clause.get(0), clause.get(2), "asc");
        }
        // 3.
        else if (clause.size() == 5 && clause.contains("count")) {
            return new CypOrder(clause.get(2), "count(n)", clause.get(4));
        }
        // 4.
        else if (clause.size() == 1 && (clause.contains("asc") || clause.contains("desc"))) {
            return new CypOrder(null, cypWalker.getOrderClause().split(" ")[0], clause.get(0));
        } else throw new DQInvalidException("The Order By clause is malformed in some way " +
                "and could not be translated: " + clause);
    }

    /**
     * Generate a WhereClause object from the Cypher input. Where string should be contained
     * within the cypWalker object.
     *
     * @param matchC    MatchClause object generated from same Cypher input.
     * @param cypWalker CypherWalker object (contains string for WHERE part of input which is being parsed)
     */
    private static void whereDecode(MatchClause matchC, CypherWalker cypWalker) throws DQInvalidException {
        String fullClause = cypWalker.getWhereClause().toLowerCase();

        // store the individual parts of the full WHERE clause of the Cypher input.
        ArrayList<String> whereComponents = new ArrayList<>();

        // store the boolean operator that follows the individual component
        Map<String, String> operatorAfter = new TreeMap<>();

        // split by AND, OR, XOR
        while (!fullClause.isEmpty()) {
            int posOfOr = (!fullClause.contains(" or ")) ? Integer.MAX_VALUE : fullClause.indexOf(" or ");
            int posOfAnd = (!fullClause.contains(" and ")) ? Integer.MAX_VALUE : fullClause.indexOf(" and ");

            if ((posOfAnd == Integer.MAX_VALUE) && (posOfOr == Integer.MAX_VALUE)) {
                whereComponents.add(fullClause);
                fullClause = "";
            }
            // check for AND firstly
            else if ((posOfAnd < posOfOr)) {
                String comps[] = fullClause.split(" and ");
                whereComponents.add(comps[0]);
                fullClause = fullClause.substring(comps[0].length() + 5);
                operatorAfter.put(comps[0], (!fullClause.isEmpty()) ? "and" : null);
            }
            // check for OR else throw an error
            else if ((posOfOr < posOfAnd)) {
                String comps[] = fullClause.split(" or ");
                whereComponents.add(comps[0]);
                fullClause = fullClause.substring(comps[0].length() + 4);
                operatorAfter.put(comps[0], (!fullClause.isEmpty()) ? "or" : null);
            }
        }

        // iterate through each clause and parse it correctly.
        int posInClause = 1;
        int numLeftBracketsNotClosed = 0;
        for (String clause : whereComponents) {
            // useful to see the part of the WHERE clause being parsed (if debugging)
            // System.out.println(clause);
            CypWhere cW = new CypWhere(posInClause++);
            cW.setBoolOp(operatorAfter.get(clause));

            boolean clauseHasLeftBr = false;

            // extract bracketing information
            while (clause.startsWith("(")) {
                cW.addLBrack();
                clause = clause.substring(1);
                numLeftBracketsNotClosed++;
                clauseHasLeftBr = true;
            }

            boolean clauseHasRightBr = false;

            while (clause.endsWith(")")) {
                if (numLeftBracketsNotClosed == 0 || numLeftBracketsNotClosed == 1 && clauseHasRightBr) break;
                if (!clauseHasLeftBr) {
                    cW.addRBrack();
                    clause = clause.substring(0, clause.length() - 1);
                    numLeftBracketsNotClosed--;
                    clauseHasRightBr = true;
                } else break;
            }

            // extract NOT information
            boolean not = false;
            if (clause.startsWith("not")) {
                not = true;
                clause = clause.substring(4);
            }

            // parse the component according to its keywords
            if (clause.startsWith("any(")) {
                String collection = clause.substring(clause.indexOf("in") + 3, clause.indexOf("where") - 1);
                if (clause.contains("in [")) {
                    String predicateValue = clause.substring(clause.lastIndexOf("in [") + 3, clause.length() - 1);
                    addAnyWhere(collection, predicateValue, matchC, not, cW, "in");
                } else {
                    String predicateValue = clause.split(" = ")[1];
                    predicateValue = predicateValue.substring(0, predicateValue.length() - 1);
                    addAnyWhere(collection, predicateValue, matchC, not, cW, "=");
                }
            } else if (clause.contains("id(") && !clause.contains("[")) {
                String[] idAndValue = clause.split("\\) ");
                addIDWhere(idAndValue, matchC, not, cW);
            } else if (clause.contains("exists(")) {
                String idAndProp = clause.substring(7, clause.length() - 1);
                addExistsWhere(idAndProp, matchC, not, cW);
            } else if (clause.contains("labels(")) {
                String[] idAndValue = clause.split(" in ");
                addLabelsWhere(idAndValue, matchC, not, cW);
            } else if (clause.contains(" = ")) {
                String[] idAndValue = clause.split(" = ");
                addCondition(idAndValue, matchC, "equals", not, cW);
            } else if (clause.contains(" <> ")) {
                String[] idAndValue = clause.split(" <> ");
                addCondition(idAndValue, matchC, "nequals", not, cW);
            } else if (clause.contains(" < ")) {
                String[] idAndValue = clause.split(" < ");
                addCondition(idAndValue, matchC, "lt", not, cW);
            } else if (clause.contains(" > ")) {
                String[] idAndValue = clause.split(" > ");
                addCondition(idAndValue, matchC, "gt", not, cW);
            } else if (clause.contains(" <= ")) {
                String[] idAndValue = clause.split(" <= ");
                addCondition(idAndValue, matchC, "le", not, cW);
            } else if (clause.contains(" >= ")) {
                String[] idAndValue = clause.split(" >= ");
                addCondition(idAndValue, matchC, "ge", not, cW);
            } else if (clause.contains(":")) {
                String[] idAndLabel = clause.split(":");
                String x = "'" + idAndLabel[1] + "' in labels(" + idAndLabel[0] + ")";
                String[] idAndValue = x.split(" in ");
                addLabelsWhere(idAndValue, matchC, not, cW);
            } else if (clause.contains(" in ")) {
                if (clause.startsWith("id(")) {
                    clause = clause.substring(3, clause.indexOf(")")) + ".id"
                            + clause.substring(clause.indexOf(")") + 1);
                }
                String[] idAndValue = clause.split(" in ");
                addCondition(idAndValue, matchC, "in", not, cW);
            } else if (clause.contains(" is ")) {
                String[] idAndValue = clause.split(" is ");
                String op = (idAndValue[1].contains("not")) ? "is_not_null" : "is_null";
                addCondition(idAndValue, matchC, op, not, cW);
            } else throw new DQInvalidException("WHERE component could not be translated to an intermediate" +
                    "representation: " + clause);
        }
    }

    /**
     * If the WHERE component contains the keyword ANY, such as in:
     * WHERE any(name in a.name WHERE name IN ['uid', 'postgres'])
     * then this is the method that will be called to convert it into the intermediate representation.
     *
     * @param collection     The collection value (in the example above, collection would be equal to a.name).
     * @param predicateValue The list after the keyword IN (['uid', 'postgres']).
     * @param matchC         MatchClause of the Cypher input.
     * @param not            If the NOT keyword is used in this component, this flag is set to true.
     * @param cW             CypWhere object.
     * @param typeAny        Either set to 'in' or '='; in the former case of 'in', this is true when the WHERE
     *                       component is of the format (any(... IN [...])). In the latter case of 'in', the
     *                       WHERE component is of the form (any(... = ...)). See a list of the example queries
     *                       for more info.
     */
    private static void addAnyWhere(String collection, String predicateValue, MatchClause matchC, boolean not,
                                    CypWhere cW, String typeAny) throws DQInvalidException {
        String[] idAndProp;

        if (collection.startsWith("labels(")) {
            idAndProp = new String[2];

            // id value
            idAndProp[0] = collection.substring(7, collection.length() - 1);
            // property will be label
            idAndProp[1] = "label";
        } else idAndProp = collection.split("\\.");

        String op;
        if (typeAny.equals("in")) op = "anyin";
        else op = "any=";

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cN.getProps(), idAndProp[1], predicateValue, op, not, cW);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cR.getProps(), idAndProp[1], predicateValue, op, not, cW);
                cR.setProps(obj);
                return;
            }
        }

        throw new DQInvalidException("WHERE CLAUSE MALFORMED!");
    }

    /**
     * If the WHERE component contains the function id(...), such as in:
     * WHERE id(s) = 349
     * then this is the method that will be called to convert it into the intermediate representation.
     *
     * @param idAndValue String array - idAndValue[0] will include the id of the node; idAndValue[1] will contain
     *                   the logical operator and the value.
     * @param matchC     MatchClause of the Cypher input.
     * @param not        If the NOT keyword is used in this component, this flag is set to true.
     * @param cW         CypWhere object.
     */
    private static void addIDWhere(String[] idAndValue, MatchClause matchC, boolean not, CypWhere cW)
            throws DQInvalidException {
        String id = idAndValue[0].substring(3);
        // opAndValue[0] will contain the operator (such as =, <, >=)
        // opAndValue[1] will contain the value (such as 349, [1, 2], etc.)
        String opAndValue[] = idAndValue[1].split(" ");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cN.getProps(), "id", opAndValue[1], opAndValue[0], not, cW);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cR.getProps(), "id", opAndValue[1], opAndValue[0], not, cW);
                cR.setProps(obj);
                return;
            }
        }

        throw new DQInvalidException("WHERE CLAUSE MALFORMED");
    }

    /**
     * If the WHERE component contains the function exists(...), such as in:
     * WHERE exists(n.value)
     * then this is the method that will be called to convert it into the intermediate representation.
     *
     * @param clause Contains the string within the exists function (in the example above, this would be 'n.value').
     * @param matchC MatchClause of the Cypher input.
     * @param not    If the NOT keyword is used in this component, this flag is set to true.
     * @param cW     CypWhere object.
     */
    private static void addExistsWhere(String clause, MatchClause matchC, boolean not, CypWhere cW)
            throws DQInvalidException {
        String[] idAndProp = clause.split("\\.");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cN.getProps(), idAndProp[1], "null", "exists", not, cW);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cR.getProps(), idAndProp[1], "null", "exists", not, cW);
                cR.setProps(obj);
                return;
            }
        }

        throw new DQInvalidException("WHERE CLAUSE MALFORMED");
    }

    /**
     * If the WHERE component contains the function labels(...), such as in:
     * WHERE 'Local' in labels(n)
     * then this is the method that will be called to convert it into the intermediate representation.
     *
     * @param idAndValue String array; idAndValue[0] = value being searched for ('Local'), idAndValue[1] = labels(id).
     * @param matchC     MatchClause of the Cypher input.
     * @param not        If the NOT keyword is used in this component, this flag is set to true.
     * @param cW         CypWhere object.
     */
    private static void addLabelsWhere(String[] idAndValue, MatchClause matchC, boolean not, CypWhere cW)
            throws DQInvalidException {
        String id = idAndValue[1].substring(7, idAndValue[1].length() - 1);
        String val = idAndValue[0].replace("'", "");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(id)) {
                JsonObject obj = addToJSONObject(cN.getProps(), "label", val, "=", not, cW);
                cN.setProps(obj);
                return;
            }
        }

        throw new DQInvalidException("WHERE CLAUSE MALFORMED");
    }

    /**
     * Converts WHERE component to intermediate representation.
     *
     * @param idAndValue The id and property are contained within idAndValue[0]. The value is within idAndValue[1].
     * @param matchC     MatchClause of the Cypher input.
     * @param op         Logical operator of the where component.
     * @param not        If the NOT keyword is used in this component, this flag is set to true.
     * @param cW         CypWhere object.
     */
    private static void addCondition(String[] idAndValue, MatchClause matchC, String op, boolean not, CypWhere cW)
            throws DQInvalidException {
        String[] idAndProp = idAndValue[0].split("\\.");

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cN.getProps(), idAndProp[1], idAndValue[1], op, not, cW);
                cN.setProps(obj);
                return;
            }
        }

        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idAndProp[0])) {
                JsonObject obj = addToJSONObject(cR.getProps(), idAndProp[1], idAndValue[1], op, not, cW);
                cR.setProps(obj);
                return;
            }
        }

        throw new DQInvalidException("WHERE CLAUSE MALFORMED");
    }

    /**
     * Adds WHERE component to the properties of either the node or relationship the WHERE component was
     * referring to. The new JSONObject is then returned from this method.
     *
     * @param origProps The original properties of the node/relationship.
     * @param prop      The property to modify.
     * @param value     The value to add.
     * @param op        The type of logical operator involved.
     * @param not       If the NOT keyword was used then this is true, otherwise false.
     * @param cW        CypWhere object.
     * @return The modified properties of the node/relationship.
     */
    private static JsonObject addToJSONObject(JsonObject origProps, String prop, String value, String op,
                                              boolean not, CypWhere cW) {
        JsonObject obj = origProps;
        if (origProps == null) obj = new JsonObject();

        String valueToAdd = "";
        if (obj.has(prop)) {
            // if a key of the node/relationship has multiple conditions (such as in more complex queries,
            // the conditions should be separated by a tilde symbol).
            valueToAdd = obj.get(prop).getAsString() + "~";
        }

        if (not) op = invertOp(op);

        // include this meta string so that during the translation, the right bracketing can be included,
        // the correct boolean operators are used, and the ordering is consistent with the original
        // Cypher input.
        // format: p2and@r1#
        // meaning: the where component is the second component in the whole clause (p2), it is followed by an
        // AND, and the string should end with a right parenthesis.
        String metaString = "p" + cW.getPosInWhere() + cW.getBoolOp() + "@" + cW.getBracketing() + "$";

        switch (op) {
            case "equals":
            case "=":
                valueToAdd += metaString + "eq#" + value.replace("\"", "").toLowerCase() + "#qe";
                obj.addProperty(prop, valueToAdd);
                break;
            case "nequals":
            case "<>":
                valueToAdd += metaString + "ne#" + value.replace("\"", "").toLowerCase() + "#en";
                obj.addProperty(prop, valueToAdd);
                break;
            case "lt":
            case "<":
                valueToAdd += metaString + "lt#" + value.replace("\"", "").toLowerCase() + "#tl";
                obj.addProperty(prop, valueToAdd);
                break;
            case "gt":
            case ">":
                valueToAdd += metaString + "gt#" + value.replace("\"", "").toLowerCase() + "#tg";
                obj.addProperty(prop, valueToAdd);
                break;
            case "le":
            case "<=":
                valueToAdd += metaString + "le#" + value.replace("\"", "").toLowerCase() + "#el";
                obj.addProperty(prop, valueToAdd);
                break;
            case "ge":
            case ">=":
                valueToAdd += metaString + "ge#" + value.replace("\"", "").toLowerCase() + "#eg";
                obj.addProperty(prop, valueToAdd);
                break;
            case "exists":
                valueToAdd += metaString + "ex#" + value.replace("\"", "").toLowerCase() + "#xe";
                obj.addProperty(prop, valueToAdd);
                break;
            case "not exists":
                valueToAdd += metaString + "nx#" + value.replace("\"", "").toLowerCase() + "#xn";
                obj.addProperty(prop, valueToAdd);
                break;
            case "in":
                valueToAdd += metaString + "in#" + value.replace("\"", "").toLowerCase() + "#ni";
                obj.addProperty(prop, valueToAdd);
                break;
            case "anyin":
                valueToAdd += metaString + "anyin#" + value.replace("\"", "").toLowerCase() + "#niyna";
                obj.addProperty(prop, valueToAdd);
                break;
            case "any=":
                valueToAdd += metaString + "anyeq#" + value.replace("\"", "").toLowerCase() + "#qeyna";
                obj.addProperty(prop, valueToAdd);
                break;
            case "is_null":
                valueToAdd += metaString + "isn#" + value.replace("\"", "").toLowerCase() + "#nsi";
                obj.addProperty(prop, valueToAdd);
                break;
            case "is_not_null":
                valueToAdd += metaString + "non#" + value.replace("\"", "").toLowerCase() + "#non";
                obj.addProperty(prop, valueToAdd);
                break;
        }
        return obj;
    }

    /**
     * Inverts the operator in cases of NOT being used.
     * = --{@literal >} {@literal <}{@literal >}
     * {@literal <}= --{@literal >} {@literal >}=
     * exists --{@literal >} not exists
     *
     * @param op Original operator.
     * @return String of the inverted operator.
     */
    private static String invertOp(String op) {
        String invertedOp = "";
        switch (op) {
            case "equals":
            case "=":
                invertedOp = "<>";
                break;
            case "nequals":
            case "<>":
                invertedOp = "=";
                break;
            case "lt":
            case "<":
                invertedOp = ">=";
                break;
            case "gt":
            case ">":
                invertedOp = "<=";
                break;
            case "le":
            case "<=":
                invertedOp = ">";
                break;
            case "ge":
            case ">=":
                invertedOp = "<";
                break;
            case "exists":
                invertedOp = "not exists";
                break;
            case "not exists":
                invertedOp = "exists";
                break;
            case "is_null":
                invertedOp = "is_not_null";
                break;
            case "is_not_null":
                invertedOp = "is_null";
                break;
        }
        return invertedOp;
    }
}
