package intermediate_rep;

import java.util.List;

/**
 * Stores the order clause.
 */
public class OrderClause {
    private List<CypOrder> items;

    public List<CypOrder> getItems() {
        return items;
    }

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
