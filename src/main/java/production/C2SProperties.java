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
    private String propsLocation;
    private String neo4jSchema;
    private String queriesFile;
    private String wspace;
    private String neo4jRes;
    private String sqlRes;
    private String neoUN;
    private String neoPW;
    private String postUN;
    private String postPW;

    // Some Neo4j fields need to be adapted to handle lists. Currently, this requires the
    // user to manually outline the fields that *may* contain lists. These are stored
    // in this array (they are read from a file titled lists.txt).
    private ArrayList<String> listFields;

    /**
     * Constructor method. Sets the location of the properties file (c2s_props.properties)
     *
     * @param fileLocation File location of the c2s_props.properties file.
     */
    C2SProperties(String fileLocation) {
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
            setQueriesFile(prop.getProperty("queriesFile"));
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

    String getNeo4jSchema() {
        return neo4jSchema;
    }

    private void setNeo4jSchema(String neo4jSchema) {
        this.neo4jSchema = neo4jSchema;
    }

    String getQueriesFile() {
        return queriesFile;
    }

    private void setQueriesFile(String queriesFile) {
        this.queriesFile = queriesFile;
    }

    String getNeo4jRes() {
        return neo4jRes;
    }

    private void setNeo4jRes(String neo4jRes) {
        this.neo4jRes = neo4jRes;
    }

    String getSqlRes() {
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

    private void setLists(String listLoc) {
        ArrayList<String> fields = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(listLoc))) {
            String line;
            while ((line = br.readLine()) != null) {
                fields.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setListFields(fields);
    }

    public ArrayList<String> getListFields() {
        return listFields;
    }

    private void setListFields(ArrayList<String> listFields) {
        this.listFields = listFields;
    }
}
