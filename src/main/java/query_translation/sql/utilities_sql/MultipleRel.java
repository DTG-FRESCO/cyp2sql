package query_translation.sql.utilities_sql;

import com.google.gson.JsonObject;
import intermediate_rep.*;
import production.C2SProperties;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class for translating Cypher with multiple relationships to SQL.
 */
public class MultipleRel extends AbstractTranslation {
    private static boolean needNodeTable = false;

    /**
     * Obtain WITH clause (Common Table Expression) for query with relationships.
     *
     * @param matchC Match Clause of the original Cypher query.
     * @return New SQL.
     */
    private static StringBuilder obtainWithClause(MatchClause matchC, C2SProperties props) {
        StringBuilder withSQL = new StringBuilder();
        withSQL.append("WITH ");

        int indexRel = 0;

        // go through each relationship in the MatchClause, and generate the correct CTE to
        // add to the SQL statement.
        for (CypRel cR : matchC.getRels()) {
            String withAlias = String.valueOf(alphabet[indexRel]);
            withSQL.append(withAlias).append(" AS ");
            withSQL.append("(SELECT n1.id AS ").append(withAlias).append(1).append(", ");
            withSQL.append("n2.id AS ").append(withAlias).append(2);
            withSQL.append(", e").append(indexRel + 1).append(".*");

            int posInClause = cR.getPosInClause();
            CypNode c1 = matchC.getNodes().get(posInClause - 1);
            CypNode c2 = matchC.getNodes().get(posInClause);
            String labelC1 = TranslateUtils.getLabelType(c1.getType(), props);
            String labelC2 = TranslateUtils.getLabelType(c2.getType(), props);

            String typeRel = cR.getType();
            if (typeRel == null) {
                typeRel = "edges";
            } else typeRel = "e$" + typeRel;

            switch (cR.getDirection()) {
                case "right":
                    withSQL.append(" FROM ").append(labelC1).append(" n1 " + "INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idl ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idr = n2.id");
                    withSQL = obtainWhereInWithClause(cR, matchC, withSQL, false, indexRel, labelC1, labelC2);
                    break;
                case "left":
                    withSQL.append(" FROM ").append(labelC1).append(" n1 " + "INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idr ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idl = n2.id");
                    withSQL = obtainWhereInWithClause(cR, matchC, withSQL, false, indexRel, labelC1, labelC2);
                    break;
                case "none":
                    withSQL.append(" FROM ").append(labelC1).append(" n1 INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idl ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idr = n2.id");
                    withSQL = obtainWhereInWithClause(cR, matchC, withSQL, true, indexRel, labelC1, labelC2);
                    withSQL.append("SELECT n1.id AS ").append(withAlias).append(1).append(", ");
                    withSQL.append("n2.id AS ").append(withAlias).append(2);
                    withSQL.append(", e").append(indexRel + 1).append(".*");
                    withSQL.append(" FROM ").append(labelC1).append(" n1 INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idr ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idl = n2.id");
                    withSQL = obtainWhereInWithClause(cR, matchC, withSQL, false, indexRel, labelC1, labelC2);
                    break;
            }

            indexRel++;
        }

        withSQL.setLength(withSQL.length() - 2);
        withSQL.append(" ");
        return withSQL;
    }

