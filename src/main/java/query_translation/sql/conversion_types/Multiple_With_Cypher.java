package query_translation.sql.conversion_types;

import intermediate_rep.DecodedQuery;
import production.C2SProperties;
import query_translation.sql.utilities_sql.WithSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for dealing with the WITH keyword in Cypher (in the specific cases of more than one WITH).
 */
public class Multiple_With_Cypher extends AbstractConversion {
    public static Map<String, String> mappingMultipleWith;

    @Override
    public String convertQuery(String cypher, C2SProperties props) {
        mappingMultipleWith = new HashMap<>();

        ArrayList<String> withParts = new ArrayList<>();
        String cypherCopy = cypher.toLowerCase();

        while (!cypherCopy.isEmpty()) {
            int lastMatchIndex = cypherCopy.lastIndexOf("match");
            withParts.add(cypherCopy.substring(lastMatchIndex, cypherCopy.length() - 1) + ";");
            cypherCopy = cypherCopy.substring(0, lastMatchIndex);
        }

        int numParts = withParts.size();
        StringBuilder multipleWithSQL = new StringBuilder();

        for (int i = 0; i < numParts - 1; i++) {
            String withQuery = withParts.get(numParts - (i + 1)).replace(" with ", " return ");
            DecodedQuery withPartDQ = convertCypherToSQL(withQuery, props);
            mappingMultipleWith.put(withPartDQ.getRc().getItems().get(0).getNodeID(),
                    "w" + String.valueOf(alphabet[i]).toUpperCase());
            multipleWithSQL.append(WithSQL.genTemp(withPartDQ.getSqlEquiv(), i, withPartDQ)).append(" ");
        }

        // System.out.println(multipleWithSQL);
        DecodedQuery finalPartDQ = convertCypherToSQL(withParts.get(0), props);
        String finalSQL = finalPartDQ.getSqlEquiv();

        // if ORDER BY and/or LIMIT/SKIP have been appended, temporarily remove them, then add back after
        // the code block below.

        String tempEnd = "";
        if (finalSQL.contains("ORDER")) {
            tempEnd = " " + finalSQL.substring(finalSQL.indexOf("ORDER"));
            finalSQL = finalSQL.substring(0, finalSQL.indexOf("ORDER") + 1).trim();
        }

        String letter = String.valueOf(alphabet[numParts - 2]).toUpperCase();
        if (finalPartDQ.getMc().getRels().size() > 0) {
            int indexLastWhere = finalSQL.lastIndexOf("WHERE");
            finalSQL = finalSQL.substring(0, indexLastWhere) + ", w" + letter + " WHERE " +
                    finalSQL.substring(indexLastWhere + 6);
            finalSQL = finalSQL.substring(0, finalSQL.length() - 1);
            String correctPart = (mappingMultipleWith.keySet().contains(finalPartDQ.getMc().getNodes().get(0).getId()))
                    ? "a.a1" : "a.a2";
            finalSQL = finalSQL + " AND " + correctPart + " = w" + letter + ".id";
        } else finalSQL = finalSQL.replace(" nodes n01 ", " nodes n01, w" + letter + " ");

        multipleWithSQL.append(finalSQL).append(tempEnd);
        if (!multipleWithSQL.toString().endsWith(";")) multipleWithSQL.append(";");
        mappingMultipleWith = null;
        return multipleWithSQL.toString();
    }
}
