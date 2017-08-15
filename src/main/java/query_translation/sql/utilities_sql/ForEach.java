package query_translation.sql.utilities_sql;

import intermediate_rep.CypForEach;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;

/**
 * Method for translating Cypher with FOREACH into SQL.
 */
public class ForEach extends AbstractTranslation {
    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery, C2SProperties props) {
        String oldSelect = decodedQuery.getSqlEquiv();
        CypForEach cfe = decodedQuery.getForEachC();

        int posOfSelect = oldSelect.lastIndexOf("SELECT");
        int posOfFrom = oldSelect.lastIndexOf("FROM");

        String newSelect = "doForEachFunc(array_agg(n01.id), '" +
                cfe.getUpdateMap().keySet().iterator().next() +
                "', '" +
                cfe.getUpdateMap().values().iterator().next() +
                "')";

        sql.append(oldSelect.substring(0, posOfSelect + 7))
                .append(newSelect)
                .append(" ")
                .append(oldSelect.substring(posOfFrom, oldSelect.length()));
        return sql;
    }
}
