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
import query_translation.sql.utilities_sql.ShortestPath;
import translator.CypherTokenizer;

/**
 * Class for dealing with the shortestPath function in Cypher.
 */
public class SP_Cypher extends AbstractConversion {
    @Override
    public String convertToSQL(DecodedQuery dQ, C2SProperties props) {
        ShortestPath sp = new ShortestPath();
        return sp.translate(new StringBuilder(), dQ, props).toString();
    }

    @Override
    public DecodedQuery generateDQ(String cypher, C2SProperties props) throws DQInvalidException {
        cypher = cypher.toLowerCase();
        int returnIndex = cypher.indexOf("return");
        if (returnIndex == -1)
            throw new DQInvalidException("Cypher input has no RETURN clause --> malformed.");
        int whereIndex = cypher.indexOf("where");

        String whereClause = null;

        if (whereIndex != -1) {
            whereClause = cypher.substring(whereIndex, returnIndex - 1);
        }

        int indexToUse = (whereIndex == -1) ? returnIndex : whereIndex;

        String path = cypher.substring(cypher.indexOf("(") + 1, indexToUse - 2);
        String returnClause = cypher.substring(cypher.indexOf("return"));
        String cypherPathQuery = "MATCH " + path + ((whereIndex != -1) ? whereClause : "") + " " + returnClause;

        DecodedQuery dQ = CypherTokenizer.decode(cypherPathQuery, false);

        if (dQ == null) throw new DQInvalidException("Could not convert Cypher input...");
        return dQ;
    }
}
