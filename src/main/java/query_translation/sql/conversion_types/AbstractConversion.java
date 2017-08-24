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

package query_translation.sql.conversion_types;

import exceptions.DQInvalidException;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;
import query_translation.sql.utilities_sql.SQLTranslate;
import query_translation.sql.utilities_sql.UnionSQL;
import translator.CypherTokenizer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to extend if wishing to extend scope of Cypher that can be converted.
 */
public abstract class AbstractConversion {
    static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Convert Cypher queries to SQL
     *
     * @param cypher Original Cypher query to translate.
     * @param props  properties object.
     * @return DecodedQuery object containing all the necessary intermediate representation of the Cypher input.
     * @throws DQInvalidException The Cypher could not be translated to a DecodedQuery object.
     */
    public static DecodedQuery genDQAndSQL(String cypher, C2SProperties props) throws DQInvalidException {
        // A DecodedQuery object stores the intermediate representation of the Cypher input.
        // In theory, this same DecodedQuery object could then be translated to different backend implementations.
        DecodedQuery dQ = new DecodedQuery();

        if (cypher.toLowerCase().contains(" union all ")) {
            String[] queries = cypher.toLowerCase().split(" union all ");
            ArrayList<String> unionSQL = new ArrayList<>();

            for (String s : queries) {
                DecodedQuery unionDQ = CypherTokenizer.decode(s, false);
                if (unionDQ == null) throw new DQInvalidException("Failed to translate part of UNION ALL Cypher" +
                        "input: " + s);
                dQ.addToUnionParts(unionDQ);
                try {
                    unionSQL.add(SQLTranslate.translateRead(unionDQ, props));
                } catch (IOException e) {
                    System.err.println("Error reading from /meta_nodeProps.txt metafile...");
                    return null;
                }
            }

            dQ.setSqlEquiv(UnionSQL.genUnion(unionSQL, "UNION ALL"));
        } else if (cypher.toLowerCase().contains(" union ")) {
            String[] queries = cypher.toLowerCase().split(" union ");
            ArrayList<String> unionSQL = new ArrayList<>();

            for (String s : queries) {
                DecodedQuery unionDQ = CypherTokenizer.decode(s, false);
                if (unionDQ == null) throw new DQInvalidException("Failed to translate part of UNION Cypher" +
                        "input: " + s);
                dQ.addToUnionParts(unionDQ);
                try {
                    unionSQL.add(SQLTranslate.translateRead(unionDQ, props));
                } catch (IOException e) {
                    System.err.println("Error reading from /meta_nodeProps.txt metafile...");
                    return null;
                }
            }

            dQ.setSqlEquiv(UnionSQL.genUnion(unionSQL, "UNION"));
        } else {
            dQ = CypherTokenizer.decode(cypher, false);
            if (dQ == null) throw new DQInvalidException("Failed to convert Cypher input to " +
                    "an intermediate representation: " + cypher);

            if (dQ.getRc() != null) {
                // the translation is for a read query.
                try {
                    dQ.setSqlEquiv(SQLTranslate.translateRead(dQ, props));
                } catch (IOException e) {
                    System.err.println("Error reading from /meta_nodeProps.txt metafile...");
                    return null;
                }
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
        return dQ;
    }

    /**
     * Abstract method for converting a DecodedQuery object to SQL.
     *
     * @param dQ
     * @param props
     * @return
     * @throws DQInvalidException
     */
    public abstract String convertToSQL(DecodedQuery dQ, C2SProperties props) throws DQInvalidException;

    /**
     * Abstract method for converting Cypher to a DecodedQuery object.
     *
     * @param cypher
     * @param props
     * @return
     * @throws DQInvalidException
     */
    public abstract DecodedQuery generateDQ(String cypher, C2SProperties props)
            throws DQInvalidException;
}
