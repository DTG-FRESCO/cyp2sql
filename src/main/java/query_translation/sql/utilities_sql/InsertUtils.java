package query_translation.sql.utilities_sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import intermediate_rep.CypRel;
import intermediate_rep.MatchClause;

import java.util.Map;
import java.util.Set;

/**
 * Class of helpful utilities for translation classes that edit the database (such as inserting new data).
 */
class InsertUtils {
    /**
     * The relations stored on the relational database for nodes with multiple labels have the following format:
     * global_local_otherLabel
     * <p>
     * However, they are stored in the nodes relation as a comma separated string:
     * global, local, otherLabel
     * <p>
     * This method translates from the latter format to the former.
     *
     * @param matchClause MatchClause object being interrogated.
     * @param index       The index of the node within the matchClause object for which the method will work on.
     * @return A string of the label(s) of the node, separated by an '_' if there are multiple labels.
     */
    static String findRelation(MatchClause matchClause, int index) {
        String labels = matchClause.getNodes().get(index).getType();
        labels = labels.replace(", ", "_");
        return labels;
    }

    static String[] findColsAndValues(MatchClause matchClause, int index) {
        JsonObject obj = matchClause.getNodes().get(index).getProps();
        return extractFromObject(obj);
    }

    static String[] findColsAndValuesRels(CypRel r) {
        JsonObject obj = r.getProps();
        return extractFromObject(obj);
    }

    private static String[] extractFromObject(JsonObject obj) {
        if (obj != null) {
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            StringBuilder cols = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Map.Entry<String, JsonElement> entry : entries) {
                cols.append(entry.getKey()).append(", ");
                values.append("'").append(entry.getValue().getAsString()).append("', ");
            }

            cols = new StringBuilder(cols.substring(0, cols.length() - 2));
            values = new StringBuilder(values.substring(0, values.length() - 2));
            return new String[]{cols.toString(), values.toString()};
        } else {
            return new String[]{"", ""};
        }
    }
}
