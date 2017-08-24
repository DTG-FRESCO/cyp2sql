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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class for dealing with the basic properties associated with the application. View the
 * documentation for the getLocalProperties() method for more detailed information.
 */
public class C2SProperties {
    // Some Neo4j fields need to be adapted to handle lists. Currently, this requires the
    // user to manually outline the fields that *may* contain lists. These are stored
    // in this array (they are read from a file titled lists.txt).
    public static ArrayList<String> listFields;
    private String propsLocation;
    private String neo4jSchema;
    private String wspace;
    private String neo4jRes;
    private String sqlRes;
    private String neoUN;
    private String neoPW;
    private String postUN;
    private String postPW;

    /**
     * Constructor method. Sets the location of the properties file (c2s_props.properties)
     *
     * @param fileLocation File location of the c2s_props.properties file.
     */
    public C2SProperties(String fileLocation) {
        setPropsLocation(fileLocation);
        getLocalProperties();
    }

    /**
     * Get the properties from the properties file.
     */
    private void getLocalProperties() {
        try {
            // load the properties file.
            Properties prop = new Properties();
            prop.load(new FileInputStream(getPropsLocation()));

            // get the property values.
            setNeo4jSchema(prop.getProperty("neo4jSchema"));
            setWspace(prop.getProperty("workspaceLocation"));
            setNeo4jRes(prop.getProperty("neo4jResults"));
            setSqlRes(prop.getProperty("sqlResults"));
            setNeoUN(prop.getProperty("neo4jUser"));
            setNeoPW(prop.getProperty("neoPW"));
            setPostUN(prop.getProperty("postgresUser"));
            setPostPW(prop.getProperty("postgresPW"));

            // get the fields that need to look out for lists when parsing the schema
            // dump from Neo4j.
            setLists(prop.getProperty("listsLocation"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPropsLocation() {
        return propsLocation;
    }

    private void setPropsLocation(String propsLocation) {
        this.propsLocation = propsLocation;
    }

    public String getNeo4jSchema() {
        return neo4jSchema;
    }

    private void setNeo4jSchema(String neo4jSchema) {
        this.neo4jSchema = neo4jSchema;
    }

    String getNeo4jRes() {
        return neo4jRes;
    }

    private void setNeo4jRes(String neo4jRes) {
        this.neo4jRes = neo4jRes;
    }

    public String getSqlRes() {
        return sqlRes;
    }

    private void setSqlRes(String sqlRes) {
        this.sqlRes = sqlRes;
    }

    public String getNeoUN() {
        return neoUN;
    }

    private void setNeoUN(String neoUN) {
        this.neoUN = neoUN;
    }

    public String getNeoPW() {
        return neoPW;
    }

    private void setNeoPW(String neoPW) {
        this.neoPW = neoPW;
    }

    public String getPostUN() {
        return postUN;
    }

    private void setPostUN(String postUN) {
        this.postUN = postUN;
    }

    public String getPostPW() {
        return postPW;
    }

    private void setPostPW(String postPW) {
        this.postPW = postPW;
    }

    public String getWspace() {
        return wspace;
    }

    private void setWspace(String wspace) {
        this.wspace = wspace;
    }

    /**
     * Reads file at location listLoc, and for each line in the text file, it adds the content
     * to an ArrayList. The file is a list of fields that may contain lists.
     *
     * @param listLoc Path location of the file containing the list of fields that need to be able to handle lists.
     *                This location should be set in the properties file under the heading listsLocation.
     */
    private void setLists(String listLoc) {
        ArrayList<String> fields = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(listLoc))) {
            String line;
            while ((line = br.readLine()) != null) {
                fields.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setListFields(fields);
    }

    public ArrayList<String> getListFields() {
        return listFields;
    }

    private void setListFields(ArrayList<String> listFieldsZ) {
        listFields = listFieldsZ;
    }
}
