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

import java.util.ArrayList;

/**
 * Class for creating correct SQL query when UNION/UNION ALL keyword is present
 * in the original Cypher query.
 */
public class UnionSQL {
    /**
     * Generate the union of two or more queries.
     *
     * @param queries   Individual SQL queries.
     * @param unionType Discrete: either UNION or UNION ALL.
     * @return Complete SQL query with UNION/UNION ALL added.
     */
    public static String genUnion(ArrayList<String> queries, String unionType) {
        StringBuilder unionSQL = new StringBuilder();
        boolean insertCloseParenth = false;
        for (String s : queries) {
            unionSQL.append(s.substring(0, s.length() - 1));
            if (insertCloseParenth) unionSQL.append(")");
            unionSQL.append(" ").append(unionType).append("( ");
            insertCloseParenth = true;
        }
        unionSQL.setLength(unionSQL.length() - (2 + unionType.length()));
        unionSQL.append(";");
        return unionSQL.toString();
    }
}
