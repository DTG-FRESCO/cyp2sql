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

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class C2SBenchmarkTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    private ArrayList<String> testQueries = new ArrayList<>();

    /**
     * Main testing method for queries. The method will output to the console the results of the
     * tests. Each query will be tested even if earlier ones fail. The default database name is
     * set to "testa" in this method. The testing framework is JUnit.
     *
     * @throws Exception Error running the query translation code.
     */
    @Test
    public void allQueriesTest() throws Exception {
        boolean run = true;
        if (run) {
            // Using JUnit, two files are created in temp storage to hold the results outputted from both databases.
            File f_neo4j = folder.newFile("neo4j_results.txt");
            File f_postg = folder.newFile("postg_results.txt");

            // location of the properties file.
            String propsLocation = System.getProperty("user.dir") + "/c2s_props.properties";

            // helper method to obtain queries from a local text file and store them into the JVM.
            populateQueries();
            String dbName = "testa";

            for (String cypher : testQueries) {
                C2SBenchmark bench = new C2SBenchmark();
                collector.checkThat(cypher, true,
                        CoreMatchers.equalTo(bench.queryTest(cypher, f_neo4j, f_postg, dbName, propsLocation)));
            }
        } else System.out.println("ignoring tests...");
    }

    /**
     * Helped method to take the queries from the test file, and store them in an ArrayList,
     * so that the test method can iterate over all of them.
     */
    private void populateQueries() {
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir") +
                            "\\src\\test\\java\\production\\test_queries.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) testQueries.add(line);
            br.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
