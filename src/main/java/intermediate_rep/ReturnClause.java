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

import java.util.List;

/**
 * Stores the intermediate representation of the original Cypher RETURN clause.
 */
public class ReturnClause {
    // the individual components of the RETURN clause.
    private List<CypReturn> items;

    // returns a list of the individual components.
    public List<CypReturn> getItems() {
        return items;
    }

    // sets the individual components of the RETURN clause.
    public void setItems(List<CypReturn> items) {
        this.items = items;
    }

    /**
     * Prints out information about the individual items in the RETURN clause.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("ITEMS IN RETURN CLAUSE:\n");
        for (CypReturn cr : this.items) {
            strb.append(cr.toString()).append("\n");
        }
        strb.setLength(strb.length() - 1);
        strb.append("\n");
        return strb.toString();
    }
}
