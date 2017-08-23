package production;

import jline.TerminalFactory;
import jline.console.ConsoleReader;

import java.io.File;
import java.io.IOException;

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
     * @param props    C2SProperties object (should already be initialised).
     * @param dbName   Name of the relational database that the generated SQL will be executed on.
     */
    static void run_debug(File f_cypher, File f_sql, String dbName, C2SProperties props) {
        System.out.println("PRINT TO FILE : " + ((C2SMain.printBool) ? "enabled" : "disabled"));
        System.out.println("Cypher to SQL Translator Tool v1.1");
        System.out.println("To exit, type :exit.");

        try {
            ConsoleReader console = new ConsoleReader();
            console.setPrompt("cyp2sql> ");
            String line;
            while ((line = console.readLine()) != null) {
                if (line.equals(":exit")) break;
                if (line.isEmpty()) continue;
                try {
                    C2SMain.translateCypherToSQL(line, f_cypher, f_sql, dbName, true, props);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for running the tool in its normal operation, where Cypher is inputted into the console, and then
     * the results are outputted to the console, but from the relational database.
     *
     * @param dbName Name of the relational database that the translated query is to be executed on.
     * @param props  C2SProperties object (should already be initialised).
     */
    static void run(String dbName, C2SProperties props) {
        System.out.println("Cypher to SQL Translator Tool v1.1");
        System.out.println("To exit, type :exit.");

        try {
            ConsoleReader console = new ConsoleReader();
            console.setPrompt("cyp2sql> ");
            String line;
            while ((line = console.readLine()) != null) {
                if (line.equals(":exit")) break;
                if (line.isEmpty()) continue;
                try {
                    C2SMain.translateCypherToSQL(line, null, null, dbName, false, props);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
