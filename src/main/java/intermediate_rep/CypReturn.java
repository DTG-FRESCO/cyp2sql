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

    public CypReturn(String id, String f, boolean count_x, boolean collect_x, String caseS, MatchClause matchC)
            throws Exception {
        this.nodeID = id;
        this.field = f;
        this.count = count_x;
        this.collect = collect_x;
        this.caseString = caseS;

        if (this.nodeID != null) {
            // discoverType finds out whether we are returning a node or a
            // relationship.
            this.type = discoverType(this.nodeID, matchC);
        } else {
            // only case of null id is when returning *
            // for v2 this involves only returning nodes.
            this.type = "node";
            this.posInClause = 1;
        }
    }

    private String discoverType(String nodeID, MatchClause matchC) throws Exception {
        //check the nodes first
        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null && cN.getId().equals(nodeID)) {
                posInClause = cN.getPosInClause();
                return "node";
            }
        }

        //check relationships
        for (CypRel cR : matchC.getRels()) {
            if (cR.getId() != null && cR.getId().equals(nodeID)) {
                posInClause = cR.getPosInClause();
                return "rel";
            }
        }

        // quite experimental code!
        for (String s : WithSQL.withMapping.keySet()) {
            if (s.equals(nodeID)) {
                posInClause = -1;
                return "withNode";
            }
        }

        throw new Exception("RETURN DISCOVER TYPE INCORRECT - NOT AN NODE OR REL");
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
