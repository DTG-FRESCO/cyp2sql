package query_translation.sql.conversion_types;

import intermediate_rep.DecodedQuery;
import production.C2SMain;
import query_translation.sql.utilities_sql.SQLTranslate;
import query_translation.sql.utilities_sql.UnionSQL;
import translator.CypherTokenizer;

import java.util.ArrayList;

/**
 * Class to extend if wishing to extend scope of Cypher that can be converted.
 */
public abstract class AbstractConversion {
    /**
     * Convert Cypher queries to SQL
     *
     * @param cypher Original Cypher query to translate.
     * @return DecodedQuery object containing all the necessary intermediate representation of the Cypher input.
     */
    public static DecodedQuery convertCypherToSQL(String cypher) {
        // A DecodedQuery object stores the intermediate representation of the Cypher input.
        // In theory, this same DecodedQuery object could then be translated to different backend implementations.
        DecodedQuery dQ = null;

        try {
            if (cypher.toLowerCase().contains(" union all ")) {
                String[] queries = cypher.toLowerCase().split(" union all ");
                ArrayList<String> unionSQL = new ArrayList<>();

                for (String s : queries) {
                    dQ = CypherTokenizer.decode(s, false);
                    unionSQL.add(SQLTranslate.translateRead(dQ));
                }

                dQ.setSqlEquiv(UnionSQL.genUnion(unionSQL, "UNION ALL"));
            } else if (cypher.toLowerCase().contains(" union ")) {
                String[] queries = cypher.toLowerCase().split(" union ");
                ArrayList<String> unionSQL = new ArrayList<>();

                for (String s : queries) {
                    dQ = CypherTokenizer.decode(s, false);
                    unionSQL.add(SQLTranslate.translateRead(dQ));
                }

                dQ.setSqlEquiv(UnionSQL.genUnion(unionSQL, "UNION"));
            } else {
                dQ = CypherTokenizer.decode(cypher, false);

                if (dQ.getRc() != null) {
                    // the translation is for a read query.
                    dQ.setSqlEquiv(SQLTranslate.translateRead(dQ));
                } else {
                    if (dQ.getCypherAdditionalInfo().hasDelete()) {
                        // the translation is a delete query.
                        dQ.setSqlEquiv(SQLTranslate.translateDelete(dQ));
                    } else {
                        // the translation is an insert query.
                        dQ.setSqlEquiv(SQLTranslate.translateInsert(dQ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        C2SMain.currentDQ = dQ;
        return dQ;
    }

    public abstract String convertQuery(String cypher);
}
