package intermediate_rep;

import query_translation.sql.utilities_sql.WithSQL;

/**
 * Class for storing the return fields of a Cypher query.
 */
public class CypReturn {
    private String nodeID;
    private String field;
    private String type;
    private boolean count;
    private boolean collect;
    private String caseString;
    private int posInClause;

    /**
     * Constructor for recording information about the items being returned from the Cypher query.
     * The constructor is called on the individual parts of the return string (the parts separated by
     * commas in the whole clause).
     * Example return clause: ... RETURN count(n) AS cool_count, b.thing, collect(b.other_thing) ...
     *
     * @param id        The id of the return item being stored. In the example above, these include 'n' and 'b'.
     * @param field     The field of the return item being stored. In the example, these are null, 'thing', and
     *                  'other_thing' respectively.
     * @param count_x   If the return item is a count(...), then this is set to true; otherwise it is false.
     * @param collect_x If the return item is a collect(...), then this is set to true; otherwise it is false.
     * @param caseS     If the return item has the format of CASE .. etc., then the whole original CASE string
     *                  in the Cypher query is stored in this field.
     * @param matchC    The MatchClause object associated with the same Cypher query is also needed, so that
     *                  the type of the return item can be calculated (type meaning either the return item is returning
     *                  a node, a relationship, or is part of a longer Cypher query that contains the WITH keyword.)
     * @throws Exception Error in discovering the type of the return item.
     */
    public CypReturn(String id, String field, boolean count_x, boolean collect_x, String caseS, MatchClause matchC)
            throws Exception {
        this.nodeID = id;
        this.field = field;
        this.count = count_x;
        this.collect = collect_x;
        this.caseString = caseS;

        if (this.nodeID != null) {
            // discoverType finds out whether we are returning a node or a
            // relationship.
            this.type = discoverType(this.nodeID, matchC);
        } else {
            // only case of null id is when returning *, which for now is presumed as returning nodes only.
            this.type = "node";
            this.posInClause = 1;
        }
    }

    /**
     * Method for calculating the type of the return item. The type is calculated from the id of the return item
     * and the MatchClause object of the Cypher input.
     * <p>
     * Type may be one of three:
     * - node
     * - rel
     * - withNode (the node does not appear in the MatchClause object presented, but is a member of a different
     * MatchClause object contained within an earlier part of the same Cypher input. This is true in the cases
     * where WITH is used to split longer queries into smaller parts).
     *
     * @param idReturnItem The id of the return item for which the type is being calculated for.
     * @param matchC       The MatchClause object for the Cypher input.
     * @return String - one of ['node', 'rel', 'withNode']
     * @throws Exception The return item is not a node, relationship, or part of a longer Cypher input, and therefore
     *                   must be invalid.
     */
    private String discoverType(String idReturnItem, MatchClause matchC) throws Exception {
        //check the nodes first
        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(idReturnItem)) {
                posInClause = cN.getPosInClause();
                return "node";
            }
        }

        //check relationships
        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(idReturnItem)) {
                posInClause = cR.getPosInClause();
                return "rel";
            }
        }

        // quite experimental code - used with the keyword WITH
        for (String s : WithSQL.withMapping.keySet()) {
            if (s.equals(idReturnItem)) {
                posInClause = -1;
                return "withNode";
            }
        }

        throw new Exception("RETURN DISCOVER TYPE INCORRECT - NOT A NODE OR REL");
    }

    public String getNodeID() {
        return nodeID;
    }

    public String getField() {
        return field;
    }

    public boolean getCount() {
        return count;
    }

    public boolean getCollect() {
        return collect;
    }

    public String getCaseString() {
        return caseString;
    }

    @Override
    public String toString() {
        return "(ID: " + this.nodeID +
                ", FIELD: " + this.field + ", TYPE: " + this.type + ", POS: " + this.getPosInClause() +
                ", COUNT: " + this.count +
                ", COLLECT: " + this.collect + ", CASE: " + this.caseString + ")";
    }

    public String getType() {
        return type;
    }

    public int getPosInClause() {
        return posInClause;
    }
}
