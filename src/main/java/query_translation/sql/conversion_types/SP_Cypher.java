package query_translation.sql.conversion_types;

import intermediate_rep.DecodedQuery;
import production.C2SMain;
import query_translation.sql.utilities_sql.ShortestPath;
import translator.CypherTokenizer;

/**
 * Class for dealing with the shortestPath function in Cypher.
 */
public class SP_Cypher extends AbstractConversion {
    @Override
    public String convertQuery(String cypher) {
        cypher = cypher.toLowerCase();
        int returnIndex = cypher.indexOf("return");
        int whereIndex = cypher.indexOf("where");
        String whereClause = null;

        if (whereIndex != -1) {
            whereClause = cypher.substring(whereIndex, returnIndex - 1);
        }

        int indexToUse = (whereIndex == -1) ? returnIndex : whereIndex;

        String path = cypher.substring(cypher.indexOf("(") + 1, indexToUse - 2);
        String returnClause = cypher.substring(cypher.indexOf("return"));
        String cypherPathQuery = "MATCH " + path + ((whereIndex != -1) ? whereClause : "") + " " + returnClause;

        DecodedQuery dQMainPath;
        try {
            dQMainPath = CypherTokenizer.decode(cypherPathQuery, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        C2SMain.currentDQ = dQMainPath;
        StringBuilder sql = new StringBuilder();
        ShortestPath sp = new ShortestPath();
        return sp.translate(sql, dQMainPath).toString();
    }
}
