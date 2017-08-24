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

package intermediate_rep;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storing information about the FOREACH section of a Cypher query.
 */
public class CypForEach {
    // updateMap consists of the property to be updated, and the value it is to be updated with.
    private Map<String, String> updateMap;

    /**
     * Constructor for the object.
     *
     * @param forEachClause FOREACH part of a Cypher query.
     */
    public CypForEach(String forEachClause) {
        String forEachPart = forEachClause.split(" \\| ")[1];
        String[] kv = forEachPart.split(" = ");

        // extract the key value pair.
        kv[0] = kv[0].split("\\.")[1];
        kv[1] = kv[1].substring(1, kv[1].length() - 3);
        Map<String, String> upMap = new HashMap<>();
        upMap.put(kv[0], kv[1]);
        updateMap = upMap;
    }

    /**
     * Retrieve the mapping specified in the FOREACH part of the Cypher query.
     *
     * @return Mapping coded in FOREACH part of Cypher query.
     */
    public Map<String, String> getUpdateMap() {
        return updateMap;
    }
}
