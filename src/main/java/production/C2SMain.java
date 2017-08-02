package production;

import database.KeyValueTest;
import database.Neo4jDriver;
import database.RelDBDriver;
import intermediate_rep.DecodedQuery;
import org.apache.commons.io.FileUtils;
import org.neo4j.driver.v1.exceptions.ClientException;
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;
import query_translation.sql.conversion_types.*;
import schema_conversion.SchemaConvert;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Main class for starting the application.
 */
public class C2SMain {
    // for optimisations based on the return clause of Cypher
    public static final Map<String, String> labelProps = new HashMap<>();
    // for use in deleting relationships attached to nodes
    public static final ArrayList<String> allRelTypes = new ArrayList<>();

    public static C2SProperties props;
    public static int numResultsNeo4j;
    public static int numResultsPostgres;
    // stores the current DecodedQuery object so that the Cypher results module can use it,
    // without having to rerun computation.
    public static DecodedQuery currentDQ = null;
    // variable set at the command line to turn on/off printing to a file the results of a read query.
    static boolean printBool = false;
    // variable set at the command line to indicate whether results should be emailed back to the user
    private static boolean emailUser = false;
    // variable set at the command line to indicate whether testing is being performed on the queries.
    private static boolean testConditions = true;
    // store queries that fail so that they are not run again during the evaluation.
    private static ArrayList<String> denyList = new ArrayList<>();

    /**
     * <-schema|-translate|-s|-t> <propertiesFile> <databaseName> <-a|-e|-i>
     * View README for additional guidance.
     *
     * @param args Arguments for the application.
     */
    public static void main(String args[]) {
        if (args.length == 0) printError(0);
        if ((args[0].equals("-s") || args[0].equals("-schema")) && args.length != 3) {
            printError(1);
        } else if ((args[0].equals("-t") || args[0].equals("-translate")) && args.length != 4) {
            printError(2);
        }

        // obtain properties for the program from the properties file.
        props = new C2SProperties(args[1]);

        // create file objects to store results of the file
        File f_cypher = new File(props.getNeo4jRes());
        File f_sql = new File(props.getSqlRes());

        String dbName = args[2];
        System.out.println("DATABASE RUNNING : " + dbName);

        if (args.length == 4)
            switch (args[3]) {
                case "-e":
                    emailUser = true;
                    break;
                case "-a":
                    testConditions = false;
                    printBool = true;
                    break;
                case "-i":
                    printBool = true;
                    C2SInteractive.run(f_cypher, f_sql, dbName);
                    System.exit(0);
                default:
                    printError(3);
            }

        switch (args[0]) {
            case "-schema":
            case "-s":
                // perform the schema translation
                convertGraphSchema(props.getNeo4jSchema(), dbName);
                break;
            case "-translate":
            case "-t":
                System.out.println("PRINT TO FILE : " + ((printBool) ? "enabled" : "disabled"));
                System.out.println("EMAIL USER : " + ((emailUser) ? "enabled" : "disabled"));
                getLabelMapping();
                warmUpResetSSL();

                // clear the database test results in preparation for new test data.
                if (testConditions) RelDBDriver.clearTestContents(dbName);

                if (!printBool && testConditions) {
                    // translate Cypher queries to SQL.
                    // first, reorder the queries file, to randomise the order in which the
                    // queries are executed. This is more for testing than production purposes however.
                    randomiseQueriesFile(args[1]);

                    String randomQueriesFile = args[1].replace(".txt", "_temp.txt");

                    // perform 3 dry runs, then record the times of 5 executions
                    for (int i = -2; i <= 5; i++) {
                        if (i < 1) System.out.println("Warming up - iterations left : " + (i * -1));
                        translateCypherFile(randomQueriesFile, f_cypher, f_sql, i, dbName);
                    }

                    // send the results via email
                    /*
                    if (emailUser)
                        try {
                            SendResultsEmail.sendEmail(dbName, args[0]);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    */

                    // delete temporary queries file.
                    File f = new File(randomQueriesFile);
                    f.delete();
                } else {
                    translateCypherFile(props.getQueriesFile(), f_cypher, f_sql, 1, dbName);
                }
                break;
            default:
                printError(4);
        }
    }

