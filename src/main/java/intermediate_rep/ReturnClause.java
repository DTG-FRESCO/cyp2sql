package intermediate_rep;

import java.util.List;

/**
 * Stores the return clause.
 */
public class ReturnClause {
    private List<CypReturn> items;

    public List<CypReturn> getItems() {
        return items;
    }

    public void setItems(List<CypReturn> items) {
        this.items = items;
    }

    /**
     * Prints out information about the individual items in the match clause.
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
