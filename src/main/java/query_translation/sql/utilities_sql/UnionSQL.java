package query_translation.sql.utilities_sql;

import java.util.ArrayList;

/**
 * Class for creating correct SQL query when UNION/UNION ALL keyword is present
 * in the original Cypher query.
 */
public class UnionSQL {
    /**
     * Generate the union of two or more queries.
     *
     * @param queries   Individual SQL queries.
     * @param unionType Discrete: either UNION or UNION ALL.
     * @return Complete SQL query with UNION/UNION ALL added.
     */
    public static String genUnion(ArrayList<String> queries, String unionType) {
        StringBuilder unionSQL = new StringBuilder();
        boolean insertCloseParenth = false;
        for (String s : queries) {
            unionSQL.append(s.substring(0, s.length() - 1));
            if (insertCloseParenth) unionSQL.append(")");
            unionSQL.append(" ").append(unionType).append("( ");
            insertCloseParenth = true;
        }
        unionSQL.setLength(unionSQL.length() - (2 + unionType.length()));
        unionSQL.append(";");
        return unionSQL.toString();
    }
}
