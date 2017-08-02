package intermediate_rep;

import com.google.gson.JsonObject;

/**
 * Class for storing nodes from a Cypher query, that are present in the MATCH clause.
 */
public class CypNode {
    // where the node is in relation to the whole MATCH clause (starts at 1).
    private int posInClause;

    // the id of the node if it has one.
    private String id;

    // any labels belonging to the node (separated by a comma if there are multiple labels).
    private String labels;

    // any properties of the node are stored as they are represented in Cypher: JSON object.
    private JsonObject props;

    public CypNode(int posInClause, String id, String type, JsonObject props) {
        this.posInClause = posInClause;
        this.id = id;
        this.labels = type;
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
