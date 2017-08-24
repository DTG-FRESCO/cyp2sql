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
 * Stores the intermediate representation of the original Cypher ORDER BY clause.
 */
public class OrderClause {
    // the individual components of the ORDER BY clause.
    private List<CypOrder> items;

    // returns a list of the individual components.
    public List<CypOrder> getItems() {
        return items;
    }

    // sets the individual components of the ORDER BY clause.
    public void setItems(List<CypOrder> items) {
        this.items = items;
    }

    /**
     * Prints out information about the ORDER BY clause.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("ITEMS IN ORDER BY CLAUSE:\n");
        for (CypOrder co : this.items) {
            strb.append(co.toString()).append("\n");
        }
        strb.setLength(strb.length() - 1);
        strb.append("\n");
        return strb.toString();
    }
}
