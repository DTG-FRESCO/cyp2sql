package database;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import production.C2SMain;
import production.C2SProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Driver connected to the Neo4j database.
 * Cypher queries can be run from within Java, and then the results parsed,
 * and outputted to a text file. The idea is this text file can then
 * be automatically compared against the results from Postgres.
 */
public class Neo4jDriver {
    // public variable keeping track of how long the Cypher queries take.
    public static long lastExecTime = 0;

    /**
     * Method that runs Cypher query.
     *
     * @param query          Cypher to execute.
     * @param cypher_results File to store the results.
     * @param props          C2SProperties object (should already be initialised).
     * @param printOutput    Set to true to store the outputs of the query on disk.
     */
    public static void run(String query, File cypher_results, boolean printOutput, C2SProperties props) {
        // database essentials
        Driver driver = GraphDatabase.driver("bolt://localhost",
                AuthTokens.basic(props.getNeoUN(), props.getNeoPW()));
        Session session = driver.session();

        // print to file if the result being returned is a count, so that the tool can
        // validate that the translation was valid
        printOutput = printOutput || query.toLowerCase().contains("count");

        // timing unit
        long startNano = System.nanoTime();
        session.run(query).consume();
        long endNano = System.nanoTime();
        lastExecTime = endNano - startNano;


        // only print results to the output file if the query is for reading.
        if (!query.toLowerCase().startsWith("create")) {
            StatementResult result = session.run(query);
            int countRecords = 0;

            try {
                PrintWriter writer = (printOutput) ? new PrintWriter(cypher_results, "UTF-8") : null;
                while (result.hasNext()) {
                    Record record = result.next();
                    if (printOutput) {
                        Map<String, Object> mapResults = record.asMap();

                        for (Map.Entry<String, Object> entry : mapResults.entrySet()) {
                            String key = formatKey(entry.getKey());

                            // is the value a node...
                            String strValue = String.valueOf(entry.getValue());

                            // should change to regex really or work out if it is a node without converting to a string.
                            if (strValue.contains("node<")) {
                                Node n = record.get(key).asNode();

                                for (String s : n.keys()) {
                                    if (props.getListFields().contains(s)) {
                                        writer.println(s + " : {" + formatVal(n.get(s))
                                                .toString()
                                                .replace("[", "")
                                                .replace("]", "") + "}");
                                    } else writer.println(s + " : " + formatVal(n.get(s)));
                                }
                            } else {
                                if (props.getListFields().contains(key)) {
                                    writer.println(key + " : {" + entry.getValue()
                                            .toString()
                                            .replace("[", "")
                                            .replace("]", "") + "}");
                                } else {
                                    if (key.startsWith("id(")) key = "id";
                                    writer.println(key + " : " + entry.getValue());
                                }
                            }
                        }
                    }
                    countRecords++;
                }

                if (printOutput) {
                    writer.println();
                    writer.println("NUM RECORDS : " + countRecords);
                    writer.close();
                }

                C2SMain.numResultsNeo4j = countRecords;
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // close the database connection.
        session.close();
        driver.close();
    }

    /**
     * Formats the value returned from the Neo4j database so that it matches the format emitted from Postgres,
     * thus helping to validate the results.
     * In particular, if the value is a number (long), then the inverted commas are removed.
     *
     * @param origValue The original value (type of Object) returned from the Neo4j database.
     * @return Correctly formatted value to output to file.
     */
    private static Object formatVal(Object origValue) {
        String strValue = String.valueOf(origValue);
        strValue = strValue.replace("\"", "");
        try {
            Long o = Long.valueOf(strValue);
            return o;
        } catch (Exception e) {
            return strValue;
        }
    }

    /**
     * Formats the key returned from the Neo4j database so that it matches the format emitted from Postgres,
     * thus helping to validate the results.
     * In particular, if the key is some sort of count, then only 'count' is returned. Likewise, if something
     * similar to n.property is returned, then the leading n. is removed, and just 'property' is returned.
     *
     * @param origKey The original key returned from the Neo4j database.
     * @return Correctly formatted key to output to file.
     */
    private static String formatKey(String origKey) {
        if (origKey.contains("count(")) {
            return "count";
        } else if (origKey.contains("max(")) {
            return "max";
        } else if (origKey.contains("min(")) {
            return "min";
        } else if (origKey.contains("sum(")) {
            return "sum";
        } else if (origKey.contains("avg(")) {
            return "avg";
        } else if (origKey.startsWith("case")) {
            return "case";
        } else if (origKey.contains(".")) {
            StringBuilder newKey = new StringBuilder();
            String[] parts = origKey.split("\\.");
            for (int i = 1; i < parts.length; i++) newKey.append(parts[i]);
            return newKey.toString();
        }
        return origKey;
    }

    /**
     * Warm up the Neo4J database by running the following query:
     * MATCH (n) OPTIONAL MATCH (n)-[r]-{@literal >}() RETURN count(n.prop) + count(r.prop);
     */
    public static void warmUp(C2SProperties props) {
        Driver driver = GraphDatabase.driver("bolt://localhost",
                AuthTokens.basic(props.getNeoUN(), props.getNeoPW()));
        Session session = driver.session();
        String warm_up_query = "MATCH (n) OPTIONAL MATCH (n)-[r]->() RETURN count(n.prop) + count(r.prop);";
        session.run(warm_up_query).consume();
        session.close();
        driver.close();
    }
}
