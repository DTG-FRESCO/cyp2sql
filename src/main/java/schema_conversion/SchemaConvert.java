package schema_conversion;

import com.google.gson.JsonParser;
import production.C2SMain;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class that translates the graph database schema that Neo4j uses, into a set of relations
 * that can be executed on a relational database. This includes relations for all the nodes
 * and relationships, as well as more specific ones for each label type and type of
 * relationship. Additional metafiles are created to help the translator and output module
 * of Reagan.
 * <p>
 * The class makes use of parallel processing on the original dump file to optimise speed up.
 * The original dump file from Neo4J needs to be parsed beforehand, as it contains unnecessary
 * line breaks, as well as characters that will not work in SQL. The c2s_props.properties file
 * states the location of the 'work area', which is used as scratch space for this method.
 */
public class SchemaConvert {
    // storing all the labels for nodes and edges.
    public static List<String> nodeRelLabels = Collections.synchronizedList(new ArrayList<>());
    public static List<String> edgesRelLabels = Collections.synchronizedList(new ArrayList<>());

    // storing separate information for types of nodes
    public static Map<String, String> labelMappings = Collections.synchronizedMap(new HashMap<>());

    // storing separate information on the types of relationships
    public static List<String> relTypes = Collections.synchronizedList(new ArrayList<>());

    // workspace area for both nodes and edges
    public static String nodesFile = C2SMain.props.getWspace() + "/nodes.txt";
    public static String edgesFile = C2SMain.props.getWspace() + "/edges.txt";

    // JSON Parser for creating JSON objects from the text files.
    static JsonParser parser = new JsonParser();

    // regex for deciding whether a line is a node or a relationship
    private static String patternForNode = "(_\\d+:.*)";
    static Pattern patternN = Pattern.compile(patternForNode);

    // regex for deciding whether relationship has properties
    private static String patternForRel = "\\{.+\\}";
    static Pattern patternR = Pattern.compile(patternForRel);

    private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Main method for translating the schema.
     *
     * @param file Dump File from Neo4j.
     */
    public static boolean translate(String file) {
        // perform initial preprocess of the file to remove content such as new file markers
        // and other aspects that will break the schema converter.
        // return number of lines if no issue, else -1
        int count = performPreProcessFile(file);
        if (count == -1) return false;
        boolean success = true;

        // number of concurrent threads to work on dump file.
        final int segments = 8;
        final int amountPerSeg = (int) Math.ceil(count / segments);
        ArrayList<String>[] group = new ArrayList[segments];

        for (int i = 0; i < segments; i++) {
            group[i] = new ArrayList<>();
        }

        try {
            // open correctly preparsed file
            FileInputStream fis = new FileInputStream(file.replace(".txt", "_new.txt"));

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;

            int segNum = 0;
            int amountInSeg = 0;

            System.out.println("***SPLITTING FILE INTO SEGMENTS***");
            while ((line = br.readLine()) != null) {
                if (amountInSeg++ <= amountPerSeg) {
                    group[segNum].add(line);
                } else {
                    segNum++;
                    group[segNum].add(line);
                    amountInSeg = 1;
                }
            }

            br.close();
            fis.close();
            System.out.println("\n***SPLITTING COMPLETE***\n");

            // file indicators for the threads to output on.
            String[] files = new String[segments];
            for (int j = 0; j < segments; j++) {
                files[j] = String.valueOf(alphabet[j]);
            }

            Thread[] ts = new Thread[segments];
            for (int i = 0; i < ts.length; i++) {
                ts[i] = new Thread(new PerformWork(group[i], files[i]));
            }

            System.out.println("***PARSING***");
            for (Thread q : ts) {
                q.start();
            }

            int done = 0;
            while (done < ts.length) {
                try {
                    ts[done].join();
                    done++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("***PARSING COMPLETE***\n");

            combineWork(files);

            // remove any duplicates appearing in the ArrayList
            Set<String> hs = new HashSet<>();
            hs.addAll(nodeRelLabels);
            nodeRelLabels.clear();
            nodeRelLabels.addAll(hs);
            hs.clear();

            hs.addAll(edgesRelLabels);
            edgesRelLabels.clear();
            edgesRelLabels.addAll(hs);
            hs.clear();

            hs.addAll(relTypes);
            relTypes.clear();
            relTypes.addAll(hs);
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } finally {
            File f = new File(file.replace(".txt", "_new.txt"));
            //noinspection ResultOfMethodCallIgnored
            f.delete();
        }
        return success;
    }

    /**
     * Preprocess the original file to remove line breaks and to format it
     * correctly for insertion into a relational backend.
     *
     * @param file File location of the dump file.
     * @return Number of lines in the file, or -1 if there was a failure in this method.
     */
    private static int performPreProcessFile(String file) {
        System.out.println("***PRE PROCESSING FILE***");

        FileInputStream fis;
        FileOutputStream fos;
        int count = 0;

        try {
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;

            long totalBytes = new File(file).length();
            long bytesRead = 0;
            int previousPercent = 0;

            fos = new FileOutputStream(file.replace(".txt", "_new.txt"));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String output = "";
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                count++;

                // escape character in SQL (' replaced with '')
                line = line.replace("'", "''");

                if (line.startsWith("create")) {
                    if (firstLine) {
                        firstLine = false;
                    } else {
                        bw.write(output);
                        bw.newLine();
                    }
                    output = line;
                } else if (line.isEmpty()) {
                    // do nothing intentionally
                } else {
                    output += line;
                }

                bytesRead += line.length();
                int percent = (int) (bytesRead * 100 / totalBytes);
                if ((previousPercent + 10) < percent) {
                    System.out.println(percent + "% read.");
                    previousPercent = percent;
                }
            }

            br.close();
            // remove last semi-colon and commit from the dump file.
            bw.write(output.substring(0, output.length() - 7));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        System.out.println("***PRE PROCESSING COMPLETE***\n");
        return count;
    }

    /**
     * Concatenate result of individual threads to one file. One method call does this
     * for both the nodes and relationships.
     *
     * @param files n files resulted from reading initial dump. (where n is the number of files created from
     *              the last step).
     * @throws IOException Error with the text files being written to.
     */
    private static void combineWork(String[] files) throws IOException {
        System.out.println("***COMBINING FILES***");
        OutputStream out = null;
        byte[] buf;

        for (int i = 0; i < 2; i++) {
            String file = (i == 0) ? nodesFile : edgesFile;
            out = new FileOutputStream(file);
            buf = new byte[1024];

            for (String indivFile : files) {
                InputStream in = new FileInputStream(file.replace(".txt", indivFile + ".txt"));
                int b;
                while ((b = in.read(buf)) >= 0) {
                    out.write(buf, 0, b);
                    out.flush();
                }
                in.close();
                File f = new File(file.replace(".txt", indivFile + ".txt"));
                f.delete();
            }
        }
        out.close();

        System.out.println("\n***COMBINING COMPLETE***");
    }
}
