package query_translation.sql.conversion_types;

import com.google.gson.JsonObject;
import intermediate_rep.CypIterate;
import intermediate_rep.CypNode;
import intermediate_rep.DecodedQuery;
import query_translation.sql.utilities_sql.SQLTranslate;

/**
 * Class for dealing with the ITERATE keyword. NOTE: this is currently not an accepted keyword
 * in Cypher, but was added as an extension of this translator tool to show the possibilities
 * of the program. More details can be found in the documentation.
 */
public class Iterate_Cypher extends AbstractConversion {
    private static int calculatePos(String loopIndexFrom, DecodedQuery loopDQ) {
        for (CypNode cN : loopDQ.getMc().getNodes()) {
            if (cN.getId().equals(loopIndexFrom)) return cN.getPosInClause();
        }
        return -1;
    }

    @Override
    public String convertQuery(String cypher) {
        CypIterate cypIter = new CypIterate(cypher);

        String line = cypIter.getOriginalCypherInput();
        String matchClause = line.substring(8, line.indexOf("loop"));

        line = line.split(" loop ")[1];
        cypIter.setLoopIndexTo(line.split(" ")[0]);
        line = line.split(" on ")[1];
        cypIter.setLoopIndexFrom(line.split(" ")[0]);
        line = line.split(" collect ")[1];
        cypIter.setCollectIndex(line.split(" ")[0]);
        line = line.split(" return ")[1];
        cypIter.setReturnStatement(line);

        String firstStep = matchClause + "return " + cypIter.getLoopIndexTo() + ";";
        cypIter.setFirstQuery(firstStep);

        // get correct loop query
        int posOfLoopFrom = firstStep.indexOf(cypIter.getLoopIndexFrom());
        String lQuery = "MATCH " + firstStep.substring(posOfLoopFrom - 1, firstStep.length());
        cypIter.setLoopQuery(lQuery);

        // generate the traditional translation to SQL for the loop query (store in string as used
        // multiple times)
        DecodedQuery loopDQ = AbstractConversion.convertCypherToSQL(cypIter.getFirstQuery());
        String loopSQL = loopDQ.getSqlEquiv();

        String returnSQL = AbstractConversion.convertCypherToSQL(cypIter.getReturnStatement()).getSqlEquiv();
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

        loopDQ = AbstractConversion.convertCypherToSQL(cypIter.getLoopQuery());
        int posLoopFrom = calculatePos(cypIter.getLoopIndexFrom(), loopDQ);
        JsonObject obj = loopDQ.getMc().getNodes().get(posLoopFrom - 1).getProps();
        if (obj == null) {
            obj = new JsonObject();
        }
        obj.addProperty("id", "ANY($1)");
        loopDQ.getMc().getNodes().get(posLoopFrom - 1).setProps(obj);

        try {
            loopDQ.setSqlEquiv(SQLTranslate.translateRead(loopDQ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainParts = loopDQ.getSqlEquiv().split("SELECT n01\\.\\*");
        loopWorkStr = mainParts[0];
        loopWorkStr += " SELECT array_agg(n01.id)";
        loopWorkStr += mainParts[1].substring(0, mainParts[1].length() - 1);

        String functionLoop = "CREATE OR REPLACE FUNCTION loop_work(int[]) RETURNS int[] AS $$ " + loopWorkStr +
                " $$ LANGUAGE SQL;";

        cypIter.setSQL(functionLoop + " " + mainInitStmt);

        // the iterate function should always be persistent on the database and shouldn't need modification.
        return cypIter.getSQL();
    }
}
