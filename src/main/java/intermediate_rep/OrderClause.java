package intermediate_rep;

import java.util.List;

/**
 * Stores the intermediate representation of the original Cypher ORDER BY clause.
 */
public class OrderClause {
    // the individual components of the ORDER BY clause.
    private List<CypOrder> items;

    // returns a list of the individual components.
    public List<CypOrder> getItems() {
        return items;
    }

    // sets the individual components of the ORDER BY clause.
    public void setItems(List<CypOrder> items) {
        this.items = items;
    }

    /**
     * Prints out information about the ORDER BY clause.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("ITEMS IN ORDER BY CLAUSE:\n");
        for (CypOrder co : this.items) {
            strb.append(co.toString()).append("\n");
        }
        strb.setLength(strb.length() - 1);
        strb.append("\n");
        return strb.toString();
    }
}
