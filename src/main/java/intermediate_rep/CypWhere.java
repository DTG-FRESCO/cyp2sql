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
 * Stores information linking to an individual predicate contained within a WHERE clause. For example, information
 * about a statement a.name = 'Ben' would be stored in one CypWhere object.
 */
public class CypWhere {
    // position in the original Cypher WHERE clause of the component.
    private int posInWhere;
    // stores string of brackets either before or after component.
    private String bracketing;
    // stores boolean operator that immediately follows this component (will be either and, or, NULL).
    private String boolOp;

    public CypWhere(int id) {
        this.posInWhere = id;
    }

    public int getPosInWhere() {
        return posInWhere;
    }

    public String getBracketing() {
        if (this.bracketing == null) return "null";
        else return this.bracketing;
    }

    public String getBoolOp() {
        return ((this.boolOp != null) ? this.boolOp : "null");
    }

    public void setBoolOp(String boolOp) {
        this.boolOp = boolOp;
    }

    public void addLBrack() {
        if (this.bracketing == null) this.bracketing = "(";
        else this.bracketing = this.bracketing + "(";
    }

    public void addRBrack() {
        if (this.bracketing == null) this.bracketing = ")";
        else this.bracketing = this.bracketing + ")";
    }

    @Override
    public String toString() {
        return "POS: " + this.posInWhere + "; BRACKETING: " + this.getBracketing() + "; BOOL_OP: " + this.getBoolOp();
    }
}
