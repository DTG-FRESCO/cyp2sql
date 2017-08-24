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
