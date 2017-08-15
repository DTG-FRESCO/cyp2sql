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
