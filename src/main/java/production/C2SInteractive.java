package production;

import java.io.File;
import java.util.Scanner;

/**
 * Class for running the translation part of the tool through the terminal. This is opposed to running the
 * tool with a file containing a list of Cypher queries.
 */
class C2SInteractive {
    static void run_debug(File f_cypher, File f_sql, String dbName) {
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
                    try {
                        C2SMain.getLabelMapping();
                        C2SMain.warmUpResetSSL();
                    } catch (Exception e) {
                        System.err.println("Error setting up the tool...");
                        e.printStackTrace();
                    }
                    C2SMain.translateCypherToSQL(resp, f_cypher, f_sql, 1, dbName, true);
                } catch (Exception e) {
                    System.err.println("Error with the last query...");
                    e.printStackTrace();
                }
            }
        }
    }

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
                    try {
                        C2SMain.getLabelMapping();
                    } catch (Exception e) {
                        System.err.println("Error setting up the tool...");
                        e.printStackTrace();
                    }
                    C2SMain.translateCypherToSQL(resp, null, null, -1, dbName, false);
                } catch (Exception e) {
                    System.err.println("Error with the last query...");
                    e.printStackTrace();
                }
            }
        }
    }
}
