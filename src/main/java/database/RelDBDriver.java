package database;

import production.C2SMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database driver for Postgres.
 */
public class RelDBDriver {
    public static long lastExecTimeRead = 0;
    public static long lastExecTimeCreate = 0;
    public static long lastExecTimeInsert = 0;
    private static Connection c = null;
    private static int numRecords = 0;
    private static boolean DB_OPEN = false;

    /**
     * Create the initial connection to the database.
     *
     * @param dbName Name of the database to connect to.
     */
    static void createConnection(String dbName) {
        try {
            String sqlDBUsername = C2SMain.props.getPostUN();
            String sqlDBPassword = C2SMain.props.getPostPW();
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, sqlDBUsername,
                    sqlDBPassword);
            DB_OPEN = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Close connection to the database.
     */
    static void closeConnection() {
        try {
            c.close();
            DB_OPEN = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the SQL begins with a CREATE keyword, then this query should be executed
     * and committed first before other queries execute. This is needed when TEMP
     * views are created.
     *
     * @param query  SQL to execute (beginning with CREATE)
     * @param dbName Database name to execute on.
     * @throws SQLException Error with the SQL query being executed.
     */
    public static void executeCreateView(String query, String dbName) throws SQLException {
        if (!DB_OPEN) RelDBDriver.createConnection(dbName);
        Statement stmt = c.createStatement();

        // timing unit for creating statements.
        long startNanoCreate = System.nanoTime();
        stmt.executeUpdate(query);
        long endNanoCreate = System.nanoTime();
        lastExecTimeCreate += (endNanoCreate - startNanoCreate);

        stmt.close();
    }

    /**
     * Execute standard read SQL statement.
     *
     * @param query       SQL statement
     * @param database    Database to execute statement on.
     * @param pg_results  File to store the results.
     * @param printOutput Set to true if the output of the SQL statement should be stored in a local file.
     * @throws SQLException Thrown if there is an error in the SQL statement.
     */
    public static void select(String query, String database, File pg_results, boolean printOutput)
            throws SQLException {
        if (!DB_OPEN) RelDBDriver.createConnection(database);
        Statement stmt;
        stmt = c.createStatement();

        // obtain the columns returned from the result.
        ArrayList<ArrayList<String>> results = getQueryResult(query, stmt);
        ArrayList<String> colNames = results.get(0);
        results.remove(0);

        if (printOutput) {
            PrintWriter writer;
            try {
                writer = new PrintWriter(pg_results, "UTF-8");

                for (ArrayList<String> as : results) {
                    int i = 0;
                    for (String column : colNames) {
                        if (!column.equals("id") && !column.equals("x") && !column.equals("label")) {
                            String result = as.get(i);
                            if (result != null) writer.println(column + " : " + result);
                        }
                        i++;
                    }
                }

                writer.println();
                writer.println("NUM RECORDS : " + numRecords);
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        C2SMain.numResultsPostgres = numRecords;
        RelDBDriver.closeConnection();
    }

    /**
     * Obtain results from the database (along with additional metadata such as the columns
     * returned).
     *
     * @param query SQL to execute.
     * @param stm   Internal JDBC statement.
     * @return List of the results and column names.
     */
    private static ArrayList<ArrayList<String>> getQueryResult(String query, Statement stm)
            throws SQLException {
        ArrayList<ArrayList<String>> feedback = new ArrayList<>();
        ArrayList<String> feed;

        // timing unit
        long startNanoReadQuery = System.nanoTime();
        ResultSet rs = stm.executeQuery(query);
        long endNanoReadQuery = System.nanoTime();
        lastExecTimeRead += (endNanoReadQuery - startNanoReadQuery);

        ResultSetMetaData rsm = rs.getMetaData();

        feed = new ArrayList<>();
        for (int y = 0; y < rsm.getColumnCount(); y++) {
            feed.add(rsm.getColumnName(y + 1));
        }
        feedback.add(feed);

        numRecords = 0;

        while (rs.next()) {
            feed = new ArrayList<>();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                feed.add(rs.getString(i));
            }
            feedback.add(feed);
            numRecords++;
        }

        stm.close();
        return feedback;
    }

    /**
     * Method for creating an SQL statement object from an SQL argument, and then executing it.
     *
     * @param query SQL to run against the database.
     * @throws SQLException Error in query argument, not valid SQL or database error.
     */
    static void createInsert(String query) throws SQLException {
        Statement stmt = c.createStatement();
        long startNanoInsert = System.nanoTime();
        stmt.executeUpdate(query);
        long endNanoInsert = System.nanoTime();

        System.out.println("TIME OF QUERY : " + query.substring(0, Math.min(query.length(), 50)) + " -- " +
                ((endNanoInsert - startNanoInsert) / 1000000.0) + " ms.");

        stmt.close();
    }

    /**
     * Method for executing an SQL statement which will either insert or delete records.
     *
     * @param query  SQL statement to execute.
     * @param dbName Database name of the database to execute the statement on.
     * @throws SQLException Error with the transaction.
     */
    public static void insertOrDelete(String query, String dbName) throws SQLException {
        if (!DB_OPEN) createConnection(dbName);
        Statement stmt = c.createStatement();

        // timing unit for creating statements.
        long startNanoInsert = System.nanoTime();
        stmt.executeUpdate(query);
        long endNanoInsert = System.nanoTime();
        lastExecTimeInsert += (endNanoInsert - startNanoInsert);

        stmt.close();
    }

    /*
      Method for obtaining the test results of the tool, such that they can be emailed onwards.
      This method creates a temporary file within user space to store the .csv output of the
      executed SQL:
      SELECT cypher, sql, neot, pgt FROM query_mapping
      This file is deleted once it has been sent via email.

      @param dbName        Database name test results are stored on (same db as translation also stored on).
     * @param typeTranslate If equal to -t, then normal translation. If -tc, then the transitive closure method
     *                      has been used.
     * @return String in HTML format to email.
     * @throws SQLException Error in th SQL execution.
    public static String getTestResults(String dbName, String typeTranslate) throws SQLException {
        if (!DB_OPEN) RelDBDriver.createConnection(dbName);
        String query = "SELECT cypher, sql, neot, pgt FROM query_mapping";
        PreparedStatement stmt = c.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        PrintWriter writer;
        try {
            writer = new PrintWriter(C2SMain.props.getCsvStats(), "UTF-8");
            writer.println("cypher,sql,neot,pgt");

            while (rs.next()) {
                writer.println("\"" + rs.getString(1).replace("\"", "'") + "\",\""
                        + rs.getString(2) + "\"," + rs.getDouble(3) + ","
                        + rs.getDouble(4));
            }

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String translationMechanism = (typeTranslate.equals("-t")) ?
                "standard" : (typeTranslate.equals("-tc")) ? "with transitive closure" : "unknown/broken.";

        String html = "<html><head><title>Test Results Summary!</title><style>table, th, td " +
                "{border: 1px solid black; border-collapse: collapse;}</style></head>" +
                "<body>Type of translation : " + translationMechanism + "</br>";
        html = html + "<table style=\"width:100%\"><tr><th>Cypher</th>" +
                "<th>Neo4J Average Time</th><th>Neo4J STDDEV</th>" +
                "<th>Postgres Average Time</th><th>Postgres STDDEV</th></tr>";

        query = "SELECT cypher AS Query, avg(neoT) AS Neo4J_Avg_Exec, stddev(neoT) AS Neo4J_stddev, " +
                "avg(pgt) AS Postgres_Avg_Exec, stddev(pgt) AS Postgres_stddev FROM query_mapping " +
                "GROUP BY cypher ORDER BY avg(neoT) DESC;";
        stmt = c.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()) {
            html = html + "<tr>";
            html = html + "<td>" + rs.getString(1) + "</td>";
            html = html + "<td>" + rs.getDouble(2) + "</td>";
            html = html + "<td>" + rs.getDouble(3) + "</td>";
            html = html + "<td>" + rs.getDouble(4) + "</td>";
            html = html + "<td>" + rs.getDouble(5) + "</td>";
            html = html + "</tr>";
        }

        html = html + "</table></body></html>";
        return html;
    }
    */

    /**
     * Clear the contents of the current query_mapping relation for a new test run.
     *
     * @param dbName Name of the database for which the test results are being cleared from.
     */
    public static void clearTestContents(String dbName) {
        try {
            RelDBDriver.insertOrDelete("DELETE FROM query_mapping", dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RelDBDriver.lastExecTimeInsert = 0;
    }
}
