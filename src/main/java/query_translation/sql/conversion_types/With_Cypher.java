/*
 * Copyright (c) 2017.
 *
 * Oliver Crawford <o.crawford@hotmail.co.uk>
 * Lucian Carata <lc525@cam.ac.uk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package query_translation.sql.conversion_types;

import exceptions.DQInvalidException;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;
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
    public String convertQuery(String cypher, C2SProperties props) {
        int posOfMatch = cypher.toLowerCase().lastIndexOf("match");
        int posOfWhere = cypher.toLowerCase().indexOf("where");
        int posOfOrderBy = cypher.toLowerCase().indexOf("order by");

        // from the tokens in the original Cypher query, decide on the most appropriate method for translation.
        try {
            if (posOfMatch != -1 && posOfMatch != 0) return withMatch(cypher, props);
            if (posOfOrderBy == -1 || (posOfWhere != -1 && (posOfWhere < posOfOrderBy)))
                return withWhere(cypher, props);
            else return withOB(cypher, props);
        } catch (DQInvalidException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WITH ... MATCH ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @param props
     * @return SQL equivalent of the Cypher input.
     */
    private String withMatch(String cypher, C2SProperties props) throws DQInvalidException {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String firstWith = changeLine.toLowerCase()
                .substring(0, cypher.toLowerCase().lastIndexOf("match") + 1) + ";";
        DecodedQuery dqFirstWith = genDQAndSQL(firstWith, props);

        String withTemp = null;
        if (dqFirstWith != null) {
            withTemp = WithSQL.genTemp(dqFirstWith.getSqlEquiv(), 0, dqFirstWith);
        }

        String secondWith = cypher.toLowerCase().substring(cypher.toLowerCase().lastIndexOf("match"));
        String sqlSelect = WithSQL.createSelectMatch(secondWith, dqFirstWith, props);

        return withTemp + " " + sqlSelect;
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WITH ... ORDER BY ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @param props
     * @return SQL equivalent of the Cypher input.
     */
    private String withOB(String cypher, C2SProperties props) throws DQInvalidException {
        int posOfReturn = cypher.toLowerCase().indexOf("return");
        String firstWith = cypher.toLowerCase().replace("with", "return");
        firstWith = firstWith.substring(0, posOfReturn + 1) + ";";
        DecodedQuery dQ = genDQAndSQL(firstWith, props);

        String withTemp = null;
        if (dQ != null) {
            withTemp = WithSQL.genTemp(dQ.getSqlEquiv(), 0, dQ);
        }

        String indexName = dQ.getMc().getNodes().get(0).getId();
        String finalPart = "match (" + indexName + ") " +
                cypher.toLowerCase().substring(posOfReturn, cypher.length());
        DecodedQuery decQFinal = genDQAndSQL(finalPart, props);
        String sqlSelect = WithSQL.createSelectOB(decQFinal);
        return withTemp + " " + sqlSelect;
    }

    /**
     * The current method accepts queries of the following type ONLY:
     * MATCH ... WHERE ... WITH ... MATCH ... WHERE ... RETURN ...
     *
     * @param cypher Original Cypher input.
     * @param props
     * @return SQL equivalent of the Cypher input.
     */
    private String withWhere(String cypher, C2SProperties props) throws DQInvalidException {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String[] withParts = changeLine.toLowerCase().split(" where ");
        DecodedQuery dQ = genDQAndSQL(withParts[0] + ";", props);

        String withTemp = null;
        if (dQ != null) {
            withTemp = WithSQL.genTemp(dQ.getSqlEquiv(), 0, dQ);
        }

        String sqlSelect = WithSQL.createSelectWhere(withParts[1].trim(), dQ);
        return withTemp + " " + sqlSelect;
    }

    @Override
    public String convertToSQL(DecodedQuery dQ, C2SProperties props) {
        return null;
    }

    @Override
    public DecodedQuery generateDQ(String cypher, C2SProperties props) {
        return null;
    }
}
