package query_translation.sql.conversion_types;

import exceptions.DQInvalidException;
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
    public String convertToSQL(DecodedQuery dQ, C2SProperties props) throws DQInvalidException {
        mappingMultipleWith = new HashMap<>();

        int numParts = dQ.getWithParts().size();
        StringBuilder multipleWithSQL = new StringBuilder();

        for (int i = 0; i < numParts - 1; i++) {
            String withQuery = dQ.getWithParts().get(numParts - (i + 1)).replace(" with ", " return ");
            DecodedQuery withPartDQ = genDQAndSQL(withQuery, props);
            mappingMultipleWith.put(withPartDQ.getRc().getItems().get(0).getNodeID(),
                    "w" + String.valueOf(alphabet[i]).toUpperCase());
            multipleWithSQL.append(WithSQL.genTemp(withPartDQ.getSqlEquiv(), i, withPartDQ)).append(" ");
        }

        DecodedQuery finalPartDQ = genDQAndSQL(dQ.getWithParts().get(0), props);
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

            if (indexLastWhere == -1)
                finalSQL = finalSQL.substring(0, finalSQL.length() - 1) + ", w" + letter + " WHERE ";
            else {
                finalSQL = finalSQL.substring(0, indexLastWhere) + ", w" + letter + " WHERE " +
                        finalSQL.substring(indexLastWhere + 6);
                finalSQL = finalSQL.substring(0, finalSQL.length() - 1);
            }

            String correctPart = (mappingMultipleWith.keySet().contains(finalPartDQ.getMc().getNodes().get(0).getId()))
                    ? "a.a1" : "a.a2";
            finalSQL = (!finalSQL.endsWith(" WHERE ")) ? finalSQL + " AND " : finalSQL;
            finalSQL = finalSQL + correctPart + " = w" + letter + ".id";
        } else finalSQL = finalSQL.replace(" nodes n01 ", " nodes n01, w" + letter + " ");

        multipleWithSQL.append(finalSQL).append(tempEnd);
        if (!multipleWithSQL.toString().endsWith(";")) multipleWithSQL.append(";");
        mappingMultipleWith = null;
        return multipleWithSQL.toString();
    }

    @Override
    public DecodedQuery generateDQ(String cypher, C2SProperties props) throws DQInvalidException {
        DecodedQuery dQ = new DecodedQuery();

        ArrayList<String> withParts = new ArrayList<>();
        String cypherCopy = cypher.toLowerCase();

        while (!cypherCopy.isEmpty()) {
            int lastMatchIndex = cypherCopy.lastIndexOf("match");
            if (lastMatchIndex == -1)
                throw new DQInvalidException("Original Cypher input looks malformed, " +
                        "or cannot currently be translated, please check.");
            withParts.add(cypherCopy.substring(lastMatchIndex, cypherCopy.length() - 1) + ";");
            cypherCopy = cypherCopy.substring(0, lastMatchIndex);
        }

        dQ.setWithParts(withParts);
        return dQ;
    }
}
