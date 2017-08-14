package query_translation.sql.utilities_sql;

import intermediate_rep.CypAggFuncs;
import intermediate_rep.CypReturn;
import intermediate_rep.DecodedQuery;
import query_translation.sql.conversion_types.AbstractConversion;
import query_translation.sql.conversion_types.Multiple_With_Cypher;
import translator.CypherTokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static query_translation.sql.utilities_sql.SQLTranslate.obtainOrderByClause;

public class WithSQL {
    private static final String o = "order";
    private static final String r = "return";
    private static final String s = "skip";
    private static final String l = "limit";
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static Map<String, String> withMapping = new HashMap<>();

    public static String genTemp(String query, int numWith, DecodedQuery withPartDQ) {
        String initial = "CREATE TEMP VIEW w" + String.valueOf(alphabet[numWith]).toUpperCase() + " AS (";
        String resultingView = initial + query.substring(0, query.length() - 1) + ");";
        if (numWith > 0 && withPartDQ.getMc().getRels().size() > 0) {
            String prevView = String.valueOf(alphabet[numWith - 1]);
            int indexLastWhere = resultingView.lastIndexOf("WHERE");
            resultingView = resultingView.substring(0, indexLastWhere) + ", w" + prevView + " WHERE " +
                    resultingView.substring(indexLastWhere + 6);
            for (String s : Multiple_With_Cypher.mappingMultipleWith.keySet()) {
                if (Multiple_With_Cypher.mappingMultipleWith.get(s).endsWith(String.valueOf(alphabet[numWith - 1]).toUpperCase())) {
                    resultingView = resultingView.substring(0, resultingView.length() - 2);
                    resultingView = resultingView + " AND w" + alphabet[numWith - 1]
                            + ".id = a.a2);";
                }
            }
        } else if (numWith > 0) {
            String prevView = String.valueOf(alphabet[numWith - 1]);
            resultingView = resultingView.replace(" WHERE", ", w" + prevView + " WHERE ");
        }
        return resultingView;
    }

    // current WITH statement setup presumes no aliasing of return in second part of the WITH clause.
    public static String createSelectWhere(String query, DecodedQuery dQ) {
        StringBuilder sWith = new StringBuilder();
        ArrayList<String> tokens = CypherTokenizer.getTokenList(query, false);

        // get SELECT
        sWith = getSelectForWith(sWith, tokens, dQ);

        // get WHERE
        if (!tokens.get(0).equals(o) && !tokens.get(0).equals(s) && !tokens.get(0).equals(l)
                && !tokens.get(0).equals(r)) {
            sWith = getWhereForWith(sWith, tokens);
        }

        // get ORDER
        if (tokens.contains("order")) {
            sWith = getOrderByForWith(sWith, tokens);
        }

        int posOfSkip = tokens.indexOf("skip");
        if (posOfSkip != -1) sWith.append(" OFFSET ").append(tokens.get(++posOfSkip));
        int posOfLimit = tokens.indexOf("limit");
        if (posOfLimit != -1) sWith.append(" LIMIT ").append(tokens.get(++posOfLimit));

        return sWith.toString() + ";";
    }

    private static StringBuilder getOrderByForWith(StringBuilder sWith, ArrayList<String> tokens) {
        int posOfOrder = tokens.indexOf("order");
        sWith.append(" ORDER BY ");
        while (true) {
            String field = tokens.get(posOfOrder + 2);
            String dir = (posOfOrder + 3 >= tokens.size()) ? null : tokens.get(posOfOrder + 3);
            if (dir != null) {
                dir = (dir.equals("asc") || dir.equals("desc")) ? dir : null;
            }
            sWith.append(field).append(" ").append((dir == null) ? "" : dir);
            posOfOrder += 2;
            if (posOfOrder + 1 >= tokens.size() || !tokens.get(posOfOrder + 1).equals(",")) {
                break;
            } else {
                posOfOrder++;
            }
        }
        return sWith;
    }

    private static StringBuilder getWhereForWith(StringBuilder sWith, ArrayList<String> tokens) {
        int i = 0;
        sWith.append(" WHERE ");
        StringBuilder whereStmt = new StringBuilder();
        while (true) {
            String currentTok = tokens.get(i);

            // loop termination condition
            if (currentTok.equals(o) || currentTok.equals(r) || currentTok.equals(s) || currentTok.equals(l)) break;

            whereStmt.append(" ").append(currentTok);
            i++;
        }
        return sWith.append(whereStmt);
    }

