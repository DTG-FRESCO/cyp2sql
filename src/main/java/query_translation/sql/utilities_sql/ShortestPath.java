package query_translation.sql.utilities_sql;

import intermediate_rep.*;

import java.util.Map;

/**
 * Class for translating Cypher with the shortestPath function to SQL.
 */
public class ShortestPath extends AbstractTranslation {
    /**
     * Use properties of the first node in the path, and if there are any, add them to the SQL statement
     * being generated.
     *
     * @param cypNode Cypher node for whom the properties/label are being extracted and used.
     * @return String of newly generated section of SQL.
     */
    private static String getFirstStep(CypNode cypNode) {
        StringBuilder sql = new StringBuilder();
        sql.append("nodes q ON leftnode = id");

        boolean hasWhere = false;

        if (cypNode.getType() != null) {
            sql.append(" WHERE label LIKE ");
            hasWhere = true;
            sql.append(TranslateUtils.genLabelLike(cypNode, "q"));
        }

        if (cypNode.getProps() != null) {
            if (hasWhere) sql.append(" AND ");
            else sql.append(" WHERE ");
            sql = TranslateUtils.getWholeWhereClause(sql, cypNode, "q");
        }

        return sql.toString();
    }

    /**
     * As part of the translation to SQL, multiple views are built up for each step in the path. To return
     * the correct shortestPath, the individual results from each step need to be combined together, and
     * that is what the SQL in this method is doing.
     *
     * @param lastIndex  The lastIndex value ensures that the name of this view will be distinct from the ones
     *                   previously created in this same query.
     * @param amountHigh To make sure all the views are combined, need to know how many were created.
     * @return SQL representing the joining of all the individual views previously created.
     */
    private static String joinViewsTogether(int lastIndex, int amountHigh) {
        StringBuilder sql = new StringBuilder();
        sql.append(alphabet[lastIndex]).append(" AS (SELECT * FROM a");
        for (int i = 1; i < amountHigh; i++) {
            sql.append(" UNION ALL SELECT * FROM ").append(alphabet[i]);
        }
        sql.append("), ");
        return sql.toString();
    }

    private static String getFinalSelect(int lastIndex, CypNode cN2, ReturnClause rc, Map<String, String> alias) {
        StringBuilder sql = new StringBuilder();
        StringBuilder thingsToGroupBy = new StringBuilder();

        sql.append("finStep AS (SELECT ");

        // return only the correct things
        for (CypReturn cR : rc.getItems()) {
            if (cR.getField() == null && cR.getCount() > 0) {
                sql.append("id");
            } else {
                sql.append("n01.").append(cR.getField());
            }
            sql.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            if (cR.getField() != null) {
                thingsToGroupBy.append(cR.getField()).append(", ");
            } else thingsToGroupBy.append("id").append(", ");
        }

        String table = TranslateUtils.getTable(rc);

        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }
        sql.append(", min(Depth), xx, Start ");
        sql.append("FROM ").append(table).append(" n01 INNER JOIN ").append(alphabet[lastIndex]);
        sql.append(" ON xx = id");

        boolean hasWhere = false;

        if (cN2.getType() != null) {
            sql.append(" WHERE label LIKE ");
            hasWhere = true;
            sql.append(TranslateUtils.genLabelLike(cN2, "n01"));
        }

        if (cN2.getProps() != null) {
            if (hasWhere) sql.append(" AND ");
            else sql.append(" WHERE ");
            sql = TranslateUtils.getWholeWhereClause(sql, cN2, "n01");
        }

        thingsToGroupBy = new StringBuilder(thingsToGroupBy.substring(0, thingsToGroupBy.length() - 2));
        sql.append(" GROUP BY ").append(thingsToGroupBy).append(", xx, Start) SELECT ");

        // return only the correct things
        for (CypReturn cR : rc.getItems()) {
            if (cR.hasAggFunc()) {
                sql.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
            }
            if (cR.getCount() > 0) {
                sql.append("count(");
                if (cR.getCount() == 2) sql.append("distinct ");
            }
            if (cR.getField() == null) {
                sql.append("id");
            } else {
                sql.append("n01.").append(cR.getField());
            }
            if (cR.hasAggFunc() || cR.getCount() > 0) {
                sql.append(") ").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            } else {
                sql.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
            }
        }

        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }

        sql.append(" FROM finStep n01");
        return sql.toString();
    }

    @Override
    public StringBuilder translate(StringBuilder shortPath, DecodedQuery dQMainPath) {
        shortPath.append("WITH a AS(SELECT unnest(rightnode) AS xx, 1 AS Depth, ARRAY[id] AS Path, id AS Start ");
        shortPath.append("FROM adjList_from INNER JOIN ");

        MatchClause matchC = dQMainPath.getMc();
        String direction = "none";
        int amountHigh = 0;

        // work out direction of query and upper and lower bound on number of edges
        // the query is allowed to traverse.
        for (CypRel cR : matchC.getRels()) {
            if (cR.getDirection().contains("var")) {
                String dirAndAmount[] = cR.getDirection().split("#");
                direction = dirAndAmount[1];
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

        shortPath.append(getFirstStep(cN1));
        shortPath.append("), ");

        int lastIndex = 1;

        for (int i = 1; i < amountHigh; i++) {
            shortPath.append(alphabet[i]).append(" AS (SELECT unnest(rightnode) AS xx, ");
            shortPath.append(i + 1).append(" AS Depth, ");
            shortPath.append(alphabet[i - 1]).append(".Path || ARRAY[xx] AS Path, ");
            shortPath.append(alphabet[i - 1]).append(".Path[1] AS Start FROM adjList_from INNER JOIN ");
            shortPath.append(alphabet[i - 1]).append(" ON leftnode = xx), ");
            lastIndex = i;
        }

        if (!shortPath.toString().endsWith(", ")) {
            shortPath.append(", ");
        }

        lastIndex++;

        shortPath.append(joinViewsTogether(lastIndex, amountHigh));

        shortPath.append(getFinalSelect(lastIndex, cN2, dQMainPath.getRc(),
                dQMainPath.getCypherAdditionalInfo().getAliasMap()));

        if (dQMainPath.getOc() != null)
            shortPath = SQLTranslate.obtainOrderByClause(dQMainPath.getOc(), dQMainPath.getRc(), shortPath, "n01");

        int skipAmount = dQMainPath.getSkipAmount();
        int limitAmount = dQMainPath.getLimitAmount();

        if (skipAmount != -1) shortPath.append(" OFFSET ").append(skipAmount);
        if (limitAmount != -1) shortPath.append(" LIMIT ").append(limitAmount);

        shortPath.append(";");

        return shortPath;
    }
}
