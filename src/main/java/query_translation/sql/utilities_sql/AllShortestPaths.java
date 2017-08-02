package query_translation.sql.utilities_sql;

import com.google.gson.JsonObject;
import intermediate_rep.*;

import java.util.Map;

public class AllShortestPaths extends AbstractTranslation {
    private static StringBuilder getFinalSelect(StringBuilder asp, ReturnClause rc, Map<String, String> alias) {
        asp.append("SELECT ");

        // return only the correct things
        for (CypReturn cR : rc.getItems()) {
            if (cR.getCollect()) asp.append("array_agg(");
            if (cR.getCount()) asp.append("count(");
            if (cR.getField() == null) {
                asp.append("*");
            } else {
                asp.append("n01.").append(cR.getField());
            }
            if (cR.getCollect() || cR.getCount()) {
                asp.append(") ").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            } else {
                asp.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            }
        }

        String table = TranslateUtils.getTable(rc);

        if (asp.toString().endsWith(", ")) {
            asp.setLength(asp.length() - 2);
        }
        asp.append(" ");
        asp.append("FROM ").append(table).append(" n01, asp WHERE n01.id = asp.idr;");
        return asp;
    }

    private static String getTCSelect(CypNode cN, WhereClause wc) {
        StringBuilder idlSelect = new StringBuilder();
        idlSelect.append("SELECT id FROM ");
        String table = TranslateUtils.getLabelType(cN.getType());
        idlSelect.append(table).append(" x");
        JsonObject obj = cN.getProps();
        if (obj != null) {
            idlSelect.append(" WHERE ");
            idlSelect = TranslateUtils.getWholeWhereClause(idlSelect, cN, wc, "x");
            return idlSelect.append(")").toString();
        } else return idlSelect.append(")").toString();
    }

    @Override
    public StringBuilder translate(StringBuilder asp, DecodedQuery dQMainPath) {
        asp.append("WITH asp AS(SELECT tc.* FROM tclosure tc INNER JOIN (SELECT idl, idr, min(depth) AS MinPath FROM ");
        asp.append("tclosure WHERE idl IN (");

        MatchClause matchC = dQMainPath.getMc();
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

        CypNode cN1 = null;
        CypNode cN2 = null;

        // build up query in same direction as relationship is going.
        if (direction.equals("left")) {
            cN1 = matchC.getNodes().get(1);
            cN2 = matchC.getNodes().get(0);
        } else if (direction.equals("right")) {
            cN1 = matchC.getNodes().get(0);
            cN2 = matchC.getNodes().get(1);
        }

        asp.append(getTCSelect(cN1, dQMainPath.getWc()));
        asp.append(" AND idr IN(");
        asp.append(getTCSelect(cN2, dQMainPath.getWc()));
        asp.append(" GROUP BY idl, idr) t ON tc.idr = t.idr AND tc.depth = t.MinPath AND ");
        asp.append(" tc.idl = t.idl AND depth >= ").append(amountLow).append(" AND ");
        asp.append(" depth <= ").append(amountHigh).append(") ");

        asp = getFinalSelect(asp, dQMainPath.getRc(), dQMainPath.getCypherAdditionalInfo().getAliasMap());
        return asp;
    }
}
