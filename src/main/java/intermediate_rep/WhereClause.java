package intermediate_rep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores the where clause.
 */
public class WhereClause {
    // The individual predicates of the WHERE clause are stored in this field.
    private ArrayList<String> components;
    // whereMappings holds a mapping between an individual item in the WHERE clause, and the boolean
    // operator that follows it (currently the tool handles only AND and OR, not yet built for XOR).
    private Map<String, String> whereMappings = new HashMap<>();
    // The whole WHERE part of the Cypher input.
    private String clause;

    public WhereClause(String c) {
        this.clause = c;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> co) {
        this.components = co;
    }

    public Map<String, String> getWhereMappings() {
        return whereMappings;
    }

    public void setWhereMappings(Map<String, String> map) {
        this.whereMappings = map;
    }

    /**
     * Prints out the individual components of the WHERE part of the Cypher input.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("WHERE CLAUSE COMPONENTS:\n");
        for (String s : components) {
            strb.append(s).append("\n");
        }
        return strb.toString();
    }
}
