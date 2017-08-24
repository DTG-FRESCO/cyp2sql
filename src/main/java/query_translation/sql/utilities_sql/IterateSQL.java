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

package query_translation.sql.utilities_sql;

import com.google.gson.JsonObject;
import exceptions.DQInvalidException;
import intermediate_rep.CypIterate;
import intermediate_rep.CypNode;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;
import query_translation.sql.conversion_types.AbstractConversion;

/**
 * Method for translating Cypher with FOREACH into SQL.
 */
public class IterateSQL extends AbstractTranslation {
    private static int calculatePos(String loopIndexFrom, DecodedQuery loopDQ) {
        for (CypNode cN : loopDQ.getMc().getNodes()) {
            if (cN.getId().equals(loopIndexFrom)) return cN.getPosInClause();
        }
        return -1;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery dQ, C2SProperties props) throws DQInvalidException {
        // get correct loop query
        CypIterate ci = dQ.getIterate();
        String firstStep = ci.getFirstQuery();
        int posOfLoopFrom = firstStep.indexOf(ci.getLoopIndexFrom());
        String lQuery = "MATCH " + firstStep.substring(posOfLoopFrom - 1, firstStep.length());
        ci.setLoopQuery(lQuery);

        // generate the traditional translation to SQL for the loop query (store in string as used
        // multiple times)
        DecodedQuery loopDQ = AbstractConversion.genDQAndSQL(ci.getFirstQuery(), props);
        String loopSQL = loopDQ.getSqlEquiv();

        String returnSQL = AbstractConversion.genDQAndSQL(ci.getReturnStatement(), props).getSqlEquiv();
        returnSQL = returnSQL.substring(0, returnSQL.length() - 1);

        // need to modify loopSQL for the main SQL statement.
        String[] mainParts = loopSQL.split("SELECT n01\\.\\*");
        String mainInitStmt = mainParts[0].trim() + ", firstStep AS (SELECT (array_agg(n01.id)) AS list_ids ";
        mainInitStmt = mainInitStmt + mainParts[1].substring(0, mainParts[1].length() - 1) + "),  ";
        mainInitStmt = mainInitStmt + "collectStep AS (SELECT unnest((cypher_iterate(firstStep.list_ids))) " +
                "AS zz from firstStep) ";
        mainInitStmt = mainInitStmt + returnSQL + " INNER JOIN collectStep c ON n01.id = c.zz;";

        // create the loop_work function with this string
        String loopWorkStr;

        loopDQ = AbstractConversion.genDQAndSQL(ci.getLoopQuery(), props);
        int posLoopFrom = calculatePos(ci.getLoopIndexFrom(), loopDQ);
        JsonObject obj = loopDQ.getMc().getNodes().get(posLoopFrom - 1).getProps();
        if (obj == null) {
            obj = new JsonObject();
        }
        obj.addProperty("id", "ANY($1)");
        loopDQ.getMc().getNodes().get(posLoopFrom - 1).setProps(obj);

        try {
            loopDQ.setSqlEquiv(SQLTranslate.translateRead(loopDQ, props));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainParts = loopDQ.getSqlEquiv().split("SELECT n01\\.\\*");
        loopWorkStr = mainParts[0];
        loopWorkStr += " SELECT array_agg(n01.id)";
        loopWorkStr += mainParts[1].substring(0, mainParts[1].length() - 1);

        String functionLoop = "CREATE OR REPLACE FUNCTION loop_work(int[]) RETURNS int[] AS $$ " + loopWorkStr +
                " $$ LANGUAGE SQL;";

        ci.setSQL(functionLoop + " " + mainInitStmt);

        // the iterate function should always be persistent on the database and shouldn't need modification.
        return new StringBuilder(ci.getSQL());
    }
}
