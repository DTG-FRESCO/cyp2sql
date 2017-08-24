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
    public String convertToSQL(DecodedQuery dQ, C2SProperties props) {
        ForEach fe = new ForEach();
        return fe.translate(new StringBuilder(), dQ, props).toString();
    }

    @Override
    public DecodedQuery generateDQ(String cypher, C2SProperties props) throws DQInvalidException {
        String changeLine = cypher.toLowerCase().replace("with", "return");
        String[] feParts = changeLine.toLowerCase().split(" foreach ");

        DecodedQuery dQ = genDQAndSQL(feParts[0].trim() + ";", props);

        CypForEach cypForEach = new CypForEach(feParts[1].trim());
        if (dQ != null) {
            dQ.setForEachC(cypForEach);
        } else throw new DQInvalidException("Cypher containing FOREACH keyword is invalid.");

        return dQ;
    }
}
