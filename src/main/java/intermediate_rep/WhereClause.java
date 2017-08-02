package intermediate_rep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores the where clause.
 */
public class WhereClause {
    private ArrayList<String> components;
    private Map<String, String> whereMappings = new HashMap<>();
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
