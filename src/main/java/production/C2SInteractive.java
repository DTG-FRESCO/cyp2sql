package production;

import java.io.File;
import java.util.Scanner;

/**
 * Class for running the translation part of the tool through the terminal. This is opposed to running the
 * tool with a file containing a list of Cypher queries.
 */
class C2SInteractive {
    /**
     * Method for running the tool in debug mode, where information about the queries is recorded, and where
     * printing the results to a local file is possible.
     *
     * @param f_cypher File object to store the results from Neo4j.
     * @param f_sql    File object to store the results from Postgres.
     * @param dbName   Name of the relational database that the generated SQL will be executed on.
     */
    static void run_debug(File f_cypher, File f_sql, String dbName) {
        System.out.println("PRINT TO FILE : " + ((C2SMain.printBool) ? "enabled" : "disabled"));
        System.out.println("Cypher to SQL Translator Tool v1.0");
        System.out.println("To exit, type :exit.");

        while (true) {
            System.out.println("\nType query, or :exit.");
            System.out.print("c2s> ");
            Scanner in = new Scanner(System.in);
            String resp = in.nextLine();

            if (resp.isEmpty()) continue;
            if (resp.equals(":exit")) break;
            else {
                try {
                    C2SMain.translateCypherToSQL(resp, f_cypher, f_sql, dbName, true);
                } catch (Exception e) {
                    System.err.println("Error with the last query...");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method for running the tool in its normal operation, where Cypher is inputted into the console, and then
     * the results are outputted to the console, but from the relational database.
     *
     * @param dbName Name of the relational database that the translated query is to be executed on.
     */
    static void run(String dbName) {
        System.out.println("Cypher to SQL Translator Tool v1.0");
        System.out.println("To exit, type :exit.");

        while (true) {
            System.out.println("\nType query, or :exit.");
            System.out.print("c2s> ");
            Scanner in = new Scanner(System.in);
            String resp = in.nextLine();

            if (resp.isEmpty()) continue;
            if (resp.equals(":exit")) break;
            else {
                try {
                    C2SMain.translateCypherToSQL(resp, null, null, dbName, false);
                } catch (Exception e) {
                    System.err.println("Error with the last query...");
                    e.printStackTrace();
                }
            }
        }
    }
}
