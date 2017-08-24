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

import java.util.ArrayList;

/**
 * Stores all the CypNode and CypRel objects, and has an internal ID to keep track of the order.
 */
public class MatchClause {
    // Stores all the nodes in the MATCH clause.
    private ArrayList<CypNode> nodes = new ArrayList<>();
    // Stores all the relationships in the MATCH clause.
    private ArrayList<CypRel> rels = new ArrayList<>();

    // varRel indicates if the relationship is of the type -[*a..b]-.
    private boolean varRel = false;

    private int internalID;

    public MatchClause() {
        internalID = 0;
    }

    public ArrayList<CypNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<CypNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<CypRel> getRels() {
        return rels;
    }

    public void setRels(ArrayList<CypRel> rels) {
        this.rels = rels;
    }

    public int getInternalID() {
        internalID++;
        return internalID;
    }

    public void resetInternalID() {
        internalID = 0;
    }

    public boolean isVarRel() {
        return varRel;
    }

    public void setVarRel() {
        this.varRel = true;
    }


    /**
     * Prints out information about the nodes and relationships in the MATCH clause.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("NODES IN MATCH CLAUSE:\n");
        for (CypNode cn : this.nodes) {
            strb.append(cn.toString()).append("\n");
        }
        strb.append("RELS IN MATCH CLAUSE:\n");
        for (CypRel cr : this.rels) {
            strb.append(cr.toString()).append("\n");
        }
        strb.append("DOES MATCH CLAUSE HAVE VARIABLE LENGTH PATH: ").append(this.isVarRel()).append("\n");
        return strb.toString();
    }
}
