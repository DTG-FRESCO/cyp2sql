package query_translation.sql.utilities_sql;

import intermediate_rep.*;
import production.C2SMain;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class for translating Cypher with no relationships to SQL.
 */
public class NoRels extends AbstractTranslation {

    private static StringBuilder getSelect(ReturnClause rc, MatchClause mc, StringBuilder sql,
                                           boolean hasDistinct, Map<String, String> alias) {
        sql.append("SELECT ");
        if (hasDistinct) sql.append("DISTINCT ");

        for (CypReturn r : rc.getItems()) {
            if (r.getNodeID() == null && r.getField().equals("*")) {
                sql.append("*");
            } else if (r.getCaseString() != null) {
                String caseString = r.getCaseString();
                sql.append(caseString.replace(r.getNodeID() + "." + r.getField(),
                        "n01." + r.getField()));
            } else {
                for (CypNode cN : mc.getNodes()) {
                    if (r.getNodeID().equals(cN.getId())) {
                        String prop = r.getField();
                        if (r.hasAggFunc()) {
                            sql.append(CypAggFuncs.sqlEquiv(r.getAggFunc()));
                        }
                        if (r.getCount() > 0) {
                            sql.append("count(");
                            if (r.getCount() == 2) sql.append("distinct ");
                        }
                        if (prop != null) {
                            sql.append("n01").append(".").append(prop);
                            if (r.hasAggFunc() || r.getCount() > 0) sql.append(") ");
                            sql.append(TranslateUtils.useAlias(r.getNodeID(), r.getField(), alias)).append(", ");
                        } else {
                            sql.append("n01.*");
                            if (r.hasAggFunc() || r.getCount() > 0) sql.append(") ");
                            sql.append(TranslateUtils.useAlias("count(" + r.getNodeID() + ")", r.getField(), alias))
                                    .append(", ");
                        }
                        break;
                    }
                }
                for (String s : WithSQL.withMapping.keySet()) {
                    if (r.getNodeID().equals(s)) {
                        String prop = r.getField();
                        if (r.hasAggFunc()) {
                            sql.append(CypAggFuncs.sqlEquiv(r.getAggFunc()));
                        }
                        if (r.getCount() > 0) {
                            sql.append("count(");
                            if (r.getCount() == 2) sql.append("distinct ");
                        }
                        if (prop != null) {
                            sql.append(WithSQL.withMapping.get(s)).append(".").append(prop);
                            if (r.hasAggFunc() || r.getCount() > 0) sql.append(") ");
                            sql.append(TranslateUtils.useAlias(r.getNodeID(), r.getField(), alias)).append(", ");
                        } else {
                            sql.append(WithSQL.withMapping.get(s)).append(".*");
                            if (r.hasAggFunc() || r.getCount() > 0) sql.append(") ");
                            sql.append(TranslateUtils.useAlias("count(" + r.getNodeID() + ")", r.getField(), alias))
                                    .append(", ");
                        }
                        break;
                    }
                }
            }
        }


        if (sql.toString().endsWith(", ")) sql.setLength(sql.length() - 2);
        sql.append(" ");
        return sql;
    }

    private static StringBuilder getFrom(StringBuilder sql, MatchClause mc, ReturnClause rc) {
        sql.append("FROM ");
        String table = TranslateUtils.getLabelType(mc.getNodes().get(0).getType());

        if (!table.equals("nodes")) {
            useOptimalTable = true;
        } else {
            boolean possibleOpti = true;
            String possTable = "nodes";

            for (CypReturn cR : rc.getItems()) {
                if (!C2SMain.labelProps.containsKey(cR.getField())) {
                    possibleOpti = false;
                    break;
                } else {
                    String newTable = C2SMain.labelProps.get(cR.getField());
                    if (!possTable.equals(newTable) && !possTable.equals("nodes")) {
                        possibleOpti = false;
                        break;
                    }
                    possTable = newTable;
                }
            }

            if (possibleOpti) table = possTable;
            else table = "nodes";
        }
        sql.append(table);
        sql.append(" n01");

        if (!WithSQL.withMapping.isEmpty()) sql.append(", wA");

        return sql;
    }

    private static StringBuilder getWhere(StringBuilder sql, ReturnClause returnC, MatchClause matchC) {
        boolean hasWhere = false;
        ArrayList<String> nodesScanned = new ArrayList<>();

        for (CypReturn cR : returnC.getItems()) {
            // prevents multiple return items that are referring to the same node
            // (i.e. n.name, n.type) from creating a nonsense WHERE clause in SQL.
            if (nodesScanned.contains(cR.getNodeID())) continue;

            if (cR.getNodeID() == null && cR.getField().equals("*")) {
                CypNode cN = matchC.getNodes().get(0);
                sql.append(" WHERE n01.label LIKE ").append(TranslateUtils.genLabelLike(cN, "n01"));
                if (cN.getProps() != null) {
                    sql.append(" AND ");
                    sql = TranslateUtils.getWholeWhereClause(sql, cN);
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
                            sql.append(" WHERE ");
                            hasWhere = true;
                        }
                        sql = TranslateUtils.getWholeWhereClause(sql, cN);
                    }

                    if (cN.getType() != null && !useOptimalTable) {
                        if (!hasWhere) {
                            sql.append(" WHERE n01.label LIKE");
                            hasWhere = true;
                        } else {
                            if (!sql.toString().endsWith("AND ")) sql.append(" AND ");
                            sql.append("n01.label LIKE");
                        }
                        sql.append(" ").append(TranslateUtils.genLabelLike(cN, "n01"));
                    }
                } else if (!WithSQL.withMapping.isEmpty()) {
                    cN = matchC.getNodes().get(0);
                    if (cN.getProps() != null) {
                        if (!hasWhere) {
                            sql.append(" WHERE ");
                            hasWhere = true;
                        }
                        sql = TranslateUtils.getWholeWhereClause(sql, cN);
                    }
                }
            }
        }

        return sql;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery) {
        useOptimalTable = false;
        sql = getSelect(decodedQuery.getRc(), decodedQuery.getMc(), sql,
                decodedQuery.getCypherAdditionalInfo().hasDistinct(),
                decodedQuery.getCypherAdditionalInfo().getAliasMap());
        sql = getFrom(sql, decodedQuery.getMc(), decodedQuery.getRc());
        sql = getWhere(sql, decodedQuery.getRc(), decodedQuery.getMc());
        return sql;
    }
}
