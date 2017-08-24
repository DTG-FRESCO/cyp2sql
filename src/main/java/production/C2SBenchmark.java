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
        C2SProperties props = new C2SProperties(propsLocation);
        C2SMain.printBool = true;
        C2SMain.getLabelMapping(props);
        C2SMain.warmUpResetSSL(props);

        // translate the Cypher
        try {
            C2SMain.translateCypherToSQL(originalCypherInput, f_cypher, f_sql, dbName, true, props);
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