    /**
     * Augmenting the WITH clauses with the correct predicates (that may be associated with either the nodes
     * or properties of the relationship itself).
     *
     * @param cR              The Cypher relationship: (a)-[b]-{@literal >}(c) for example.
     * @param matchC          The MatchClause: MATCH (a)-[b]-{@literal >}(c) ...
     * @param sql             The current SQL being created.
     * @param isBiDirectional If the relationship has no direction (i.e. -[]- as opposed to {@literal <}-[] and -[]-{@literal >}), then
     *                        need to append UNION ALL to the end of the SQL created.
     * @param indexRel        The position of the relationship within the context of the whole MatchClause.
     * @param nodeLabel1      Label(s) of the left node of the relationship.
     * @param nodeLabel2      Label(s) of the right node of the relationship.
     * @return SQL string with additional information as a result of this method.
     */
    private static StringBuilder obtainWhereInWithClause(CypRel cR, MatchClause matchC, StringBuilder sql,
                                                         boolean isBiDirectional, int indexRel,
                                                         String nodeLabel1, String nodeLabel2) {
        boolean includesWhere = false;
        int posOfRel = cR.getPosInClause();

        CypNode leftNode = obtainNode(matchC, posOfRel);
        JsonObject leftNodeProps = leftNode.getProps();
        CypNode rightNode = obtainNode(matchC, posOfRel + 1);
        JsonObject rightNodeProps = rightNode.getProps();
        JsonObject relProps = cR.getProps();

        if (leftNodeProps != null) {
            sql.append(" WHERE ( ");
            includesWhere = true;
            sql = TranslateUtils.getWholeWhereClause(sql, leftNode, "n1");
            if (sql.toString().endsWith(" and ")) sql.setLength(sql.length() - 5);
            else if (sql.toString().endsWith(" or ")) sql.setLength(sql.length() - 4);
            sql.append(") AND ");
        }

        if (rightNodeProps != null) {
            if (!includesWhere) {
                sql.append(" WHERE ( ");
                includesWhere = true;
            } else sql.append(" ( ");

            sql = TranslateUtils.getWholeWhereClause(sql, rightNode, "n2");
            if (sql.toString().endsWith(" and ")) sql.setLength(sql.length() - 5);
            else if (sql.toString().endsWith(" or ")) sql.setLength(sql.length() - 4);
            sql.append(") AND ");
        }

        if (leftNode.getType() != null && nodeLabel1.equals("nodes")) {
            if (!includesWhere) {
                sql.append(" WHERE ");
                includesWhere = true;
            }
            sql.append("n1.label LIKE ");
            sql.append(TranslateUtils.genLabelLike(leftNode, "n1")).append(" AND ");
        }

        if (rightNode.getType() != null && nodeLabel2.equals("nodes")) {
            if (!includesWhere) {
                sql.append(" WHERE ");
                includesWhere = true;
            }
            sql.append("n2.label LIKE ");
            sql.append(TranslateUtils.genLabelLike(rightNode, "n2")).append(" AND ");
        }

        if (relProps != null) {
            if (!includesWhere) {
                sql.append(" WHERE ");
                includesWhere = true;
            }
            sql = TranslateUtils.getWholeWhereClauseRel(sql, cR, "e" + (indexRel + 1));
            if (sql.toString().endsWith(" and ")) sql.setLength(sql.length() - 5);
            else if (sql.toString().endsWith(" or ")) sql.setLength(sql.length() - 4);
            sql.append(" AND ");
        }

        if (includesWhere) sql.setLength(sql.length() - 5);
        if (isBiDirectional) {
            sql.append(" UNION ALL ");
        } else {
            sql.append("), ");
        }
        return sql;
    }

    /**
     * @param matchC Match Clause of the original Cypher input.
     * @param i      Index in clause for which we wish to obtain the relevant node.
     * @return CypNode in ith position of the clause.
     */
    private static CypNode obtainNode(MatchClause matchC, int i) {
        for (CypNode c : matchC.getNodes()) {
            if (c.getPosInClause() == i) {
                return c;
            }
        }
        return null;
    }

