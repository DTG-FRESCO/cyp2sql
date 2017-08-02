package query_translation.sql.utilities_sql;

import intermediate_rep.CypRel;
import intermediate_rep.MatchClause;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.Set;

class InsertUtils {
    static String findRelation(MatchClause createC, int index) {
        String labels = createC.getNodes().get(index).getType();
        labels = labels.replace(", ", "_");
        return labels;
    }

    static String[] findColsAndValues(MatchClause createC, int index) {
        JsonObject obj = createC.getNodes().get(index).getProps();
        return extractFromObject(obj);
    }

    static String[] findColsAndValuesRels(CypRel r) {
        JsonObject obj = r.getProps();
        return extractFromObject(obj);
    }

    private static String[] extractFromObject(JsonObject obj) {
        if (obj != null) {
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            String cols = "";
            String values = "";

            for (Map.Entry<String, JsonElement> entry : entries) {
                cols = cols + entry.getKey() + ", ";
                values = values + "'" + entry.getValue().getAsString() + "', ";
            }

            cols = cols.substring(0, cols.length() - 2);
            values = values.substring(0, values.length() - 2);
            return new String[]{cols, values};
        } else {
            return new String[]{"", ""};
        }
    }
}
