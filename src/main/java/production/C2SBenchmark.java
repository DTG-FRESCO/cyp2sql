package production;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Class called by the JUnit testing framework when testing queries for correctness of output. Only testing
 * frameworks should use this class.
 */
class C2SBenchmark {
    C2SBenchmark() {
    }

    /**
     * queryTest is the method that combines the translation of Cypher with the checking of the outputs.
     *
     * @param originalCypherInput The Cypher input to be converted.
     * @param f_cypher            The file to store the results from Neo4j in to.
     * @param f_sql               The file to store the results from Postgres/other relational backend in to.
     * @param dbName              The name of the relational database to execute the SQL on.
     * @param propsLocation       The file location of the c2s_props.properties file.
     * @return True if the translation was successful, false otherwise.
     */
    boolean queryTest(String originalCypherInput, File f_cypher, File f_sql, String dbName, String propsLocation) {
        // setup the translation tool
        C2SMain.props = new C2SProperties(propsLocation);
        C2SMain.printBool = true;
        C2SMain.getLabelMapping();
        C2SMain.warmUpResetSSL();

        // translate the Cypher
        try {
            C2SMain.translateCypherToSQL(originalCypherInput, f_cypher, f_sql, 1, dbName, true);
        } catch (Exception e) {
            System.err.println("*** Error with the last query... ***");
            e.printStackTrace();
            return false;
        }

        // check the outputs to see if they are the same
        try {
            return FileUtils.contentEquals(f_cypher, f_sql);
        } catch (IOException e) {
            System.err.println("*** Error comparing outputs from files. ***");
            e.printStackTrace();
            return false;
        }
    }
}
