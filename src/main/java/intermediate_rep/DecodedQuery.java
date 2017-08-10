package intermediate_rep;

import translator.CypherWalker;

/**
 * This class definition bundles together all of the intermediate representations
 * built up by the tool, as well as storing the SQL translation for completeness.
 */
public class DecodedQuery {
    private MatchClause matchC;
    private ReturnClause returnC;
    private OrderClause orderC;
    private CypForEach forEachC;
    private int skipAmount;
    private int limitAmount;
    private CypherWalker cypherAdditionalInfo;
    // The SQL translation is also stored in this object for completeness.
    private String sqlEquiv;

    /**
     * Create the DecodedQuery object based on the parsing of the Cypher input.
     *  @param m     MatchClause object.
     * @param r     ReturnClause object.
     * @param o     OrderClause object.
     * @param skip  SKIP value.
     * @param limit LIMIT value.
     * @param c     The CypherWalker object.
     */
    public DecodedQuery(MatchClause m, ReturnClause r, OrderClause o,
                        int skip, int limit, CypherWalker c) {
        this.matchC = m;
        this.returnC = r;
        this.orderC = o;
        this.skipAmount = skip;
        this.limitAmount = limit;
        this.cypherAdditionalInfo = c;
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
}
