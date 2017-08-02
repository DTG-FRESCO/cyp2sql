package intermediate_rep;

/**
 * Class for storing the order by clause of a cypher query (if there is one).
 */
public class CypOrder {
    private String nodeID;
    private String field;
    private String ascOrDesc;

    public CypOrder(String id, String f, String ascOrDesc) {
        this.nodeID = id;
        this.field = f;
        this.ascOrDesc = ascOrDesc;
    }

    public String getField() {
        return field;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    @Override
    public String toString() {
        return "(ID: " + this.nodeID + ", FIELD: " + this.field +
                ", ORDER_TYPE: " + this.ascOrDesc + ")";
    }
}
