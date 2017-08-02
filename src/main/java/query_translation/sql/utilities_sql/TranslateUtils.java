package query_translation.sql.utilities_sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import intermediate_rep.*;
import production.C2SMain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * Class with useful translation methods that can be used regardless of the type of Cypher query
 * actually being translated.
 */
class TranslateUtils {
    private static StringBuilder genWhere(StringBuilder sql, JsonObject obj, WhereClause wc, String sqlLabel) {
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();

        // default boolean condition to add.
        String boolToAppend = "and";

        for (Map.Entry<String, JsonElement> entry : entries) {
            sql.append(sqlLabel).append(".").append(entry.getKey());
            String value;
            if (entry.getValue().isJsonArray()) {
                value = "ARRAY" + entry.getValue().getAsJsonArray().toString();
            } else if (entry.getKey().equals("name")) {
                value = "ARRAY[" + entry.getValue().toString() + "]";
            } else value = entry.getValue().getAsString();
            sql = TranslateUtils.addWhereClause(sql, value);

            String i = null;
            int index = 0;
            int j = 0;
            boolean findBoolToAppend = false;
            if (wc != null) {
                for (String x : wc.getComponents()) {
                    if (x.contains(entry.getKey())) {
                        findBoolToAppend = true;
                        i = x;
                        index = j;
                    }
                    j++;
                }
                if (findBoolToAppend)
                    boolToAppend = (index < wc.getWhereMappings().size()) ? wc.getWhereMappings().get(i) : "and";
            }

            sql.append(" ").append(boolToAppend).append(" ");
        }
        sql.setLength(sql.length() - (boolToAppend.length() + 1));
        return sql;
    }

    static StringBuilder getWholeWhereClause(StringBuilder sql, CypNode cN, WhereClause wc, String sqlLabel) {
        JsonObject obj = cN.getProps();
        return genWhere(sql, obj, wc, sqlLabel);
    }

    /**
     * If no label provided as an argument, method just adds the default label which is just 'n' (for node), and
     * then calls the method getWholeWhereClause.
     *
     * @param sql Original SQL statement.
     * @param cN  CypNode with properties.
     * @param wc  Where Clause of Cypher query with additional information about the WHERE clause.
     * @return New SQL with WHERE part added.
     */
    static StringBuilder getWholeWhereClause(StringBuilder sql, CypNode cN, WhereClause wc) {
        return getWholeWhereClause(sql, cN, wc, "n01");
    }

    static StringBuilder getWholeWhereClauseRel(StringBuilder sql, CypRel cR, WhereClause wc, String sqlLabel) {
        JsonObject obj = cR.getProps();
        return genWhere(sql, obj, wc, sqlLabel);
    }

    /**
     * Formats the WHERE part of the SQL query, depending on the boolean operator
     * in the original Cypher WHERE clause.
     *
     * @param sql
     * @return
     */
    private static StringBuilder addWhereClause(StringBuilder sql, String value) {
        // format part of the where clause correctly for further parsing.
        if (!value.contains("#")) value = "eq#" + value + "#qe";

        String prop = sql.toString().substring(sql.toString().lastIndexOf(" ") + 1);

        sql = getProperWhereValue(value, sql);

        while (value.contains("~")) {
            sql.append(value.split("~")[1]).append(" ").append(prop);
            String[] valueSplit = value.split("~");
            value = "";
            for (int i = 2; i < valueSplit.length; i++) {
                value += valueSplit[i] + "~";
            }
            value = value.substring(0, value.length() - 1);
            sql = getProperWhereValue(value, sql);
        }

        return sql;
    }

