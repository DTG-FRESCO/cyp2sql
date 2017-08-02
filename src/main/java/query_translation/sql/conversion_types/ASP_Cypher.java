package query_translation.sql.conversion_types;

import intermediate_rep.DecodedQuery;
import production.C2SMain;
import query_translation.sql.utilities_sql.AllShortestPaths;
import translator.CypherTokenizer;

public class ASP_Cypher extends AbstractConversion {
    @Override
    public String convertQuery(String cypher) {
        String path = cypher.substring(cypher.indexOf("(") + 1, cypher.indexOf("RETURN") - 2);
        String returnClause = cypher.substring(cypher.indexOf("RETURN"));
        String cypherPathQuery = "MATCH " + path + " " + returnClause;
        DecodedQuery dQMainPath;

        try {
            dQMainPath = CypherTokenizer.decode(cypherPathQuery, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        C2SMain.currentDQ = dQMainPath;
        StringBuilder sql = new StringBuilder();
        AllShortestPaths asp = new AllShortestPaths();
        return asp.translate(sql, dQMainPath).toString();
    }
}
