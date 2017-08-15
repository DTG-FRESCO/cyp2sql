package query_translation.sql.utilities_sql;

import intermediate_rep.*;
import production.C2SProperties;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class for translating Cypher with no relationships to SQL.
 */
public class NoRels extends AbstractTranslation {

    private static StringBuilder getSelect(ReturnClause rc, MatchClause mc, boolean hasDistinct,
                                           Map<String, String> alias) {
        StringBuilder selectSQL = new StringBuilder();

        // add the initial keywords to the translation.
        selectSQL.append("SELECT ");
        if (hasDistinct) selectSQL.append("DISTINCT ");

        // loop through all the return components, and add them to the SELECT clause individually.
        for (CypReturn cR : rc.getItems()) {
            // check for CASE string first.
            if (cR.getCaseString() != null) {
                String caseString = cR.getCaseString();
                selectSQL.append(caseString.replace(cR.getNodeID() + "." + cR.getField(),
                        "n01." + cR.getField()));
            } else {
                CypNode cN = mc.getNodes().get(0);

                if (cR.getNodeID().equals(cN.getId())) {
                    String prop = cR.getField();

                    if (cR.hasAggFunc()) {
                        selectSQL.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
                    } else if (cR.getCount() > 0) {
                        selectSQL.append("count(");
                        if (cR.getCount() == 2) selectSQL.append("distinct ");
                    }

                    if (prop != null) {
                        selectSQL.append("n01").append(".").append(prop);
                        if (cR.hasAggFunc() || cR.getCount() > 0) selectSQL.append(") ");
                        selectSQL.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
                    } else {
                        selectSQL.append("n01.*");
                        if (cR.hasAggFunc() || cR.getCount() > 0) selectSQL.append(") ");
                        selectSQL.append(TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", cR.getField(), alias))
                                .append(", ");
                    }
                }

                for (String s : WithSQL.withMapping.keySet()) {
                    if (cR.getNodeID().equals(s)) {
                        String prop = cR.getField();

                        if (cR.hasAggFunc()) {
                            selectSQL.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
                        }

                        if (cR.getCount() > 0) {
                            selectSQL.append("count(");
                            if (cR.getCount() == 2) selectSQL.append("distinct ");
                        }

                        if (prop != null) {
                            selectSQL.append(WithSQL.withMapping.get(s)).append(".").append(prop);
                            if (cR.hasAggFunc() || cR.getCount() > 0) selectSQL.append(") ");
                            selectSQL.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
                        } else {
                            selectSQL.append(WithSQL.withMapping.get(s)).append(".*");
                            if (cR.hasAggFunc() || cR.getCount() > 0) selectSQL.append(") ");
                            selectSQL.append(TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", cR.getField(), alias))
                                    .append(", ");
                        }
                        break;
                    }
                }
            }
        }

        if (selectSQL.toString().endsWith(", ")) selectSQL.setLength(selectSQL.length() - 2);
        selectSQL.append(" ");
        return selectSQL;
    }

    private static StringBuilder getFrom(MatchClause mc, ReturnClause rc, C2SProperties props) {
        StringBuilder fromSQL = new StringBuilder();

        fromSQL.append("FROM ");
        String table = TranslateUtils.getLabelType(mc.getNodes().get(0).getType(), props);

        if (!table.equals("nodes")) {
            usesOptimalTable = true;
        } else {
            table = TranslateUtils.findOptimisedTable(rc);
        }

        fromSQL.append(table).append(" n01");

        if (!WithSQL.withMapping.isEmpty()) fromSQL.append(", wA");
        return fromSQL;
    }

    private static StringBuilder getWhere(ReturnClause returnC, MatchClause matchC) {
        StringBuilder where = new StringBuilder();

        boolean hasWhere = false;
        ArrayList<String> nodesScanned = new ArrayList<>();

        for (CypReturn cR : returnC.getItems()) {
            // prevents multiple return items that are referring to the same node
            // (i.e. n.name, n.type) from creating a nonsense WHERE clause in SQL.
            if (nodesScanned.contains(cR.getNodeID())) continue;

            if (cR.getNodeID() == null && cR.getField().equals("*")) {
                CypNode cN = matchC.getNodes().get(0);
                where.append(" WHERE n01.label LIKE ").append(TranslateUtils.genLabelLike(cN, "n01"));
                if (cN.getProps() != null) {
                    where.append(" AND ");
                    where = TranslateUtils.getWholeWhereClause(where, cN);
                }
            } else {
                CypNode cN = null;

                for (CypNode c : matchC.getNodes()) {
                    if (c.getId().equals(cR.getNodeID())) {
                        cN = c;
                        nodesScanned.add(cR.getNodeID());
                    }
                }

                if (cN != null) {
                    if (cN.getProps() != null) {
                        if (!hasWhere) {
                            where.append(" WHERE ");
                            hasWhere = true;
                        }
                        where = TranslateUtils.getWholeWhereClause(where, cN);
                    }

                    if (cN.getType() != null && !usesOptimalTable) {
                        if (!hasWhere) {
                            where.append(" WHERE n01.label LIKE");
                            hasWhere = true;
                        } else {
                            if (!where.toString().endsWith("AND ")) where.append(" AND ");
                            where.append("n01.label LIKE");
                        }
                        where.append(" ").append(TranslateUtils.genLabelLike(cN, "n01"));
                    }
                } else if (!WithSQL.withMapping.isEmpty()) {
                    cN = matchC.getNodes().get(0);
                    if (cN.getProps() != null) {
                        if (!hasWhere) {
                            where.append(" WHERE ");
                            hasWhere = true;
                        }
                        where = TranslateUtils.getWholeWhereClause(where, cN);
                    }
                }
            }
        }

        return where;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery, C2SProperties props) {
        usesOptimalTable = false;
        StringBuilder select = getSelect(decodedQuery.getRc(), decodedQuery.getMc(),
                decodedQuery.getCypherAdditionalInfo().hasDistinct(),
                decodedQuery.getCypherAdditionalInfo().getAliasMap());

        StringBuilder from = getFrom(decodedQuery.getMc(), decodedQuery.getRc(), props);
        StringBuilder where = getWhere(decodedQuery.getRc(), decodedQuery.getMc());

        sql.append(select).append(from).append(where);
        return sql;
    }
}
