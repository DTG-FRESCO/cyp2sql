package intermediate_rep;

import com.google.gson.JsonObject;

/**
 * Class for storing the relationships in a Cypher query.
 */
public class CypRel {
    private int posInClause;
    private String id;
    private String type;
    private JsonObject props;
    private String direction;

    public CypRel(int posInClause, String id, String type, JsonObject props, String direction) {
        this.posInClause = posInClause;
        this.id = id;
        this.type = type;
        this.props = props;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public JsonObject getProps() {
        return props;
    }

    public void setProps(JsonObject o) {
        this.props = o;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "(ID: " + this.id + ", TYPE: " + this.type + ", DIR: " +
                this.direction + ", PROPS: " + this.props + ", POS: " +
                this.posInClause + ")";
    }

    public int getPosInClause() {
        return posInClause;
    }
}