    static void warmUpResetSSL() {
        // warm up the Neo4j caches if the server has just been turned on.
        // https://neo4j.com/developer/kb/warm-the-cache-to-improve-performance-from-cold-start/
        // also, if there is an SSL issue when changing databases, attempt to fix it by running
        // some fix up code.
        try {
            Neo4jDriver.warmUp();
        } catch (ClientException ce) {
            if (ce.getMessage().contains("SSLEngine problem")) {
                System.out.println("Resetting Neo4j SSL properties...");
                // Neo4jDriver.resetSSLNeo4J();
                System.out.println("Reset complete.");
                Neo4jDriver.warmUp();
            }
        } catch (ServiceUnavailableException unavail) {
            System.err.println("*** Make sure the Neo4j database is up and running correctly. ***");
            unavail.printStackTrace();
            System.exit(1);
        }
    }

    private static void printError(int errorCode) {
        switch (errorCode) {
            case 0:
                System.err.println("*** Error 0: Must pass either 3 or 4 arguments to the program. ***");
                break;
            case 1:
                System.err.println("*** Error 1: Schema translator requires 3 arguments. ***");
                break;
            case 2:
                System.err.println("*** Error 2: The tool requires 4 arguments to function correctly. ***");
                break;
            case 3:
                System.err.println("*** Error 3: Fourth command line flag is invalid. Options are below. ***");
                break;
            case 4:
                System.err.println("*** Error 4: First command line flag is invalid. Options are below. ***");
                break;
            default:
                System.err.println("*** Error running the application. ***");
        }
        System.err.println("*** Please see README for further help. ***");
        System.err.println("<-schema|-translate|-s|-t> <propertiesFile> <databaseName> <-a|-e|-i>");
        System.exit(1);
    }

    /**
     * Converting a 'dump' from an existing Neo4j graph into a relational schema, before executing
     * on a relational backend.
     *
     * @param neo4jSchema  The 'dump' file being converted. This location is set in the properties file,
     *                     under the key 'neo4jSchema'.
     * @param databaseName The name of the database to be used for storing the relational representation.
     */
    private static void convertGraphSchema(String neo4jSchema, String databaseName) {
        System.out.println("\n***CONVERTING THE SCHEMA***\n");
        boolean successfulConversion = SchemaConvert.translate(neo4jSchema);
        if (successfulConversion) {
            System.out.println("\n***INSERTING THE SCHEMA TO THE DATABASE***\n");
            //InsertSchema.executeSchemaChange(databaseName);
            KeyValueTest.executeSchemaChange();
        } else {
            // error in the schema conversion - exits the application.
            System.err.println("Conversion of the graph schema to a relational form has failed.");
            System.exit(1);
        }
    }

