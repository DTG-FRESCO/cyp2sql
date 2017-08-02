package query_translation.sql.utilities_sql;

import com.google.gson.JsonObject;
import intermediate_rep.*;

import java.util.ArrayList;
import java.util.Map;

public class MultipleRel extends AbstractTranslation {
    private static boolean needNodeTable = false;

    /**
     * Obtain WITH clause (Common Table Expression) for query with relationships.
     *
     * @param sql    Existing SQL
     * @param matchC Match Clause of the original Cypher query.
     * @param wc     Where Clause of the original Cypher query.
     * @return New SQL.
     */
    private static StringBuilder obtainWithClause(StringBuilder sql, MatchClause matchC, WhereClause wc) {
        sql.append("WITH ");
        int indexRel = 0;

        for (CypRel cR : matchC.getRels()) {
            String withAlias = String.valueOf(alphabet[indexRel]);
            sql.append(withAlias).append(" AS ");
            sql.append("(SELECT n1.id AS ").append(withAlias).append(1).append(", ");
            sql.append("n2.id AS ").append(withAlias).append(2);
            sql.append(", e").append(indexRel + 1).append(".*");

            int posInClause = cR.getPosInClause();
            CypNode c1 = matchC.getNodes().get(posInClause - 1);
            CypNode c2 = matchC.getNodes().get(posInClause);
            String labelC1 = TranslateUtils.getLabelType(c1.getType());
            String labelC2 = TranslateUtils.getLabelType(c2.getType());

            String typeRel = cR.getType();
            if (typeRel == null) {
                typeRel = "edges";
            } else typeRel = "e$" + typeRel;

            switch (cR.getDirection()) {
                case "right":
                    sql.append(" FROM ").append(labelC1).append(" n1 " + "INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idl ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idr = n2.id");
                    sql = obtainWhereInWithClause(cR, matchC, sql, false, indexRel, wc, labelC1, labelC2);
                    break;
                case "left":
                    sql.append(" FROM ").append(labelC1).append(" n1 " + "INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idr ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idl = n2.id");
                    sql = obtainWhereInWithClause(cR, matchC, sql, false, indexRel, wc, labelC1, labelC2);
                    break;
                case "none":
                    sql.append(" FROM ").append(labelC1).append(" n1 INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idl ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idr = n2.id");
                    sql = obtainWhereInWithClause(cR, matchC, sql, true, indexRel, wc, labelC1, labelC2);
                    sql.append("SELECT n1.id AS ").append(withAlias).append(1).append(", ");
                    sql.append("n2.id AS ").append(withAlias).append(2);
                    sql.append(", e").append(indexRel + 1).append(".*");
                    sql.append(" FROM ").append(labelC1).append(" n1 INNER JOIN ").append(typeRel).append(" e")
                            .append(indexRel + 1)
                            .append(" on n1.id = e").append(indexRel + 1).append(".idr ")
                            .append("INNER JOIN ").append(labelC2).append(" n2 on e").append(indexRel + 1)
                            .append(".idl = n2.id");
                    sql = obtainWhereInWithClause(cR, matchC, sql, false, indexRel, wc, labelC1, labelC2);
                    break;
            }

            indexRel++;
        }

        sql.setLength(sql.length() - 2);
        sql.append(" ");
        return sql;
    }

