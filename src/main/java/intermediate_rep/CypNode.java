package intermediate_rep;

import com.google.gson.JsonObject;

/**
 * Class for storing nodes from a Cypher query, that are present in the MATCH clause.
 */
public class CypNode {
    // where the node is in relation to the whole MATCH clause (starts at 1).
    private int posInClause;

    // the id of the node (if it has one).
    private String id;

    // any labels belonging to the node (separated by a comma if there are multiple labels).
    private String labels;

    // any properties of the node are stored as they are represented in Cypher: JSON object.
    private JsonObject props;

    /**
     * Constructor for recording information about a Cypher node.
     * Example match clause: MATCH (a:Global:Meta)--(b {node_id : 875})--(c:Process)-->(d) ...
     *
     * @param posInClause The position in the Cypher match clause where the node belongs. In the example above,
     *                    the node with id 'b' would be in position 2, and the node with id 'd' would be in position 4.
     * @param id          The id of the node: in the example above, these would be a, b, c, and d.
     * @param labels      The label(s) of the node: in the example above, the labels for the node with id 'a' would
     *                    be 'global, meta'.
     * @param props       If the node has any properties, they are stored in this field. In the example above,
     *                    the node with id 'b' would have the JSON object {node_id : 875} stored in this field.
     */
    public CypNode(int posInClause, String id, String labels, JsonObject props) {
        this.posInClause = posInClause;
        this.id = id;
        this.labels = labels;
        this.props = props;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return labels;
    }

    public JsonObject getProps() {
        return props;
    }

    public void setProps(JsonObject newProps) {
        this.props = newProps;
    }

    @Override
    public String toString() {
        return "(ID: " + this.id + ", LABELS: " + this.labels + ", PROPS: "
                + this.props + ", POS: " + this.posInClause + ")";
    }

    public int getPosInClause() {
        return posInClause;
    }
}