    /**
     * This method performs two important tasks for the translator tool:
     * - Firstly, the translator tool checks to see if it can optimise any of the queries by storing a mapping
     * between unique keys, and the label of the node that tke key will only appear under. These mappings are
     * stored in the labelProps object.
     * <p>
     * - Secondly, it reads in all the possible types of relationships
     * (used for example when deleting nodes from the relational schema).
     */
    static void getLabelMapping() {
        // open file and read in property keys, removing duplicates as they are of no use.
        FileInputStream fis;
        try {
            fis = new FileInputStream(props.getWspace() + "/meta_labelProps.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            String currentLabelType = null;

            // ArrayList to keep track of duplicates.
            ArrayList<String> dupKeys = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("*")) {
                    // view meta_labelProps.txt/README for more information.
                    // Lines starting with a '*' defines the label that the following properties
                    // in the file belong to.
                    currentLabelType = line.substring(1, line.length() - 1);
                } else {
                    if (labelProps.containsKey(line)) {
                        dupKeys.add(line);
                    } else {
                        labelProps.put(line, currentLabelType);
                    }
                }
            }

            // remove the duplicate keys.
            for (String s : dupKeys) labelProps.remove(s);

            br.close();
            fis.close();

            // Section for reading in all the different types of relationships contained within the dataset.
            fis = new FileInputStream(props.getWspace() + "/meta_rels.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) allRelTypes.add(line);
            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iterate through the original query file, randomising the order of the queries within the file.
     *
     * @param queriesFile Original location of the queries file.
     */
    private static void randomiseQueriesFile(String queriesFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(queriesFile));
            Map<Integer, String> queries = new TreeMap<>();
            String line;
            Random r = new Random();
            while ((line = reader.readLine()) != null) {
                queries.put(r.nextInt(1000), line);
            }
            reader.close();

            FileWriter writer = new FileWriter(queriesFile.replace(".txt", "_temp.txt"));

            for (String val : queries.values()) {
                writer.write(val);
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void translateCypherToSQL(String cypher_line, File f_cypher, File f_pg,
                                     int repeatCount, String dbName) throws Exception {
        String sql;

        if (cypher_line.toLowerCase().contains(" foreach ")) {
            ForEach_Cypher fe = new ForEach_Cypher();
            sql = fe.convertQuery(cypher_line);
        } else if (cypher_line.toLowerCase().contains(" with ")) {
            With_Cypher wc = new With_Cypher();
            sql = wc.convertQuery(cypher_line);
        }
        // commented code below was for transitive closure representation which is not used
//        else if (cypher_line.toLowerCase().contains("allshortestpaths")) {
//            ASP_Cypher aspc = new ASP_Cypher();
//            sql = aspc.convertQuery(cypher_line);
//        }
        else if (cypher_line.toLowerCase().contains("shortestpath")) {
            SP_Cypher spc = new SP_Cypher();
            sql = spc.convertQuery(cypher_line);
        } else if (cypher_line.toLowerCase().contains("iterate")) {
            Iterate_Cypher ic = new Iterate_Cypher();
            sql = ic.convertQuery(cypher_line);
        } else {
            sql = AbstractConversion.convertCypherToSQL(cypher_line).getSqlEquiv();
        }

        boolean sqlExecSuccess;
        if (sql != null && !sql.isEmpty()) {
            sqlExecSuccess = executeSQL(sql, f_pg, (printBool || cypher_line.toLowerCase().contains("count")), dbName);
        } else throw new Exception("Conversion of SQL failed");

        if (!sqlExecSuccess) denyList.add(cypher_line);

        // All the Cypher queries other than the extension
        if (!cypher_line.toLowerCase().contains("iterate"))
            Neo4jDriver.run(cypher_line, f_cypher, printBool, testConditions);

        // validate the results
        if (sqlExecSuccess) {
            if ((numResultsNeo4j != numResultsPostgres) && !cypher_line.toLowerCase().contains("iterate")
                    && !cypher_line.toLowerCase().startsWith("create")
                    && !cypher_line.toLowerCase().contains("detach delete")
                    && !cypher_line.toLowerCase().contains("foreach")) {
                translationFail(cypher_line, sql, f_cypher, f_pg);
            } else if (cypher_line.toLowerCase().contains("count") && !cypher_line.toLowerCase().contains("with")) {
                boolean countSame = FileUtils.contentEquals(f_cypher, f_pg);
                if (!countSame) {
                    translationFail(cypher_line, sql, f_cypher, f_pg);
                }
            }

            if (repeatCount > 0) {
                // record the performance of Cypher and SQL on Neo4J and Postgres respectively.
                printSummary(cypher_line, sql, f_cypher, f_pg, testConditions);
                // DbUtil.insertMapping(line, sql, returnItemsForCypher, dbName);
            }
        }

        resetExecTimes();
    }


    /**
     * Translating the queries in the file to SQL and executing them.
     *
     * @param translateFile String file location containing a list of Cypher queries.
     * @param f_cypher      File object - the output from the Neo4j Java driver will be sent here.
     * @param f_pg          File object - the output from the JDBC driver will be sent here.
     * @param repeatCount   The number of iterations completed so far. If less than 0, the database is still being
     * @param dbName        The name of the database the queries are to be executed on (for the relational side; the
     *                      equivalent Neo4j database needs to already be a running process).
     */
    private static void translateCypherFile(String translateFile, File f_cypher, File f_pg,
                                            int repeatCount, String dbName) {
        try {
            FileInputStream fis = new FileInputStream(translateFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                // if line is commented out in the read queries file, then do not attempt to convert it.
                if (!line.startsWith("//") && !line.isEmpty() && !denyList.contains(line))
                    translateCypherToSQL(line, f_cypher, f_pg, repeatCount, dbName);
            }
            br.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute the SQL command on the database.
     * If query is a concatenation of multiple queries, then perform then
     * one by one in order that was passed to the method.
     * Type of method call depends on whether or not query begins with CREATE
     * or not.
     *
     * @param sql         SQL to execute.
     * @param pg_results  File to store the results.
     * @param printOutput Write the results to a file for viewing.
     * @param dbName      Name of the database the SQL will be executed on.
     */
    private static boolean executeSQL(String sql, File pg_results, boolean printOutput, String dbName) {
        try {
            String indivSQL[] = sql.split(";");
            for (String q : indivSQL) {
                if (q.trim().startsWith("CREATE")) {
                    RelDBDriver.executeCreateView(q + ";", dbName);
                } else if (q.trim().startsWith("INSERT")) {
                    RelDBDriver.insertOrDelete(q + ";", dbName);
                } else if (q.trim().startsWith("DELETE")) {
                    RelDBDriver.insertOrDelete(q + ";", dbName);
                } else
                    RelDBDriver.select(q + ";", dbName, pg_results, printOutput);
            }
        } catch (SQLException e) {
            System.out.println("FAILED IN executeSQL -- " + sql);
            e.printStackTrace();
            //if (emailUser) SendResultsEmail.sendFailEmail(dbName, sql);
            return false;
        }
        return true;
    }

    /**
     * Print summary of the translation.
     *
     * @param line           Cypher query.
     * @param sql            SQL equivalent.
     * @param f_cypher       File containing Neo4J output.
     * @param f_pg           File containing Postgres output.
     * @param testConditions If this flag is set than the times of execution will be printed.
     * @throws IOException Error comparing the files f_cypher and f_pg.
     */
    private static void printSummary(String line, String sql, File f_cypher, File f_pg,
                                     boolean testConditions) throws IOException {
        System.out.println("**********\nCypher Input : " + line);
        System.out.println("SQL Output: " + sql + "\nExact Result: " +
                FileUtils.contentEquals(f_cypher, f_pg) + "\nNumber of records from Neo4j: " +
                numResultsNeo4j + "\nNumber of results from PostG: " + numResultsPostgres);
        if (testConditions) {
            System.out.println("Time on Neo4j: \t\t" + (Neo4jDriver.lastExecTime / 1000000.0) +
                    " ms.\nTime on Postgres: \t" + ((RelDBDriver.lastExecTimeRead + RelDBDriver.lastExecTimeCreate +
                    RelDBDriver.lastExecTimeInsert)
                    / 1000000.0) +
                    " ms.");
        }
        System.out.println("**********\n");
    }

    private static void translationFail(String line, String sql, File f_cypher, File f_pg) throws IOException {
        System.err.println("\n**********Statements do not appear to " +
                "be logically correct - please check**********\n"
                + line + "\n" + sql + "\n***********");
        printSummary(line, sql, f_cypher, f_pg, testConditions);
        System.exit(1);
    }

    /**
     * Reset measured performance times.
     */
    private static void resetExecTimes() {
        Neo4jDriver.lastExecTime = 0;
        RelDBDriver.lastExecTimeCreate = 0;
        RelDBDriver.lastExecTimeRead = 0;
        RelDBDriver.lastExecTimeInsert = 0;
    }
}