package query_translation.sql.conversion_types;

import intermediate_rep.CypIterate;
import production.C2SProperties;
import query_translation.sql.utilities_sql.IterateSQL;

/**
 * Class for dealing with the ITERATE keyword. NOTE: this is currently not an accepted keyword
 * in Cypher, but was added as an extension of this translator tool to show the possibilities
 * of the program. More details can be found in the documentation.
 */
public class Iterate_Cypher extends AbstractConversion {
    @Override
    public String convertQuery(String cypher, C2SProperties props) {
        CypIterate cypIter = new CypIterate(cypher);

        String line = cypIter.getOriginalCypherInput();
        String matchClause = line.substring(8, line.indexOf("LOOP"));

        line = line.split(" LOOP ")[1];
        cypIter.setLoopIndexTo(line.split(" ")[0]);
        line = line.split(" ON ")[1];
        cypIter.setLoopIndexFrom(line.split(" ")[0]);
        line = line.split(" COLLECT ")[1];
        cypIter.setCollectIndex(line.split(" ")[0]);
        line = line.split(" RETURN ")[1];
        cypIter.setReturnStatement(line);

        String firstStep = matchClause + "return " + cypIter.getLoopIndexTo() + ";";
        cypIter.setFirstQuery(firstStep);

        StringBuilder sql = new StringBuilder();
        IterateSQL it = new IterateSQL();
        return it.translate(sql, cypIter, props);
    }
}