    private static StringBuilder getProperWhereValue(String value, StringBuilder sql) {
        // remove anything to do with ARRAYS from the value
        boolean array = value.startsWith("ARRAY[");
        if (array) value = value.substring(7, value.length() - 2);
        else if (value.contains("ARRAY[")) {
            array = true;
            value = value.replace("ARRAY[", "").replace("]#", "#");
        }

        String v = "";

        if (value.startsWith("eq#")) {
            sql.append(" = ");
            v = value.substring(3, value.indexOf("#qe"));
        } else if (value.startsWith("ne#")) {
            sql.append(" <> ");
            v = value.substring(3, value.indexOf("#en"));
        } else if (value.startsWith("lt#")) {
            sql.append(" < ");
            v = value.substring(3, value.indexOf("#tl"));
        } else if (value.startsWith("gt#")) {
            sql.append(" > ");
            v = value.substring(3, value.indexOf("#tg"));
        } else if (value.startsWith("le#")) {
            sql.append(" <= ");
            v = value.substring(3, value.indexOf("#el"));
        } else if (value.startsWith("ge#")) {
            sql.append(" >= ");
            v = value.substring(3, value.indexOf("#eg"));
        } else if (value.startsWith("ex#")) {
            sql.append(" IS NOT NULL ");
            return sql;
        }

        if (array) {
            sql.append("ARRAY[").append(v.replace("\"", "'")).append("] ");
        } else if (v.equals("ANY($1)")) sql.append(v).append(" ");
        else sql.append("'").append(v.replace("'", "")).append("' ");

        return sql;
    }

    /**
     * Labels in the current schema conversion are stored as strings. Some nodes have multiple labels.
     * Thus, need to query labels using string comparison, and this methods helps generate that.
     *
     * @param cN CypNode with labels attached to it.
     * @return SQL Like statement (such as n.label LIKE '%person$' AND n.label LIKE '%actor%')
     */
    static String genLabelLike(CypNode cN, String id) {
        String label = cN.getType();
        String stmt = "'%";
        String[] labels = label.split(", ");
        for (String l : labels) {
            stmt = stmt + l + "%' AND " + (id == null ? "" : (id + ".")) + "label LIKE '%";
        }
        //stmt = stmt.substring(0, stmt.length() - (18 + (id == null ? 0 : 1)));
        stmt = stmt.substring(0, stmt.substring(0, stmt.length() - 2).lastIndexOf("'") + 1);
        return stmt;
    }

    static String getLabelType(String type) {
        if (type == null) return "nodes";

        // nodes is the default table with all the data inside it.
        String correctTable = "nodes";

        // integer count to indicate whether or not label type specific enough for this optimisation.
        FileInputStream fis = null;
        BufferedReader br = null;
        int changed = 0;

        try {
            fis = new FileInputStream(C2SMain.props.getWspace() + "/meta_labelNames.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(type)) {
                    correctTable = line;
                    changed++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    fis.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (changed > 1) correctTable = "nodes";
        return correctTable;
    }

    static String getTable(ReturnClause rc) {
        boolean possibleOpti = true;
        String table = "nodes";

        for (CypReturn cR : rc.getItems()) {
            if (!C2SMain.labelProps.containsKey(cR.getField())) {
                possibleOpti = false;
                break;
            } else {
                String newTable = C2SMain.labelProps.get(cR.getField());
                if (!table.equals(newTable) && !table.equals("nodes")) {
                    possibleOpti = false;
                    break;
                }
                table = newTable;
            }
        }

        if (!possibleOpti) table = "nodes";

        return table;
    }

    static String addToRelsNeeded(String relsNeeded, String idRel) {
        if (relsNeeded.contains(idRel)) return relsNeeded;
        else return idRel + ", " + relsNeeded;
    }

    static String useAlias(String nodeID, String field, Map<String, String> alias) {
        if (alias.isEmpty()) {
            return "";
        } else {
            for (String s : alias.keySet()) {
                String key = s.split(" AS ")[0];
                if (key.startsWith("collect")) key = key.substring(8, key.length() - 1);
                if (field != null) {
                    if (key.equals((nodeID) + "." + (field))) {
                        return (" AS " + alias.get(s));
                    }
                } else {
                    if (key.equals(nodeID)) {
                        return (" AS " + alias.get(s));
                    }
                }
            }
        }
        return "";
    }

    static String getSQLStmtID(CypNode cN, MatchClause matchC) {
        // if rel is of type (a)-[]->(b)
        if (matchC.getNodes().size() == 2) {
            switch (cN.getPosInClause()) {
                case 1:
                    return "n2";
                case 2:
                    return "n";
            }
        }
        return "n";
    }
}
