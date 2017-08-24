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

package translator;

import parsing_lexing.CypherBaseListener;
import parsing_lexing.CypherParser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static parsing_lexing.CypherParser.*;

/**
 * Class that walks the parse tree of the Cypher input, as parsed by the ANTLRv4 tool.
 * This class records important information about the query.
 */
public class CypherWalker extends CypherBaseListener {
    // Does the RETURN clause of the Cypher input contain the keyword DISTINCT?
    private boolean hasDistinct = false;
    // Does the RETURN clause of the Cypher input contain the keyword COUNT?
    private boolean hasCount = false;
    // Does the RETURN clause of the Cypher input contain the keyword COLLECT?
    private boolean hasCollect = false;
    // Does the RETURN clause of the Cypher input contain the keyword CASE?
    private boolean hasCase = false;
    // Does the Cypher input contain the DELETE keyword (i.e. in the case of DELETE n, r or
    // MATCH (n) DETACH DELETE n
    private boolean hasDelete = false;

    private boolean hasAverage = false;
    private boolean hasSum = false;
    private boolean hasMin = false;
    private boolean hasMax = false;

    // Contains String representation of the MATCH clause of the Cypher input.
    private String matchClause = null;
    // Contains String representation of the WHERE clause of the Cypher input.
    private String whereClause = null;
    // Contains String representation of the RETURN clause of the Cypher input.
    private String returnClause = null;
    // Holds the mapping between return items in the RETURN clause, and their alias value as described
    // in the Cypher query. For example, if the RETURN clause is RETURN a.name AS cool_name, the mapping
    // would be {a.name AS cool_name=cool_name}
    private Map<String, String> returnAlias = new HashMap<>();
    // Contains String representation of the ORDER BY clause of the Cypher input.
    private String orderClause = null;

    // The direction (ASC or DESC) for the ORDER BY clause (default setting is "" when no ORDER BY clause
    // is present in the Cypher input).
    private String latestOrderDirection = "";

    // Amount to SKIP, as set in the Cypher input (-1 if no SKIP keyword present)
    private int skipAmount = -1;
    // Amount to LIMIT, as set in the Cypher input (-1 if no SKIP keyword present)
    private int limitAmount = -1;

    public void enterMatch(CypherParser.MatchContext ctx) {
        // code below is an example of how these methods may be used to obtain further information
        // about the Cypher input if desired.

        //optional keyword attached or not
        //if (ctx.getText().toLowerCase().startsWith("optional "))
        //    hasOptional = true;
    }

    /**
     * Sets matchClause to its correct value.
     *
     * @param ctx See CypherParser.PatternContext for further information.
     */
    public void enterPattern(CypherParser.PatternContext ctx) {
        matchClause = ctx.getText();
    }

    /**
     * Using rule information from the ANTLR generated framework, expressions can be appropaitely parsed.
     * This method sets the SKIP and LIMIT values (if present), the ORDER BY clause, and the WHERE clause.
     *
     * @param ctx See CypherParser.ExpressionContext for further information.
     */
    public void enterExpression(CypherParser.ExpressionContext ctx) {
        // The rule indices can be found in CypherParser.java
        switch (ctx.getParent().getRuleIndex()) {
            case RULE_skip:
                skipAmount = Integer.parseInt(ctx.getText());
                break;
            case RULE_limit:
                limitAmount = Integer.parseInt(ctx.getText());
                break;
            case RULE_sortItem:
                if (orderClause == null) {
                    orderClause = ctx.getText() + " " + latestOrderDirection;
                } else {
                    orderClause += ", " + ctx.getText();
                }
                break;
            case RULE_where:
                if (whereClause == null) whereClause = ctx.getText();
                break;
        }
    }

