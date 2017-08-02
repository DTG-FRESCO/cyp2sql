package database;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import production.C2SMain;

import java.io.*;
import java.util.ArrayList;
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
     * @param printOutput    Set to true to store the outputs of the query on disk.
     * @param testConditions If set to true, the times of the execution will not be recorded.
     */
    public static void run(String query, File cypher_results, boolean printOutput, boolean testConditions) {
        // database essentials
        Driver driver = GraphDatabase.driver("bolt://localhost",
                AuthTokens.basic(C2SMain.props.getNeoUN(), C2SMain.props.getNeoPW()));
        Session session = driver.session();
        // print to file if the result being returned is a count, so that the tool can
        // validate that the translation was valid
        printOutput = printOutput || query.toLowerCase().contains("count");

        // timing unit
        if (testConditions) {
            long startNano = System.nanoTime();
            session.run(query).consume();
            long endNano = System.nanoTime();
            lastExecTime = endNano - startNano;
        }

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
                                    if (C2SMain.props.getListFields().contains(s)) {
                                        writer.println(s + " : {" + formatVal(n.get(s))
                                                .toString()
                                                .replace("[", "")
                                                .replace("]", "") + "}");
                                    } else writer.println(s + " : " + formatVal(n.get(s)));
                                }
                            } else {
                                if (C2SMain.props.getListFields().contains(key)) {
                                    writer.println(key + " : {" + entry.getValue()
                                            .toString()
                                            .replace("[", "")
                                            .replace("]", "") + "}");
                                } else writer.println(key + " : " + entry.getValue());
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

    private static Object formatVal(Object value) {
        String strValue = String.valueOf(value);
        strValue = strValue.replace("\"", "");
        try {
            Long o = Long.valueOf(strValue);
            return o;
        } catch (Exception e) {
            return strValue;
        }
    }

    private static String formatKey(String key) {
        if (key.contains("count(")) {
            return "count";
        } else if (key.contains(".")) {
            StringBuilder newKey = new StringBuilder();
            String[] parts = key.split("\\.");
            for (int i = 1; i < parts.length; i++) newKey.append(parts[i]);
            return newKey.toString();
        }
        return key;
    }

    /**
     * Warm up the Neo4J database by running the following query:
     * MATCH (n) OPTIONAL MATCH (n)-[r]->() RETURN count(n.prop) + count(r.prop);
     */
    public static void warmUp() {
        Driver driver = GraphDatabase.driver("bolt://localhost",
                AuthTokens.basic(C2SMain.props.getNeoUN(), C2SMain.props.getNeoPW()));
        Session session = driver.session();
        String warm_up_query = "MATCH (n) OPTIONAL MATCH (n)-[r]->() RETURN count(n.prop) + count(r.prop);";
        session.run(warm_up_query).consume();
        session.close();
        driver.close();
    }


    /**
     * Erase contents of this file to reset the SSL settings for Neo4J. This might be a Windows issue only.
     */
    public static void resetSSLNeo4J() {
        // need to work out a more general way of locating this file...
        String file = "C:/Users/ocraw/.neo4j/known_hosts";

        ArrayList<String> contents = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String s : contents) {
                if (s.startsWith("#")) {
                    bw.write(s);
                    bw.newLine();
                }
            }
            bw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
