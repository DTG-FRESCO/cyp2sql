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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import intermediate_rep.CypRel;
import intermediate_rep.MatchClause;

import java.util.Map;
import java.util.Set;

/**
 * Class of helpful utilities for translation classes that edit the database (such as inserting new data).
 */
class InsertUtils {
    /**
     * The relations stored on the relational database for nodes with multiple labels have the following format:
     * global_local_otherLabel
     * <p>
     * However, they are stored in the nodes relation as a comma separated string:
     * global, local, otherLabel
     * <p>
     * This method translates from the latter format to the former.
     *
     * @param matchClause MatchClause object being interrogated.
     * @param index       The index of the node within the matchClause object for which the method will work on.
     * @return A string of the label(s) of the node, separated by an '_' if there are multiple labels.
     */
    static String findRelation(MatchClause matchClause, int index) {
        String labels = matchClause.getNodes().get(index).getType();
        labels = labels.replace(", ", "_");
        return labels;
    }

    static String[] findColsAndValues(MatchClause matchClause, int index) {
        JsonObject obj = matchClause.getNodes().get(index).getProps();
        return extractFromObject(obj);
    }

    static String[] findColsAndValuesRels(CypRel r) {
        JsonObject obj = r.getProps();
        return extractFromObject(obj);
    }

    private static String[] extractFromObject(JsonObject obj) {
        if (obj != null) {
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            StringBuilder cols = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Map.Entry<String, JsonElement> entry : entries) {
                cols.append(entry.getKey()).append(", ");
                values.append("'").append(entry.getValue().getAsString()).append("', ");
            }

            cols = new StringBuilder(cols.substring(0, cols.length() - 2));
            values = new StringBuilder(values.substring(0, values.length() - 2));
            return new String[]{cols.toString(), values.toString()};
        } else {
            return new String[]{"", ""};
        }
    }
}
