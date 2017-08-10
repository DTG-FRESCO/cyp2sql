package intermediate_rep;

public class CypWhere {
    private int posInWhere;
    private String bracketing;
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
