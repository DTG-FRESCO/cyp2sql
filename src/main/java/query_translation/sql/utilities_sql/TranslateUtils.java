package query_translation.sql.utilities_sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import intermediate_rep.CypNode;
import intermediate_rep.CypRel;
import intermediate_rep.CypReturn;
import intermediate_rep.ReturnClause;
import production.C2SMain;
import production.C2SProperties;
import query_translation.sql.conversion_types.Multiple_With_Cypher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class with useful translation methods that can be used regardless of the type of Cypher query
 * actually being translated.
 */
class TranslateUtils {
    private static StringBuilder genWhere(StringBuilder sql, JsonObject obj, String sqlLabel) {
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();

        TreeMap<Integer, StringBuilder> whereComps = new TreeMap<>();

        StringBuilder beginningWhere = new StringBuilder();

        for (Map.Entry<String, JsonElement> entry : entries) {
            String value;
            if (entry.getValue().isJsonArray()) {
                value = "ARRAY" + entry.getValue().getAsJsonArray().toString();
            } else if (C2SProperties.listFields.contains(entry.getKey())) {
                value = "ARRAY[" + entry.getValue().toString() + "]";
            } else value = entry.getValue().getAsString();

            for (String innerVal : value.split("~")) {
                if (!innerVal.contains("$") && !innerVal.contains("@") && !innerVal.contains("#")) {
                    beginningWhere.append(sqlLabel).append(".").append(entry.getKey());
                    beginningWhere = (addWhereClause(beginningWhere, innerVal, sqlLabel));
                    beginningWhere.append(" and ");
                } else {
                    boolean isArray = innerVal.startsWith("ARRAY[");
                    if (isArray) innerVal = innerVal.substring(7, innerVal.length() - 2);

                    int pos = Integer.parseInt(String.valueOf(innerVal.charAt(1)));
                    String boolOp = innerVal.substring(2, innerVal.indexOf("@"));
                    String bracketing = innerVal.split("@")[1].split("\\$")[0];
                    innerVal = innerVal.split("\\$")[1];

                    StringBuilder temp = new StringBuilder();
                    if (!bracketing.equals("null") && bracketing.startsWith("(")) temp.append(bracketing);
                    temp.append(sqlLabel).append(".").append(entry.getKey());

                    if (isArray) innerVal = "ARRAY[\"" + innerVal + "\"]";
                    temp = addWhereClause(temp, innerVal, sqlLabel);

                    if (!bracketing.equals("null") && bracketing.startsWith(")")) temp.append(bracketing).append(" ");
                    if (!boolOp.equals("null")) temp.append(boolOp);
                    temp.append(" ");

                    whereComps.put(pos, temp);
                }
            }
        }

        StringBuilder whereBit = new StringBuilder();
        for (StringBuilder x : whereComps.values()) {
            whereBit.append(x.toString());
        }
        if (whereBit.length() == 0) {
            if (beginningWhere.toString().endsWith(" and ")) beginningWhere.setLength(beginningWhere.length() - 5);
        }
        sql.append(beginningWhere).append(" ");
        sql.append(whereBit);

        return sql;
    }

    static StringBuilder getWholeWhereClause(StringBuilder sql, CypNode cN, String sqlLabel) {
        JsonObject obj = cN.getProps();
        return genWhere(sql, obj, sqlLabel);
    }

    /**
     * If no label provided as an argument, method just adds the default label which is just 'n' (for node), and
     * then calls the method getWholeWhereClause.
     *
     * @param sql Original SQL statement.
     * @param cN  CypNode with properties.
     * @return New SQL with WHERE part added.
     */
    static StringBuilder getWholeWhereClause(StringBuilder sql, CypNode cN) {
        return getWholeWhereClause(sql, cN, "n01");
    }

    static StringBuilder getWholeWhereClauseRel(StringBuilder sql, CypRel cR, String sqlLabel) {
        JsonObject obj = cR.getProps();
        return genWhere(sql, obj, sqlLabel);
    }

    private static StringBuilder addWhereClause(StringBuilder sql, String value, String sqlLabel) {
        // format part of the where clause correctly for further parsing.
        if (!value.contains("#")) value = "eq#" + value + "#qe";
        sql = getProperWhereValue(value, sql, sqlLabel);
        return sql;
    }