    private static StringBuilder obtainSelectAndFromClause(ReturnClause returnC, MatchClause matchC,
                                                           boolean hasDistinct,
                                                           Map<String, String> alias) {
        StringBuilder safSQL = new StringBuilder();

        safSQL.append("SELECT ");
        if (hasDistinct) safSQL.append("DISTINCT ");

        int nodeTableCount = 0;
        ArrayList<String> nodesSoFar = new ArrayList<>();
        String relsNeeded = "";
        needNodeTable = false;

        for (CypReturn cR : returnC.getItems()) {
            boolean isNode = false;

            if (cR.getCaseString() != null) {
                // if caseNode is false, then the type must be a relationship.
                boolean caseNode = (cR.getType().equals("node"));
                int posInClause = 1;
                if (caseNode) posInClause = cR.getPosInClause();
                String replacement = caseNode ? "n0" + posInClause + "." + cR.getField() : "a." + cR.getField();
                String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(), replacement);
                safSQL.append(caseString).append(", ");
                if (caseNode) {
                    needNodeTable = true;
                    nodeTableCount++;
                } else {
                    relsNeeded = TranslateUtils.addToRelsNeeded(relsNeeded, "a");
                }
            } else {
                boolean usesExistingWith = false;

                for (String z : WithSQL.withMapping.keySet()) {
                    if (cR.getNodeID().equals(z)) {
                        usesExistingWith = true;

                        String prop = cR.getField();
                        if (cR.hasAggFunc()) {
                            safSQL.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
                        }
                        if (cR.getCount() > 0) {
                            safSQL.append("count(");
                            if (cR.getCount() == 2) safSQL.append("distinct ");
                        }
                        if (cR.getCaseString() != null) {
                            String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(),
                                    WithSQL.withMapping.get(z) + "." + cR.getField());
                            safSQL.append(caseString).append(", ");
                        } else {
                            if (prop != null) {
                                safSQL.append(WithSQL.withMapping.get(z)).append(".").append(prop)
                                        .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            } else {
                                safSQL.append(WithSQL.withMapping.get(z))
                                        .append(".*").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            }
                        }
                        if (cR.hasAggFunc() || cR.getCount() > 0) {
                            safSQL.setLength(safSQL.length() - 2);
                            safSQL.append(")");
                            safSQL.append(
                                    TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", cR.getField(), alias))
                                    .append(", ");
                        }
                        break;
                    }
                }

                if (usesExistingWith) continue;

                for (CypNode cN : matchC.getNodes()) {
                    if (cR.getNodeID().equals(cN.getId())) {
                        String prop = cR.getField();
                        needNodeTable = true;

                        if (!nodesSoFar.contains(cR.getNodeID())) {
                            nodesSoFar.add(cR.getNodeID());
                            nodeTableCount++;
                        }

                        if (cR.hasAggFunc()) {
                            safSQL.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
                        }

                        if (cR.getCount() > 0) {
                            safSQL.append("count(");
                            if (cR.getCount() == 2) safSQL.append("distinct ");
                        }

                        if (cR.getCaseString() != null) {
                            String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(),
                                    "n0" + nodeTableCount + "." + cR.getField());
                            safSQL.append(caseString).append(", ");
                        } else {
                            if (prop != null) {
                                safSQL.append("n0").append(nodeTableCount).append(".").append(prop)
                                        .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            } else {
                                safSQL.append("n0").append(nodeTableCount)
                                        .append(".*").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            }
                        }
                        if (cR.hasAggFunc() || cR.getCount() > 0) {
                            safSQL.setLength(safSQL.length() - 2);
                            safSQL.append(")");
                            safSQL.append(
                                    TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", cR.getField(), alias))
                                    .append(", ");
                        }
                        isNode = true;
                        break;
                    }
                }

                // must be a relationship being returned.
                if (!isNode) {
                    for (CypRel cRel : matchC.getRels()) {
                        if (cRel.getId() != null && cRel.getId().equals(cR.getNodeID())) {
                            String field = cR.getField();
                            int relPos = cRel.getPosInClause();
                            String idRel = String.valueOf(alphabet[relPos - 1]);
                            relsNeeded = TranslateUtils.addToRelsNeeded(relsNeeded, idRel);

                            if (cR.hasAggFunc()) {
                                safSQL.append(CypAggFuncs.sqlEquiv(cR.getAggFunc()));
                            }
                            if (cR.getCount() > 0) safSQL.append("count(");

                            if (cR.getCaseString() != null) {
                                String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(),
                                        idRel + "." + cR.getField());
                                safSQL.append(caseString).append(", ");
                            } else {
                                if (field != null) {
                                    safSQL.append(idRel).append(".").append(field)
                                            .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                            .append(", ");
                                } else {
                                    safSQL.append(idRel).append(".*")
                                            .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                            .append(", ");
                                }
                            }
                            if (cR.hasAggFunc() || cR.getCount() > 0) {
                                safSQL.setLength(safSQL.length() - 2);
                                safSQL.append(")");
                                if (cR.hasAggFunc())
                                    safSQL.append(
                                            TranslateUtils.useAlias(
                                                    "collect(" + cR.getNodeID() + ")", null, alias))
                                            .append(", ");
                                else safSQL.append(
                                        TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", null, alias))
                                        .append(", ");
                            }
                            break;
                        }
                    }
                }
            }
        }