    /**
     * @param cR
     * @param matchC
     * @param sql
     * @param isBiDirectional
     * @param indexRel
     * @param wc
     * @param nodeLabel1
     * @param nodeLabel2
     * @return
     */
    private static StringBuilder obtainWhereInWithClause(CypRel cR, MatchClause matchC, StringBuilder sql,
                                                         boolean isBiDirectional, int indexRel, WhereClause wc,
                                                         String nodeLabel1, String nodeLabel2) {
        boolean includesWhere = false;
        int posOfRel = cR.getPosInClause();

        CypNode leftNode = obtainNode(matchC, posOfRel);
        JsonObject leftProps = leftNode.getProps();
        CypNode rightNode = obtainNode(matchC, posOfRel + 1);
        JsonObject rightProps = rightNode.getProps();
        JsonObject o = cR.getProps();

        if (leftProps != null) {
            sql.append(" WHERE ( ");
            includesWhere = true;
            sql = TranslateUtils.getWholeWhereClause(sql, leftNode, wc, "n1");
            sql.append(") AND ");
        }

        if (rightProps != null) {
            if (!includesWhere) {
                sql.append(" WHERE ( ");
                includesWhere = true;
            } else sql.append(" ( ");

            sql = TranslateUtils.getWholeWhereClause(sql, rightNode, wc, "n2");
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

        if (o != null) {
            if (!includesWhere) {
                sql.append(" WHERE ");
                includesWhere = true;
            }
            sql = TranslateUtils.getWholeWhereClauseRel(sql, cR, wc, "e" + (indexRel + 1));
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

    /**
     * @param returnC     Return Clause of Cypher
     * @param matchC      Match Clause of Cypher
     * @param sql         Original SQL to append more SQL to.
     * @param hasDistinct Does the return clause of Cypher have the distinct keyword.
     * @param alias       Mapping of any alias structures present in the Cypher input.
     * @return New SQL
     * @throws Exception
     */
    private static StringBuilder obtainSelectAndFromClause(ReturnClause returnC, MatchClause matchC,
                                                           StringBuilder sql, boolean hasDistinct,
                                                           Map<String, String> alias) {
        sql.append("SELECT ");
        if (hasDistinct) sql.append("DISTINCT ");
        int nodeTableCount = 0;
        ArrayList<String> nodesSoFar = new ArrayList<>();
        String relsNeeded = "";
        needNodeTable = false;
        for (CypReturn cR : returnC.getItems()) {
            boolean isNode = false;

            if (cR.getNodeID() == null && cR.getField().equals("*")) {
                sql.append("*  ");
                needNodeTable = true;
                nodeTableCount++;
                break;
            }

            if (cR.getCaseString() != null) {
                // if caseNode is false, then the type must be a relationship.
                boolean caseNode = (cR.getType().equals("node"));
                String replacement = caseNode ? "n01." + cR.getField() : "a." + cR.getField();
                String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(), replacement);
                sql.append(caseString).append("  ");
                if (caseNode) {
                    needNodeTable = true;
                    nodeTableCount++;
                } else {
                    relsNeeded = TranslateUtils.addToRelsNeeded(relsNeeded, "a");
                }
                break;
            }

            if (cR.getField() != null && cR.getField().startsWith("count")) {
                String toAdd;
                int posInCluase = cR.getPosInClause();
                if (posInCluase == 1) toAdd = "a1";
                else toAdd = "a2";
                sql.append("count(").append(toAdd).append(")");
                sql.append(TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", cR.getField(), alias))
                        .append(", ");
                needNodeTable = true;
                nodeTableCount++;
                break;
            }

            if (cR.getField() != null && cR.getField().startsWith("collect")) {
                String toAdd;
                int posInCluase = cR.getPosInClause();
                if (posInCluase == 1) toAdd = "a1";
                else toAdd = "a2";
                sql.append("array_agg(").append(toAdd).append(")");
                sql.append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias)).append(", ");
                needNodeTable = true;
                nodeTableCount++;
                break;
            }

            for (CypNode cN : matchC.getNodes()) {
                if (cR.getNodeID().equals(cN.getId())) {
                    String prop = cR.getField();
                    needNodeTable = true;

                    if (!nodesSoFar.contains(cR.getNodeID())) {
                        nodesSoFar.add(cR.getNodeID());
                        nodeTableCount++;
                    }

                    if (cR.getCollect()) sql.append("array_agg(");
                    if (cR.getCount()) sql.append("count(");

                    if (cR.getCaseString() != null) {
                        String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(),
                                "n0" + nodeTableCount + "." + cR.getField());
                        sql.append(caseString).append(", ");
                    } else {
                        if (prop != null) {
                            sql.append("n0").append(nodeTableCount).append(".").append(prop)
                                    .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                    .append(", ");
                        } else {
                            sql.append("n0").append(nodeTableCount)
                                    .append(".*").append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                    .append(", ");
                        }
                    }
                    if (cR.getCollect() || cR.getCount()) {
                        sql.setLength(sql.length() - 2);
                        sql.append(")");
                        sql.append(
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

                        if (cR.getCollect()) sql.append("array_agg(");
                        if (cR.getCount()) sql.append("count(");

                        if (cR.getCaseString() != null) {
                            String caseString = cR.getCaseString().replace(cR.getNodeID() + "." + cR.getField(),
                                    idRel + "." + cR.getField());
                            sql.append(caseString).append(", ");
                        } else {
                            if (field != null) {
                                sql.append(idRel).append(".").append(field)
                                        .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            } else {
                                sql.append(idRel).append(".*")
                                        .append(TranslateUtils.useAlias(cR.getNodeID(), cR.getField(), alias))
                                        .append(", ");
                            }
                        }
                        if (cR.getCollect() || cR.getCount()) {
                            sql.setLength(sql.length() - 2);
                            sql.append(")");
                            if (cR.getCollect())
                                sql.append(
                                        TranslateUtils.useAlias(
                                                "collect(" + cR.getNodeID() + ")", null, alias))
                                        .append(", ");
                            else sql.append(
                                    TranslateUtils.useAlias("count(" + cR.getNodeID() + ")", null, alias))
                                    .append(", ");
                        }
                        break;
                    }
                }
            }
        }

        sql.setLength(sql.length() - 2);

        String table = TranslateUtils.getTable(returnC);

        if (needNodeTable) {
            sql.append(" FROM ");
            for (int k = nodeTableCount; k > 0; k--) {
                sql.append(table).append(" n0").append(k).append(", ");
            }
            sql.append(relsNeeded);
        } else sql.append(" FROM ").append(relsNeeded);

        int numRels = matchC.getRels().size();

        for (int i = 0; i < numRels; i++)
            if (!relsNeeded.contains(String.valueOf(alphabet[i])))
                sql.append(alphabet[i]).append(", ");

        sql.setLength(sql.length() - 2);
        sql.append(" ");
        return sql;
    }

    /**
     * @param sql
     * @param returnC
     * @param matchC
     * @return
     * @throws Exception
     */
    private static StringBuilder obtainWhereClause(StringBuilder sql,
                                                   ReturnClause returnC, MatchClause matchC) {
        sql.append(" WHERE ");
        int numRels = matchC.getRels().size();

        for (int i = 0; i < numRels - 1; i++) {
            sql.append(alphabet[i]).append(".").append(alphabet[i]).append(2);
            sql.append(" = ");
            sql.append(alphabet[i + 1]).append(".").append(alphabet[i + 1]).append(1);
            sql.append(" AND ");
        }

        if ((numRels == 1)) {
            if (matchC.getRels().get(0).getDirection().equals("none")) {
                int posInCl = returnC.getItems().get(0).getPosInClause();
                if (posInCl == 1) return sql.append(" n01.id = a.a1");
                else return sql.append("n01.id = a.a2");
//                if (!returnC.getItems().get(0).getCount()) {
//                    int posInCl = returnC.getItems().get(0).getPosInClause();
//                    if (posInCl == 1) return sql.append(" n01.id = a.a1");
//                    else return sql.append("n01.id = a.a2");
//                } else {
//                    sql.setLength(sql.length() - 7);
//                }
            }
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
                        if (!(cR.getCount() && returnC.getItems().size() > 1)) {
                            if (!nodesSeenSoFar.contains(cR.getNodeID())) {
                                nodesSeenSoFar.add(cR.getNodeID());
                                nodeTableCount++;
                            }

                            int posInClause = cR.getPosInClause();
                            sql.append("n0").append(nodeTableCount).append(".id = ");

                            if (posInClause == 1) {
                                sql.append("a.a1");
                                sql.append(" AND ");
                            } else {
                                sql.append(alphabet[posInClause - 2]).append(".").append(alphabet[posInClause - 2])
                                        .append(2);
                                sql.append(" AND ");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (numRels > 1) {
            for (int i = 0; i < numRels - 1; i++) {
                if (i == 0) {
                    sql.append("a.a1 != b.b2");
                } else {
                    sql.append(alphabet[i - 1]).append(".").append(alphabet[i - 1]).append(2);
                    sql.append(" != ");
                    sql.append(alphabet[i + 1]).append(".").append(alphabet[i + 1]).append(2);
                }
                sql.append(" AND ");
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
                sql.append(a).append(" = ").append(b).append(" AND ");

                // EXPERIMENTAL LOGIC!
                if (numRels > indices[1]) {
                    indices[0]++;
                    indices[1]++;
                    a = alphabet[indices[0] - 1] + "." + alphabet[indices[0] - 1] + "2";
                    b = alphabet[indices[1] - 1] + "." + alphabet[indices[1] - 1] + "2";
                    sql.append(a).append(" != ").append(b).append(" AND ");
                }
            }
        }

        if (sql.toString().endsWith(" AND ")) sql.setLength(sql.length() - 5);
        if (sql.toString().endsWith(" WHERE ")) sql.setLength(sql.length() - 7);
        return sql;
    }

    @Override
    public StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery) {
        sql = obtainWithClause(sql, decodedQuery.getMc(), decodedQuery.getWc());
        sql = obtainSelectAndFromClause(decodedQuery.getRc(), decodedQuery.getMc(), sql,
                decodedQuery.getCypherAdditionalInfo().hasDistinct(),
                decodedQuery.getCypherAdditionalInfo().getAliasMap());
        if (needNodeTable)
            sql = obtainWhereClause(sql, decodedQuery.getRc(), decodedQuery.getMc());
        return sql;
    }
}
