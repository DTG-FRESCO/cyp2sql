package production;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

class C2SBenchmark {
    C2SBenchmark() {
    }

    boolean queryTest(String originalCypherInput, File f_cypher, File f_sql, String dbName,
                      String propsLocation) {
        C2SMain.props = new C2SProperties(propsLocation);
        C2SMain.printBool = true;
        C2SMain.getLabelMapping();
        C2SMain.warmUpResetSSL();
        try {
            C2SMain.translateCypherToSQL(originalCypherInput, f_cypher, f_sql, 1, dbName);
        } catch (Exception e) {
            System.err.println("*** Error with the last query... ***");
            e.printStackTrace();
            return false;
        }
        try {
            return FileUtils.contentEquals(f_cypher, f_sql);
        } catch (IOException e) {
            System.err.println("*** Error comparing outputs from files. ***");
            e.printStackTrace();
            return false;
        }
    }
}