        safSQL.setLength(safSQL.length() - 2);

        String table = TranslateUtils.getTable(returnC);

        boolean fromAdded = false;
        if (needNodeTable) {
            safSQL.append(" FROM ");
            fromAdded = true;
            for (int k = nodeTableCount; k > 0; k--) {
                safSQL.append(table).append(" n0").append(k).append(", ");
            }
            safSQL.append(relsNeeded);
        } else if (!relsNeeded.isEmpty()) {
            safSQL.append(" FROM ").append(relsNeeded);
            fromAdded = true;
        }

        if (!WithSQL.withMapping.isEmpty()) {
            safSQL.append((!fromAdded) ? " FROM " : " ").append("wA, ");
        }

        int numRels = matchC.getRels().size();

        for (int i = 0; i < numRels; i++)
            if (!relsNeeded.contains(String.valueOf(alphabet[i])))
                safSQL.append(alphabet[i]).append(", ");

        safSQL.setLength(safSQL.length() - 2);
        safSQL.append(" ");
        return safSQL;
    }

    private static StringBuilder obtainWhereClause(ReturnClause returnC, MatchClause matchC,
                                                   boolean partOfWithQ) {
        StringBuilder whereSQL = new StringBuilder();

        whereSQL.append(" WHERE ");
        int numRels = matchC.getRels().size();

        // in the case of only one relationship in the MATCH clause.
        if ((numRels == 1)) {
            if (matchC.getRels().get(0).getDirection().equals("none")) {
                if (returnC.getItems().size() == 2
                        && (!returnC.getItems().get(0).getNodeID().equals(returnC.getItems().get(1).getNodeID()))
                        && returnC.getItems().get(0).getType().equals("node")
                        && returnC.getItems().get(1).getType().equals("node")) {
                    whereSQL.append(" n01.id = a.a1 AND n02.id = a.a2");
                    return whereSQL;
                } else {
                    int posInCl = returnC.getItems().get(0).getPosInClause();
                    if (posInCl == 1) return whereSQL.append(" n01.id = a.a1");
                    else return whereSQL.append("n01.id = a.a2");
                }
            }
        }

        // where there is more than one relationship, the CTEs need to join together appropriately,
        // to make sure the semantics are correct.
        for (int i = 0; i < numRels - 1; i++) {
            whereSQL.append(alphabet[i]).append(".").append(alphabet[i]).append(2);
            whereSQL.append(" = ");
            whereSQL.append(alphabet[i + 1]).append(".").append(alphabet[i + 1]).append(1);
            whereSQL.append(" AND ");

            if (i == 0) {
                whereSQL.append("a.a1 != b.b2");
            } else {
                whereSQL.append(alphabet[i - 1]).append(".").append(alphabet[i - 1]).append(2);
                whereSQL.append(" != ");
                whereSQL.append(alphabet[i + 1]).append(".").append(alphabet[i + 1]).append(2);
            }
            whereSQL.append(" AND ");
        }

        if (partOfWithQ) {
            whereSQL.setLength(whereSQL.length() - 5);
            return whereSQL;
        }

        ArrayList<String> nodeIDS = new ArrayList<>();
        ArrayList<String> crossResults = new ArrayList<>();

        int nullCount = 0;

        for (CypNode cN : matchC.getNodes()) {
            if (cN.getId() != null) {
                if (nodeIDS.contains(cN.getId())) {
                    crossResults.add(nodeIDS.indexOf(cN.getId()) + "," +
                            (nodeIDS.size() + crossResults.size() + nullCount));
                } else nodeIDS.add(cN.getId());
            } else nullCount++;
        }

        int nodeTableCount = 0;
        ArrayList<String> nodesSeenSoFar = new ArrayList<>();

        for (CypReturn cR : returnC.getItems()) {
            if (cR.getType() != null) {
                switch (cR.getType()) {
                    case "node":
                        if (!(cR.getCount() > 0 && returnC.getItems().size() > 1)) {
                            if (!nodesSeenSoFar.contains(cR.getNodeID())) {
                                nodesSeenSoFar.add(cR.getNodeID());
                                nodeTableCount++;
                            }

                            int posInClause = cR.getPosInClause();
                            whereSQL.append("n0").append(nodeTableCount).append(".id = ");

                            if (posInClause == 1) {
                                whereSQL.append("a.a1");
                                whereSQL.append(" AND ");
                            } else {
                                whereSQL.append(alphabet[posInClause - 2]).append(".").append(alphabet[posInClause - 2])
                                        .append(2);
                                whereSQL.append(" AND ");
                            }
                        } else if (cR.getCount() > 0 && cR.getNodeID() != null) {
                            if (!nodesSeenSoFar.contains(cR.getNodeID())) {
                                nodesSeenSoFar.add(cR.getNodeID());
                                nodeTableCount++;
                            }

                            int posInClause = cR.getPosInClause();
                            whereSQL.append("n0").append(nodeTableCount).append(".id = ");

                            if (posInClause == 1) {
                                whereSQL.append("a.a1");
                                whereSQL.append(" AND ");
                            } else {
                                whereSQL.append(alphabet[posInClause - 2]).append(".").append(alphabet[posInClause - 2])
                                        .append(2);
                                whereSQL.append(" AND ");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (!crossResults.isEmpty()) {
            for (String s : crossResults) {
                String[] t = s.split(",");
                int[] indices = new int[2];
                indices[0] = Integer.parseInt(t[0]);
                indices[1] = Integer.parseInt(t[1]);
                String a;
                String b;
                if (indices[0] == 0) a = "a.a1";
                else a = alphabet[indices[0] - 1] + "." + alphabet[indices[0] - 1] + "2";
                b = alphabet[indices[1] - 1] + "." + alphabet[indices[1] - 1] + "2";
                whereSQL.append(a).append(" = ").append(b).append(" AND ");

                // EXPERIMENTAL LOGIC!
                if (numRels > indices[1]) {
                    a = alphabet[indices[0]] + "." + alphabet[indices[0]] + "2";
                    b = alphabet[indices[1]] + "." + alphabet[indices[1]] + "2";
                    whereSQL.append(a).append(" != ").append(b).append(" AND ");
                }
            }
        }

        if (whereSQL.toString().endsWith(" AND ")) whereSQL.setLength(whereSQL.length() - 5);
        if (whereSQL.toString().endsWith(" WHERE ")) whereSQL.setLength(whereSQL.length() - 7);
        return whereSQL;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery, C2SProperties props) {
        StringBuilder withParts = obtainWithClause(decodedQuery.getMc(), props);

        StringBuilder selectAndFrom = obtainSelectAndFromClause(decodedQuery.getRc(), decodedQuery.getMc(),
                decodedQuery.getCypherAdditionalInfo().hasDistinct(),
                decodedQuery.getCypherAdditionalInfo().getAliasMap());

        StringBuilder where = null;
        if (needNodeTable)
            where = obtainWhereClause(decodedQuery.getRc(), decodedQuery.getMc(), false);
        else if (!WithSQL.withMapping.isEmpty()) {
            if (decodedQuery.getMc().getRels().size() > 1) {
                where = obtainWhereClause(decodedQuery.getRc(), decodedQuery.getMc(), true)
                        .append(" AND wA.id = a.a2");
            } else {
                where = new StringBuilder();
                where.append(" WHERE wA.id = a.a2");
            }
        } else if (decodedQuery.getMc().getRels().size() > 1)
            where = obtainWhereClause(decodedQuery.getRc(), decodedQuery.getMc(), false);

        sql.append(withParts).append(selectAndFrom);
        if (where != null) sql.append(where);
        return sql;
    }
}
