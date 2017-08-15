package query_translation.sql.utilities_sql;

import intermediate_rep.*;
import production.C2SProperties;

import java.util.Map;

/**
 * Class for translating Cypher with a single variable length path to SQL.
 */
public class SingleVar extends AbstractTranslation {
    private static StringBuilder getFinalSelect(StringBuilder sql, ReturnClause returnC,
                                                Map<String, String> alias, CypNode cn2,
                                                int amountHigh, boolean usesDistinct) {
        sql.append(" SELECT ");
        if (usesDistinct) sql.append("DISTINCT ");

        // return only the correct things
        for (CypReturn cR : returnC.getItems()) {
            if (cR.hasAggFunc()) {
                sql.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
            }
            if (cR.getCount() > 0) {
                sql.append("count(");
                if (cR.getCount() == 2) sql.append("distinct ");
            }
            if (cR.getField() == null) {
                sql.append("*");
            } else {
                sql.append("n01.").append(cR.getField());
            }
            if (cR.hasAggFunc() || cR.getCount() > 0) {
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
            TranslateUtils.getWholeWhereClause(sql, cn2, "n01");
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
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery, C2SProperties props) {
        MatchClause matchC = decodedQuery.getMc();

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

        String matView = (direction.equals("right")) ? "adjList_from" : "adjList_to";

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
                sql.append("unnest(rightnode) AS xx FROM ").append(matView).append(" INNER JOIN ");

                if (i == 0) {
                    // use node data
                    String relToUse = TranslateUtils.getLabelType(cN1.getType(), props);
                    sql.append(relToUse).append(" zz ON leftnode = zz.id");
                    if (cN1.getProps() != null) {
                        sql.append(" WHERE ");
                        TranslateUtils.getWholeWhereClause(sql, cN1, "zz");
                    }
                } else {
                    sql.append(alphabet[(i - 1) % 26]).append(extendID[(i - 1) / 26]).append(" ON leftnode = xx");
                }

                sql.append("), ");
            }
        }

        sql = getFinalSelect(sql, decodedQuery.getRc(), decodedQuery.getCypherAdditionalInfo().getAliasMap(),
                cN2, amountHigh, decodedQuery.getCypherAdditionalInfo().hasDistinct());

        return sql;
    }
}
