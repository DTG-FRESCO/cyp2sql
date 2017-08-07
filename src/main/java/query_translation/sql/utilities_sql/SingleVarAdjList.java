package query_translation.sql.utilities_sql;

import intermediate_rep.*;

import java.util.Map;

/**
 * Class for translating Cypher with a single variable length path to SQL.
 */
public class SingleVarAdjList extends AbstractTranslation {
    private static StringBuilder getFinalSelect(StringBuilder sql, ReturnClause returnC,
                                                Map<String, String> alias, CypNode cn2,
                                                int amountHigh, WhereClause wc, boolean usesDistinct) {
        sql.append(" SELECT ");
        if (usesDistinct) sql.append("DISTINCT ");

        // return only the correct things
        for (CypReturn cR : returnC.getItems()) {
            if (cR.getCollect()) sql.append("array_agg(");
            if (cR.getCount()) sql.append("count(");
            if (cR.getField() == null) {
                sql.append("*");
            } else {
                sql.append("n01.").append(cR.getField());
            }
            if (cR.getCollect() || cR.getCount()) {
                sql.append(") ").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            } else {
                sql.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            }
        }

        sql.setLength(sql.length() - 2);
        sql.append(" ");

        String table = TranslateUtils.getTable(returnC);
        sql.append("FROM ").append(table).append(" n01 ");

        sql.append(" INNER JOIN ").append(alphabet[amountHigh % 26])
                .append(extendID[amountHigh / 26]).append(" ON xx = id");

        boolean hasWhere = false;

        if (cn2.getProps() != null) {
            sql.append(" WHERE ");
            hasWhere = true;
            TranslateUtils.getWholeWhereClause(sql, cn2, wc, "n01");
        }

        if (cn2.getType() != null && table.equals("nodes")) {
            if (!hasWhere) {
                sql.append(" WHERE n01.label LIKE");
            } else {
                sql.append(" AND n01.label LIKE");
            }
            sql.append(" ").append(TranslateUtils.genLabelLike(cn2, "n01"));
        }

        return sql;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery) {
        MatchClause matchC = decodedQuery.getMc();
        WhereClause whereC = decodedQuery.getWc();

        String direction = "none";
        int amountLow = 0;
        int amountHigh = 0;

        // work out direction of query and upper and lower bound on number of edges
        // the query is allowed to traverse.
        for (CypRel cR : matchC.getRels()) {
            if (cR.getDirection().contains("var")) {
                String dirAndAmount[] = cR.getDirection().split("#");
                direction = dirAndAmount[1];
                amountLow = Integer.parseInt(dirAndAmount[0].split("-")[0].substring(3));
                amountHigh = Integer.parseInt(dirAndAmount[0].split("-")[1]);
            }
        }

        CypNode cN1 = matchC.getNodes().get(0);
        CypNode cN2 = matchC.getNodes().get(1);

        sql.append("WITH ");

        for (int i = 0; i <= amountHigh; i++) {
            sql.append(alphabet[i % 26]).append(extendID[i / 26]).append(" AS (SELECT ");

            if (i == amountHigh) {
                sql.append("xx FROM ");
                int j = i;
                while (j > (amountLow - 1)) {
                    j--;
                    sql.append(alphabet[j % 26]).append(extendID[j / 26]).append(" UNION ALL SELECT xx FROM ");
                }
                sql.setLength(sql.length() - 26);
                sql.append(")");
            } else {
                sql.append("unnest(rightnode) AS xx FROM adjList_from INNER JOIN ");

                if (i == 0) {
                    // use node data
                    String relToUse = TranslateUtils.getLabelType(cN1.getType());
                    sql.append(relToUse).append(" zz ON leftnode = zz.id");
                    if (cN1.getProps() != null) {
                        sql.append(" WHERE ");
                        TranslateUtils.getWholeWhereClause(sql, cN1, whereC, "zz");
                    }
                } else {
                    sql.append(alphabet[(i - 1) % 26]).append(extendID[(i - 1) / 26]).append(" ON leftnode = xx");
                }

                sql.append("), ");
            }
        }

        sql = getFinalSelect(sql, decodedQuery.getRc(), decodedQuery.getCypherAdditionalInfo().getAliasMap(),
                cN2, amountHigh, whereC, decodedQuery.getCypherAdditionalInfo().hasDistinct());

        return sql;
    }
}
