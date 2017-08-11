package intermediate_rep;

/**
 * Class for storing the order by clause of a cypher query (if there is one).
 */
public class CypOrder {
    private String nodeID;
    private String field;
    private String ascOrDesc;

    /**
     * Constructor for recording information about the ORDER BY clause of Cypher (if indeed there is one).
     * Example order by clause: ... ORDER BY n.node_id ASC ...
     *
     * @param id        The id of the node/relationship that the ordering applies to:
     *                  in the example above, this is 'n'.
     * @param field     The field of the node/relationship that the ordering applies to:
     *                  in the example above, this is 'node_id'.
     * @param ascOrDesc The type of ordering being recorded. This is either 'asc' or 'desc'. In the case
     *                  where no ordering is explicitly mentioned in the query, then the default ordering,
     *                  'asc', is recorded instead.
     */
    public CypOrder(String id, String field, String ascOrDesc) {
        this.nodeID = id;
        this.field = field;
        this.ascOrDesc = ascOrDesc;
    }

    public String getNodeId() {
        return nodeID;
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
