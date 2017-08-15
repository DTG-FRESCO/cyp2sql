package schema_conversion;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import production.C2SProperties;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Each Neo4j dump file is split into chunks, which are operated on by
 * individual threads using this class, and in particular its run() method.
 */
class PerformWork implements Runnable {
    private ArrayList<String> lines;
    private BufferedWriter bwNodes;
    private BufferedWriter bwEdges;
    private C2SProperties propsX;

    /**
     * Constructor for setting up a worker thread.
     *
     * @param strings List of strings for the thread to work on.
     * @param file    Index (letter/char) of file to store results from this thread. File should be a single
     *                letter such as a, b, or c.
     */
    PerformWork(ArrayList<String> strings, String file, C2SProperties props) {
        this.lines = strings;
        this.propsX = props;

        FileOutputStream fosNodes;
        FileOutputStream fosEdges;

        try {
            //Construct BufferedReader from InputStreamReader
            fosNodes = new FileOutputStream(SchemaConvert.nodesFile.replace(".txt", file + ".txt"));
            this.bwNodes = new BufferedWriter(new OutputStreamWriter(fosNodes));

            //Construct BufferedReader from InputStreamReader
            fosEdges = new FileOutputStream(SchemaConvert.edgesFile.replace(".txt", file + ".txt"));
            this.bwEdges = new BufferedWriter(new OutputStreamWriter(fosEdges));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runnable methods that parses the Neo4j dump into a JSON representation that is used later on
     * when executing the new relations on the SQL backend.
     */
    public void run() {
        Matcher m;

        int totalLines = lines.size();
        int linesRead = 0;
        int previousPercent = 0;

        for (String s : lines) {
            // removes the CREATE characters from each line.
            s = s.substring(7).toLowerCase();

            // Use Regex to determine whether the line in the file is a node or an edge.
            m = SchemaConvert.patternN.matcher(s);

            // If the line is a node...
            if (m.find()) parseNode(s);
            else parseEdge(s);

            // keep user updated on progress of the schema translation.
            linesRead++;
            int percent = (linesRead * 100 / totalLines);
            if (previousPercent < (percent - 5)) {
                System.out.println(percent + "% read.");
                previousPercent = percent;
            }
        }

        try {
            bwNodes.close();
            bwEdges.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for parsing an edge from the dump file.
     *
     * @param s Edge to parse and convert.
     */
    private void parseEdge(String s) {
        s = s.replace("`", "");
        String[] items = s.split("\\)-");

        int idL = Integer.parseInt(items[0].substring(2, items[0].length()));

        String[] innerItems = items[1].split("->");
        int idR = Integer.parseInt(innerItems[1].substring(2, innerItems[1].length() - 1));

        String relationship = innerItems[0].substring(2, innerItems[0].length() - 1);

        // does the relationship have properties?
        Matcher m = SchemaConvert.patternR.matcher(s);

        JsonObject o = null;

        if (m.find()) {
            String[] relAndProps = relationship.split(" \\{");
            relationship = relAndProps[0];
            relAndProps[1] = "{".concat(relAndProps[1]);
            o = (JsonObject) SchemaConvert.parser.parse(relAndProps[1]);
        }

        if (o == null) o = new JsonObject();
        o.addProperty("idL", idL);
        o.addProperty("idR", idR);
        o.addProperty("type", relationship);

        if (!SchemaConvert.relTypes.contains(relationship)) {
            SchemaConvert.relTypes.add(relationship);
        }

        for (Map.Entry<String, JsonElement> entry : o.entrySet()) {
            String type = calculateDataType(entry.getKey(), entry.getValue());

            if (!SchemaConvert.edgesRelLabels.contains(entry.getKey() + " TEXT") &&
                    !SchemaConvert.edgesRelLabels.contains(entry.getKey() + " INT") &&
                    !SchemaConvert.edgesRelLabels.contains(entry.getKey() + " BIGINT") &&
                    !SchemaConvert.edgesRelLabels.contains(entry.getKey() + " TEXT[]")) {
                String textToAdd = entry.getKey() + " " + type;
                if (propsX.getListFields().contains(entry.getKey()))
                    textToAdd = textToAdd + "[]";
                SchemaConvert.edgesRelLabels.add(textToAdd);
            }
        }

        try {
            bwEdges.write(o.toString());
            bwEdges.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for parsing a node from the dump file.
     *
     * @param s Node to parse and convert.
     */
    private void parseNode(String s) {
        // initialSplit[0] contains id and node label
        // initialSplit[1] contains properties of the node
        String[] initialSplit = s.split("` ");

        String[] idAndLabel = initialSplit[0].split(":`");
        int id = Integer.parseInt(idAndLabel[0].substring(2));

        // recover the name of the label.
        for (int i = 2; i < idAndLabel.length; i++) {
            idAndLabel[1] += idAndLabel[i];
        }

        // obtain the label of the node. In the case of multiple labels, the label in the schema
        // becomes a comma separated value of the multiple labels.
        String nodeLabel = idAndLabel[1].replace("`", ", ");

        // obtain the properties of the node (format = JSON object).
        String props = initialSplit[1].replace("`", "");
        JsonObject o = (JsonObject) SchemaConvert.parser.parse(props.substring(0, props.length() - 1));

        // For each possible key that *may* contain a list...
        for (String l : propsX.getListFields()) {
            if (o.has(l) && !o.get(l).isJsonArray()) {
                String list_val = o.get(l).getAsString();
                JsonArray j_array = new JsonArray();
                j_array.add(list_val);
                o.remove(l);
                o.add(l, j_array);
            }
        }

        o.addProperty("id", id);
        o.addProperty("label", nodeLabel);

        for (Map.Entry<String, JsonElement> entry : o.entrySet()) {
            // calculate the datatype of the value
            String type = calculateDataType(entry.getKey(), entry.getValue());
            addToLabelMap(nodeLabel, entry.getKey(), type);

            if (!SchemaConvert.nodeRelLabels.contains(entry.getKey() + " TEXT") &&
                    !SchemaConvert.nodeRelLabels.contains(entry.getKey() + " INT") &&
                    !SchemaConvert.nodeRelLabels.contains(entry.getKey() + " BIGINT") &&
                    !SchemaConvert.nodeRelLabels.contains(entry.getKey() + " TEXT[]")) {
                SchemaConvert.nodeRelLabels.add(entry.getKey() + " " + type);
            }
        }

        try {
            bwNodes.write(o.toString());
            bwNodes.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate the datatype of a key, based on an example value presented to this method. This method mostly
     * works as expected, however fails in some cases where it inteprets a field as an INT, where in fact it
     * should be of type BIGINT. The tool cannot currently handle this automatically, however can be fixed
     * manually in the code.
     *
     * @param key   Key of the field being tested.
     * @param value An example value of this field.
     * @return The correct datatype.
     */
    private String calculateDataType(String key, JsonElement value) {
        if (value.isJsonArray()) {
            return "TEXT[]";
        } else {
            // enter hacks here...
            if (key.equals("mono_time")) return "BIGINT";
            else {
                String testValue = value.getAsString();
                try {
                    //noinspection ResultOfMethodCallIgnored
                    Integer.parseInt(testValue);
                    return "INT";
                } catch (NumberFormatException nfe) {
                    return "TEXT";
                }
            }
        }
    }

    /**
     * Each label has all of its keys (and their datatypes) stored.
     *
     * @param nodeLabel Label of the node.
     * @param key       Key of the property.
     * @param type      The datatype of the property (see calculateDataType()).
     */
    private void addToLabelMap(String nodeLabel, String key, String type) {
        if (SchemaConvert.labelMappings.keySet().contains(nodeLabel)) {
            String currentValue = SchemaConvert.labelMappings.get(nodeLabel);
            if (!currentValue.contains(key)) {
                SchemaConvert.labelMappings.put(nodeLabel, currentValue + ", " + key + " " + type);
            }
        } else SchemaConvert.labelMappings.put(nodeLabel, "id INT, " + key + " " + type);
    }
}
