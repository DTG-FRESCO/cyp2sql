package production;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class C2SInteractive {
    static ArrayList<String> lastFiveInputs = new ArrayList<>();

    static void run(File f_cypher, File f_sql, String dbName) {
        System.out.println("Cypher to SQL Translator Tool v1.0");
        System.out.println("To exit, type :exit.");
        System.out.println("For help, type :help.");
        System.out.println("To view last inputs, type #.");


        while (true) {
            System.out.println("\nType query, :help, or :exit.");
            System.out.print("c2s> ");
            Scanner in = new Scanner(System.in);
            String resp = in.nextLine();
            if (resp.isEmpty()) continue;
            if (resp.equals(":exit")) break;
            if (resp.equals(":help")) showHelp();
            else {
                try {
                    if (resp.equals("#")) resp = choosePrev();
                    try {
                        C2SMain.getLabelMapping();
                        C2SMain.warmUpResetSSL();
                    } catch (Exception e) {
                        System.err.println("Error setting up the tool...");
                        e.printStackTrace();
                    }
                    C2SMain.translateCypherToSQL(resp, f_cypher, f_sql, 1, dbName);
                    lastFiveInputs.add(resp);
                } catch (Exception e) {
                    System.err.println("Error with the last query...");
                    e.printStackTrace();
                }
            }
        }
    }

    private static String choosePrev() throws Exception {
        if (lastFiveInputs.isEmpty()) throw new Exception("No previous queries to choose from.");
        int index = 0;
        for (String s : lastFiveInputs) {
            System.out.println(String.valueOf(index++) + ": " + s);
        }
        System.out.print("c2s> ");
        Scanner in = new Scanner(System.in);
        return lastFiveInputs.get(Integer.parseInt(in.nextLine()));
    }

    private static void showHelp() {
        System.out.println("Manual in pipeline...");
    }
}
