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
import intermediate_rep.CypIterate;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;
import query_translation.sql.utilities_sql.IterateSQL;

/**
 * Class for dealing with the ITERATE keyword. NOTE: this is currently not an accepted keyword
 * in Cypher, but was added as an extension of this translator tool to show the possibilities
 * of the program. More details can be found in the documentation.
 */
public class Iterate_Cypher extends AbstractConversion {
    @Override
    public String convertToSQL(DecodedQuery dQ, C2SProperties props) throws DQInvalidException {
        IterateSQL it = new IterateSQL();
        return it.translate(new StringBuilder(), dQ, props).toString();
    }

    @Override
    public DecodedQuery generateDQ(String cypher, C2SProperties props) {
        CypIterate cypIter = new CypIterate(cypher);

        String line = cypIter.getOriginalCypherInput();
        String matchClause = line.substring(8, line.indexOf("LOOP"));

        line = line.split(" LOOP ")[1];
        cypIter.setLoopIndexTo(line.split(" ")[0]);
        line = line.split(" ON ")[1];
        cypIter.setLoopIndexFrom(line.split(" ")[0]);
        line = line.split(" COLLECT ")[1];
        cypIter.setCollectIndex(line.split(" ")[0]);
        line = line.split(" RETURN ")[1];
        cypIter.setReturnStatement(line);

        String firstStep = matchClause + "return " + cypIter.getLoopIndexTo() + ";";
        cypIter.setFirstQuery(firstStep);

        return new DecodedQuery(null, null, null, -1, -1, null, cypIter);
    }
}
