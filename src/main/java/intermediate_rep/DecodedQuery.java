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
    private WhereClause whereC;
    private CypForEach forEachC;
    private CypIterate iterateC;
    private int skipAmount;
    private int limitAmount;
    private CypherWalker cypherAdditionalInfo;
    private String sqlEquiv;

    public DecodedQuery(MatchClause m, ReturnClause r, OrderClause o,
                        WhereClause wc, int skip, int limit, CypherWalker c) {
        this.matchC = m;
        this.returnC = r;
        this.orderC = o;
        this.whereC = wc;
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

    public WhereClause getWc() {
        return whereC;
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

    public CypIterate getIterateC() {
        return iterateC;
    }

    public void setIterateC(CypIterate iterateC) {
        this.iterateC = iterateC;
    }
}
