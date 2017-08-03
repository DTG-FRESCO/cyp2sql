package translator;

import parsing_lexing.CypherBaseListener;
import parsing_lexing.CypherParser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that walks the parse tree of the Cypher input, as parsed
 * by the ANTLRv4 tool. This class records important information
 * about the query.
 */
public class CypherWalker extends CypherBaseListener {
    private boolean hasOptional = false;
    private boolean hasDistinct = false;
    private boolean hasCount = false;
    private boolean hasCollect = false;
    private boolean hasCase = false;
    private boolean hasDelete = false;
    private String matchClause = null;
    private String whereClause = null;
    private String returnClause = null;
    private Map<String, String> returnAlias = new HashMap<>();
    private String orderClause = null;
    private String withClause = null;
    private String latestOrderDirection = "";
    private int skipAmount = -1;
    private int limitAmount = -1;

    public void enterMatch(CypherParser.MatchContext ctx) {
        //optional keyword attached or not
        if (ctx.getText().toLowerCase().startsWith("optional "))
            hasOptional = true;
    }

    public void enterPattern(CypherParser.PatternContext ctx) {
        matchClause = ctx.getText();
    }

    public void enterExpression(CypherParser.ExpressionContext ctx) {
        switch (ctx.getParent().getRuleIndex()) {
            case 36:
                skipAmount = Integer.parseInt(ctx.getText());
                break;
            case 37:
                limitAmount = Integer.parseInt(ctx.getText());
                break;
            case 38:
                if (orderClause == null) {
                    orderClause = ctx.getText() + " " + latestOrderDirection;
                } else {
                    orderClause += ", " + ctx.getText();
                }
                break;
            case 39:
                if (whereClause == null) whereClause = ctx.getText();
                break;
        }
    }

    public void enterReturnX(CypherParser.ReturnXContext ctx) {
        //distinct keyword attached or not
        if (ctx.getText().toLowerCase().contains(" distinct "))
            hasDistinct = true;
        // is the return query looking at a count
        if (ctx.getText().toLowerCase().contains("count"))
            hasCount = true;
        // is the return query looking at a collect
        if (ctx.getText().toLowerCase().contains("collect"))
            hasCollect = true;
        // is the return query looking at a case expression
        // note case not part of the current openCypher grammar.
        if (ctx.getText().toLowerCase().contains("case"))
            hasCase = true;
    }

    public void enterReturnItems(CypherParser.ReturnItemsContext ctx) {
        returnClause = ctx.getText();
    }

    public void enterVariable(CypherParser.VariableContext ctx) {
        if (ctx.getParent().getRuleIndex() == 34) {
            String returnC = ctx.getParent().getText();
            String[] varAndAlias = returnC.split(" as ");
            returnAlias.put(varAndAlias[0], ctx.getText().toLowerCase());
        }
    }

    public void enterSortItem(CypherParser.SortItemContext ctx) {
        String orderByString = ctx.getText().toLowerCase();
        if (orderByString.endsWith("descending") || orderByString.endsWith("desc")) {
            latestOrderDirection = "desc";
        } else if (orderByString.endsWith("ascending") || orderByString.endsWith("asc")) {
            latestOrderDirection = "asc";
        } else latestOrderDirection = "";
    }

    public void enterDelete(CypherParser.DeleteContext ctx) {
        hasDelete = true;
    }

    void printInformation() {
        System.out.println("\n--- QUERY INFORMATION ---");
        if (matchClause != null) System.out.println("Match Clause: " + matchClause + " -- OPTIONAL = " + hasOptional);
        if (whereClause != null) System.out.println("Where Clause: " + whereClause);
        if (withClause != null) System.out.println("With Clause: " + withClause);
        if (returnClause != null)
            System.out.println("Return Clause: " + returnClause + " -- DISTINCT = " + hasDistinct);
        if (!returnAlias.isEmpty())
            System.out.println("Return Alias: " + returnAlias);
        if (orderClause != null) System.out.println("Order Clause: " + orderClause);
        if (skipAmount != -1) System.out.println("Skip Amount: " + skipAmount);
        if (limitAmount != -1) System.out.println("Limit Amount: " + limitAmount);
        System.out.println("\n");
    }

    public String getReturnClause() {
        return returnClause;
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
}
