package intermediate_rep;

import java.util.List;

/**
 * Stores the intermediate representation of the original Cypher RETURN clause.
 */
public class ReturnClause {
    // the individual components of the RETURN clause.
    private List<CypReturn> items;

    // returns a list of the individual components.
    public List<CypReturn> getItems() {
        return items;
    }

    // sets the individual components of the RETURN clause.
    public void setItems(List<CypReturn> items) {
        this.items = items;
    }

    /**
     * Prints out information about the individual items in the RETURN clause.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("ITEMS IN RETURN CLAUSE:\n");
        for (CypReturn cr : this.items) {
            strb.append(cr.toString()).append("\n");
        }
        strb.setLength(strb.length() - 1);
        strb.append("\n");
        return strb.toString();
    }
}