    private static StringBuilder getProperWhereValue(String value, StringBuilder sql, String sqlLabel) {
        // remove anything to do with ARRAYS from the value
        boolean array = value.startsWith("ARRAY[");
        boolean list = false;
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
        } else if (value.startsWith("isn#")) {
            sql.append(" IS NULL ");
            return sql;
        } else if (value.startsWith("non#")) {
            sql.append(" IS NOT NULL ");
            return sql;
        } else if (value.startsWith("nx#")) {
            sql.append(" IS NULL ");
            return sql;
        } else if (value.startsWith("in#")) {
            sql.append(" IN ");
            list = true;
            v = value.substring(3, value.indexOf("#ni"))
                    .replace("[", "(").replace("]", ")");
        } else if (value.startsWith("anyin#")) {
            sql.append(" = any(ARRAY");
            v = value.substring(6, value.indexOf("#niyna"));
            sql.append(v).append(")");
            return sql;
        } else if (value.startsWith("anyeq#")) {
            String currentSQL = sql.toString();
            String[] findParts = currentSQL.split(" ");

            int lBrackCount = 0;
            while (findParts[findParts.length - 1].startsWith("(")) {
                lBrackCount++;
                findParts[findParts.length - 1] = findParts[findParts.length - 1].substring(1);
            }

            findParts[findParts.length - 1] = "any(" + findParts[findParts.length - 1] + ") ";

            v = value.substring(6, value.indexOf("#qeyna"));
            sql = new StringBuilder();

            for (int i = 0; i < findParts.length - 1; i++) {
                sql.append(findParts[i]).append(" ");
            }

            for (int z = lBrackCount; z > 0; z--) sql.append("(");
            sql.append(v).append(" = ").append(findParts[findParts.length - 1]);
            return sql;
        }


        // this bit of code helps the WITH translation to work
        // see WithSQL.java and the method createSelectMatch() for further information.

        if (!WithSQL.withMapping.isEmpty() && WithSQL.withMapping.containsKey(v.split("\\.")[0])) {
            sql.append(v.replace(v.split("\\.")[0] + ".",
                    WithSQL.withMapping.get(v.split("\\.")[0]) + "."));
        } else {
            if (array) {
                if (list) {
                    sql.append("(");
                    v = v.replace("(", "").replace(")", "");
                }
                String arrayItems[] = v.split(", ");
                for (String x : arrayItems) {
                    sql.append("ARRAY[").append(x.replace("\"", "'")).append("] ").append(", ");
                }
                sql.setLength(sql.length() - 2);
                if (list) sql.append(")");
            } else if (v.equals("ANY($1)")) sql.append(v).append(" ");
            else if (list) sql.append(v).append(" ");
            else if (v.startsWith("id(")) {
                String id = v.substring(v.indexOf("(") + 1, v.length() - 1);
                String withTable;
                if (Multiple_With_Cypher.mappingMultipleWith != null)
                    withTable = Multiple_With_Cypher.mappingMultipleWith.get(id);
                else withTable = "n2";
                sql.append(withTable).append(".id");
            } else if (v.contains(".")) {
                String idAndValue[] = v.split("\\.");
                String withTable;

                // swap n1 for n2 and vice versa.
                sqlLabel = (sqlLabel.equals("n1")) ? "n2" : "n1";

                if (Multiple_With_Cypher.mappingMultipleWith != null) {
                    withTable = Multiple_With_Cypher.mappingMultipleWith.get(idAndValue[0]);
                } else withTable = sqlLabel;

                if (withTable == null) withTable = sqlLabel;
                sql.append(withTable).append(".").append(idAndValue[1]).append(" ");
            } else sql.append("'").append(v.replace("'", "")).append("' ");
        }
        return sql;
    }

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

    static String getLabelType(String type, C2SProperties props) {
        if (type == null) return "nodes";

        // nodes is the default table with all the data inside it.
        String correctTable = "nodes";

        // integer count to indicate whether or not label type specific enough for this optimisation.
        FileInputStream fis = null;
        BufferedReader br = null;
        int changed = 0;

        try {
            fis = new FileInputStream(props.getWspace() + "/meta_labelNames.txt");
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
                // TODO: extend for more of the aggregate functions.
                if (key.startsWith("collect")) key = key.substring(8, key.length() - 1);
                if (key.startsWith("sum")) key = key.substring(4, key.length() - 1);
                if (key.startsWith("max")) key = key.substring(4, key.length() - 1);
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

    static String findOptimisedTable(ReturnClause rc) {
        boolean possibleOpti = true;
        String possTable = "nodes";

        for (CypReturn cR : rc.getItems()) {
            if (!C2SMain.labelProps.containsKey(cR.getField())) {
                possibleOpti = false;
                break;
            } else {
                String newTable = C2SMain.labelProps.get(cR.getField());
                if (!possTable.equals(newTable) && !possTable.equals("nodes")) {
                    possibleOpti = false;
                    break;
                }
                possTable = newTable;
            }
        }

        if (possibleOpti) return possTable;
        else return "nodes";
    }
}
