/*
 * Copyright (c) 2017.
 *
 * Oliver Crawford <o.crawford@hotmail.co.uk>
 * Lucian Carata <lc525@cam.ac.uk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package database.postgres;

import production.C2SMain;
import production.C2SProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database driver for Postgres.
 */
public class PostgresDriver {
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
     * @param props  C2SProperties object (should already be initialised).
     */
    static void createConnection(String dbName, C2SProperties props) {
        try {
            String sqlDBUsername = props.getPostUN();
            String sqlDBPassword = props.getPostPW();
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
     * @param props  C2SProperties object (should already be initialised).
     * @throws SQLException Error with the SQL query being executed.
     */
    public static void executeCreateView(String query, String dbName, C2SProperties props) throws SQLException {
        if (!DB_OPEN) PostgresDriver.createConnection(dbName, props);
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
     * @param props       C2SProperties object (should already be initialised).
     * @throws SQLException Thrown if there is an error in the SQL statement.
     */
    public static int select(String query, String database, File pg_results, boolean printOutput, C2SProperties props)
            throws SQLException {
        if (!DB_OPEN) PostgresDriver.createConnection(database, props);
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
                        if ((!column.equals("id") || C2SMain.needToPrintID) && !column.equals("label")) {
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

        C2SMain.needToPrintID = false;
        C2SMain.numResultsPostgres = numRecords;
        PostgresDriver.closeConnection();
        return numRecords;
    }

    /**
     * Obtain results from the database (along with additional metadata such as the columns
     * returned).
     *
     * @param query SQL to execute.
     * @param stm   Internal JDBC statement.
     * @return List of the results and column names.
     * @throws SQLException Error with the SQL.
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
     * @param props  C2SProperties object (should already be initialised).
     * @throws SQLException Error with the transaction.
     */
    public static void insertOrDelete(String query, String dbName, C2SProperties props) throws SQLException {
        if (!DB_OPEN) createConnection(dbName, props);
        Statement stmt = c.createStatement();

        // timing unit for creating statements.
        long startNanoInsert = System.nanoTime();
        stmt.executeUpdate(query);
        long endNanoInsert = System.nanoTime();
        lastExecTimeInsert += (endNanoInsert - startNanoInsert);

        stmt.close();
    }
}