    private static StringBuilder getSelectForWith(StringBuilder sWith, ArrayList<String> tokens, DecodedQuery dQ) {
        sWith.append("SELECT ");
        int posOfReturn = tokens.indexOf("return");

        for (int i = posOfReturn + 1; i < tokens.size(); i++) {
            String currentTok = tokens.get(i);
            if (currentTok.equals("order") || currentTok.equals("skip") || currentTok.equals("limit")) {
                break;
            } else {
                StringBuilder returnStmt = new StringBuilder(currentTok);

                while (i + 1 < tokens.size()) {
                    if (!tokens.get(i + 1).equals(",") && !tokens.get(i + 1).equals("order") &&
                            !tokens.get(i + 1).equals("skip") && !tokens.get(i + 1).equals("limit")
                            && !tokens.get(i + 1).equals(".")) {
                        returnStmt.append(" ").append(tokens.get(i++));
                    } else if (tokens.get(i + 1).equals(".")) {
                        returnStmt.append(".").append(tokens.get(i + 2));
                        i += 2;
                    } else {
                        i++;
                        break;
                    }
                }

                String[] idAndProp = returnStmt.toString().split("\\.");

                boolean sWithChanged = false;
                for (CypReturn cR : dQ.getRc().getItems()) {
                    if (cR.getField() != null && cR.getField().startsWith("count")) {
                        sWith.append(idAndProp[0]).append(", ");
                        sWithChanged = true;
                        break;
                    } else if (cR.getNodeID().equals(idAndProp[0])) {
                        if (cR.getField() == null && idAndProp[1] == null) {
                            sWith.append("*, ");
                        } else {
                            String field = (idAndProp[1] == null) ? cR.getField() : idAndProp[1];
                            sWith.append(field).append(", ");
                        }
                        sWithChanged = true;
                        break;
                    }
                }

                if (!sWithChanged) sWith.append(idAndProp[0]).append(", ");

                if (tokens.get(i).equals("order") || tokens.get(i).equals("skip") ||
                        tokens.get(i).equals("limit")) break;
            }
        }

        sWith.setLength(sWith.length() - 2);
        sWith.append(" FROM wA");

        return sWith;
    }

    public static String createSelectOB(DecodedQuery dQ) {
        StringBuilder sWith = new StringBuilder();
        sWith.append("SELECT ");
        for (CypReturn cR : dQ.getRc().getItems()) {
            if (cR.hasAggFunc()) {
                sWith.append(CypAggFuncs.sqlEquiv(cR.getAggFunc())).append(cR.getField()).append("), ");
            }
        }
        if (sWith.length() > 7) sWith.setLength(sWith.length() - 2);
        sWith.append(" FROM wA");
        return sWith.toString();
    }

    public static String createSelectMatch(String secondWith, DecodedQuery dqFirstWith) {
        withMapping.put(dqFirstWith.getRc().getItems().get(0).getNodeID(), "wA");

        StringBuilder resSecWith = new StringBuilder();
        DecodedQuery dQSecWith = AbstractConversion.convertCypherToSQL(secondWith);

        if (dQSecWith.getMc().getRels().isEmpty()) {
            NoRels nr = new NoRels();
            resSecWith = nr.translate(resSecWith, dQSecWith);
        } else {
            MultipleRel mr = new MultipleRel();
            resSecWith = mr.translate(resSecWith, dQSecWith);
        }

        if (dQSecWith.getOc() != null)
            resSecWith = obtainOrderByClause(dQSecWith.getOc(), dQSecWith.getRc(), resSecWith, "wA");

        int skipAmount = dQSecWith.getSkipAmount();
        int limitAmount = dQSecWith.getLimitAmount();
        if (skipAmount != -1) resSecWith.append(" OFFSET ").append(skipAmount);
        if (limitAmount != -1) resSecWith.append(" LIMIT ").append(limitAmount);

        resSecWith.append(";");

        withMapping = new HashMap<>();
        return resSecWith.toString();
    }
}