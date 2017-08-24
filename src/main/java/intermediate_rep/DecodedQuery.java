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

import translator.CypherWalker;

import java.util.ArrayList;

/**
 * This class definition bundles together all of the intermediate representations
 * built up by the tool, as well as storing the SQL translation for completeness.
 */
public class DecodedQuery {
    private MatchClause matchC;
    private ReturnClause returnC;
    private OrderClause orderC;
    private CypForEach forEachC;
    private CypIterate iterate;
    private int skipAmount;
    private int limitAmount;
    private CypherWalker cypherAdditionalInfo;
    // The SQL translation is also stored in this object for completeness.
    private String sqlEquiv;
    private ArrayList<String> withParts = new ArrayList<>();
    private ArrayList<DecodedQuery> unionParts = new ArrayList<>();

    /**
     * Create the DecodedQuery object based on the parsing of the Cypher input.
     *
     * @param m     MatchClause object.
     * @param r     ReturnClause object.
     * @param o     OrderClause object.
     * @param skip  SKIP value.
     * @param limit LIMIT value.
     * @param c     The CypherWalker object.
     */
    public DecodedQuery(MatchClause m, ReturnClause r, OrderClause o, int skip, int limit, CypherWalker c,
                        CypIterate it) {
        this.matchC = m;
        this.returnC = r;
        this.orderC = o;
        this.skipAmount = skip;
        this.limitAmount = limit;
        this.cypherAdditionalInfo = c;
        this.iterate = it;
    }

    public DecodedQuery() {

    }

    public MatchClause getMc() {
        return matchC;
    }

    public ReturnClause getRc() {
        return returnC;
    }

    public OrderClause getOc() {
        return orderC;
    }

    public int getSkipAmount() {
        return skipAmount;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public CypherWalker getCypherAdditionalInfo() {
        return cypherAdditionalInfo;
    }

    public String getSqlEquiv() {
        return sqlEquiv;
    }

    public void setSqlEquiv(String sqlEquiv) {
        this.sqlEquiv = sqlEquiv;
    }

    public CypForEach getForEachC() {
        return forEachC;
    }

    public void setForEachC(CypForEach forEachC) {
        this.forEachC = forEachC;
    }

    public CypIterate getIterate() {
        return iterate;
    }

    public ArrayList<String> getWithParts() {
        return withParts;
    }

    public void setWithParts(ArrayList<String> withParts) {
        this.withParts = withParts;
    }

    public void addToUnionParts(DecodedQuery unionDQ) {
        unionParts.add(unionDQ);
    }

    public ArrayList<DecodedQuery> getUnionParts() {
        return unionParts;
    }
}
