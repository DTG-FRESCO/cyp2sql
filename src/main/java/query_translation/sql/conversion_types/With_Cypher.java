package query_translation.sql.conversion_types;

import intermediate_rep.DecodedQuery;
import query_translation.sql.utilities_sql.WithSQL;

/**
 * Class for dealing with the WITH keyword in Cypher.
 */
public class With_Cypher extends AbstractConversion {
    /**
     * Method for converting Cypher queries containing the WITH keyword.
     *
     * @param cypher Original Cypher input containing the WITH keyword.
     * @return SQL string equivalent of the original Cypher input.
     */
    @Override
    public String convertQuery(String cypher) {
        int posOfMatch = cypher.toLowerCase().indexOf("match");
        int posOfWhere = cypher.toLowerCase().indexOf("where");
        int posOfOrderBy = cypher.toLowerCase().indexOf("order by");

        // from the tokens in the original Cypher query, decide on the most appropriate method for translation.
        if (posOfMatch != -1) return withMatch(cypher);
        if (posOfOrderBy == -1 || (posOfWhere != -1 && (posOfWhere < posOfOrderBy))) return withWhere(cypher);
        else return withOB(cypher);
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WITH ... MATCH ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @return SQL equivalent of the Cypher input.
     */
    private String withMatch(String cypher) {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String firstWith = changeLine.toLowerCase()
                .substring(0, cypher.toLowerCase().lastIndexOf("match") + 1) + ";";
        DecodedQuery dqFirstWith = convertCypherToSQL(firstWith);

        String withTemp = null;
        if (dqFirstWith != null) {
            withTemp = WithSQL.genTemp(dqFirstWith.getSqlEquiv());
        }

        String secondWith = cypher.toLowerCase().substring(cypher.toLowerCase().lastIndexOf("match"));
        String sqlSelect = WithSQL.createSelectMatch(secondWith, dqFirstWith);
        return withTemp + " " + sqlSelect;
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WITH ... ORDER BY ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @return SQL equivalent of the Cypher input.
     */
    private String withOB(String cypher) {
        int posOfReturn = cypher.toLowerCase().indexOf("return");
        String firstWith = cypher.toLowerCase().replace("with", "return");
        firstWith = firstWith.substring(0, posOfReturn + 1) + ";";
        DecodedQuery dQ = convertCypherToSQL(firstWith);

        String withTemp = null;
        if (dQ != null) {
            withTemp = WithSQL.genTemp(dQ.getSqlEquiv());
        }

        String indexName = dQ.getMc().getNodes().get(0).getId();
        String finalPart = "match (" + indexName + ") " +
                cypher.toLowerCase().substring(posOfReturn, cypher.length());
        DecodedQuery decQFinal = convertCypherToSQL(finalPart);
        String sqlSelect = WithSQL.createSelectOB(decQFinal);
        return withTemp + " " + sqlSelect;
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WHERE ... WITH ... MATCH ... WHERE ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @return SQL equivalent of the Cypher input.
     */
    private String withWhere(String cypher) {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String[] withParts = changeLine.toLowerCase().split(" where ");
        DecodedQuery dQ = convertCypherToSQL(withParts[0] + ";");

        String withTemp = null;
        if (dQ != null) {
            withTemp = WithSQL.genTemp(dQ.getSqlEquiv());
        }

        String sqlSelect = WithSQL.createSelectWhere(withParts[1].trim(), dQ);
        return withTemp + " " + sqlSelect;
    }
}
