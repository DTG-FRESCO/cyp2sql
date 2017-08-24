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

import com.google.gson.JsonObject;

/**
 * Class for storing the relationships in a Cypher query.
 */
public class CypRel {
    private int posInClause;
    private String id;
    private String type;
    private JsonObject props;
    private String direction;

    /**
     * Constructor for recording information about a Cypher relationship/edge.
     * Example match clause: MATCH (a){@literal <}-[r1 : REL_TYPE_A]-(b)-[r2 {state:8}]-(c)-[r3]-{@literal >}(d) ...
     *
     * @param posInClause The position in the match clause that the relationship fits into to. In the example,
     *                    the edge with id 'r1' is in position 1, and the edge with id 'r3' is in position 3.
     * @param id          The id of the edge (if it has one): in the example, these are identified by r1, r2, and r3.
     * @param type        The type of the edge being stored: in the example, the first relationship ('r1') has type
     *                    'REL_TYPE_A'.
     * @param props       If the edge holds and properties, then it is stored in this field. The properties does
     *                    not include the type of edge, only the JSON object.
     * @param direction   The direction of the edge: in the example above, all three directions are shown:
     *                    the edge with id 'r1' = 'left'
     *                    the edge with id 'r2' = 'none'
     *                    the edge with id 'r3' = 'right'
     */
    public CypRel(int posInClause, String id, String type, JsonObject props, String direction) {
        this.posInClause = posInClause;
        this.id = id;
        this.type = type;
        this.props = props;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public JsonObject getProps() {
        return props;
    }

    public void setProps(JsonObject o) {
        this.props = o;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "(ID: " + this.id + ", TYPE: " + this.type + ", DIR: " +
                this.direction + ", PROPS: " + this.props + ", POS: " +
                this.posInClause + ")";
    }

    public int getPosInClause() {
        return posInClause;
    }
}
