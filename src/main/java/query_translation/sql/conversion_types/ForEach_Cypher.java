package query_translation.sql.conversion_types;

import intermediate_rep.CypForEach;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;
import query_translation.sql.utilities_sql.ForEach;

/**
 * Class for dealing with FOREACH keyword in Cypher.
 * <p>
 * This class has not been tested in a while so may be quite broken...
 */
public class ForEach_Cypher extends AbstractConversion {
    @Override
    public String convertQuery(String cypher, C2SProperties props) {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String[] feParts = changeLine.toLowerCase().split(" foreach ");

        DecodedQuery dQ = convertCypherToSQL(feParts[0].trim() + ";", props);

        CypForEach cypForEach = new CypForEach(feParts[1].trim());
        if (dQ != null) {
            dQ.setForEachC(cypForEach);
        }

        StringBuilder sql = new StringBuilder();
        ForEach fe = new ForEach();
        return fe.translate(sql, dQ, props).toString();
    }
}
