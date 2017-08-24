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

/**
 * Class for storing the order by clause of a cypher query (if there is one).
 */
public class CypOrder {
    private String idOrder;
    private String field;
    private String ascOrDesc;

    /**
     * Constructor for recording information about the ORDER BY clause of Cypher (if indeed there is one).
     * Example order by clause: ... ORDER BY n.node_id ASC ...
     *
     * @param id        The id of the node/relationship that the ordering applies to:
     *                  in the example above, this is 'n'.
     * @param field     The field of the node/relationship that the ordering applies to:
     *                  in the example above, this is 'node_id'.
     * @param ascOrDesc The type of ordering being recorded. This is either 'asc' or 'desc'. In the case
     *                  where no ordering is explicitly mentioned in the query, then the default ordering,
     *                  'asc', is recorded instead.
     */
    public CypOrder(String id, String field, String ascOrDesc) {
        this.idOrder = id;
        this.field = field;
        this.ascOrDesc = ascOrDesc;
    }

    public String getField() {
        return field;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    @Override
    public String toString() {
        return "(ID: " + this.idOrder + ", FIELD: " + this.field +
                ", ORDER_TYPE: " + this.ascOrDesc + ")";
    }

    public String getID() {
        return idOrder;
    }
}