    /**
     * Method for parsing the RETURN clause of the Cypher input.
     *
     * @param ctx See CypherParser.ReturnXContext for further information.
     */
    public void enterReturnX(CypherParser.ReturnXContext ctx) {
        //distinct keyword attached or not
        if (ctx.getText().toLowerCase().contains(" distinct "))
            hasDistinct = true;
        // is the return query looking at a count
        if (ctx.getText().toLowerCase().contains("count("))
            hasCount = true;
        // is the return query looking at a collect
        if (ctx.getText().toLowerCase().contains("collect("))
            hasCollect = true;
        // is the return query looking at a case expression
        if (ctx.getText().toLowerCase().contains("case"))
            hasCase = true;
        // is the return query looking at a average function
        if (ctx.getText().toLowerCase().contains("avg("))
            hasAverage = true;
        // is the return query looking at a sum function
        if (ctx.getText().toLowerCase().contains("sum("))
            hasSum = true;
        // is the return query looking at a min function
        if (ctx.getText().toLowerCase().contains("min("))
            hasMin = true;
        // is the return query looking at a max function
        if (ctx.getText().toLowerCase().contains("max("))
            hasMax = true;
    }

    /**
     * Set returnClause to its correct value.
     *
     * @param ctx See CypherParser.ReturnItemsContext for further information.
     */
    public void enterReturnItems(CypherParser.ReturnItemsContext ctx) {
        returnClause = ctx.getText();
    }

    /**
     * Adds any alias mappings to returnAlias.
     *
     * @param ctx See CypherParser.VariableContext for further information.
     */
    public void enterVariable(CypherParser.VariableContext ctx) {
        if (ctx.getParent().getRuleIndex() == RULE_returnItem) {
            String returnItem = ctx.getParent().getText();
            String[] varAndAlias = returnItem.toLowerCase().split(" as ");
            returnAlias.put(varAndAlias[0], ctx.getText().toLowerCase());
        }
    }

    /**
     * Sets the value of latestOrderDirection.
     *
     * @param ctx See CypherParser.SortItemContext for further information.
     */
    public void enterSortItem(CypherParser.SortItemContext ctx) {
        String orderByString = ctx.getText().toLowerCase();

        if (orderByString.endsWith("descending") || orderByString.endsWith("desc")) {
            latestOrderDirection = "desc";
        } else if (orderByString.endsWith("ascending") || orderByString.endsWith("asc")) {
            latestOrderDirection = "asc";
        } else latestOrderDirection = "";
    }

    /**
     * Sets the value of hasDelete.
     *
     * @param ctx See CypherParser.DeleteContext for further information.
     */
    public void enterDelete(CypherParser.DeleteContext ctx) {
        hasDelete = true;
    }

    /**
     * Prints information about the CypherWalker object. Debugging purposes only.
     */
    void printInformation() {
        System.out.println("\n--- QUERY INFORMATION ---");
        if (matchClause != null) System.out.println("Match Clause: " + matchClause);
        if (whereClause != null) System.out.println("Where Clause: " + whereClause);
        if (returnClause != null)
            System.out.println("Return Clause: " + returnClause + " -- DISTINCT = " + hasDistinct);
        if (!returnAlias.isEmpty())
            System.out.println("Return Alias: " + returnAlias);
        if (orderClause != null) System.out.println("Order Clause: " + orderClause);
        if (skipAmount != -1) System.out.println("Skip Amount: " + skipAmount);
        if (limitAmount != -1) System.out.println("Limit Amount: " + limitAmount);
        System.out.println("\n");
    }

    int getSkipAmount() {
        return skipAmount;
    }

    int getLimitAmount() {
        return limitAmount;
    }

    boolean doesCluaseHaveWhere() {
        return (whereClause != null);
    }

    String getWhereClause() {
        return whereClause;
    }

    String getOrderClause() {
        return orderClause;
    }

    public boolean hasDistinct() {
        return hasDistinct;
    }

    public boolean hasCount() {
        return hasCount;
    }

    boolean hasCase() {
        return hasCase;
    }

    boolean hasCollect() {
        return hasCollect;
    }

    public Map<String, String> getAliasMap() {
        return returnAlias;
    }

    Collection<String> getAlias() {
        return returnAlias.values();
    }

    public boolean hasDelete() {
        return hasDelete;
    }

    boolean hasAverage() {
        return hasAverage;
    }

    boolean hasSum() {
        return hasSum;
    }

    boolean hasMin() {
        return hasMin;
    }

    boolean hasMax() {
        return hasMax;
    }

    public boolean hasAgg() {
        return (hasAverage || hasSum || hasMin || hasMax);
    }
}
