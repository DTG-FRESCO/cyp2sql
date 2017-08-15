package intermediate_rep;

/**
 * Class for mapping the different types of aggregate functions that are possible to return.
 */
public class CypAggFuncs {
    // no aggregate function present in the return item.
    public static int AGG_NONE = 0;
    // collect() function used.
    public static int AGG_COLLECT = 1;
    // sum() function used.
    public static int AGG_SUM = 2;
    // avg() function used.
    public static int AGG_AVG = 3;
    // min() function used.
    public static int AGG_MIN = 4;
    // max() function used.
    public static int AGG_MAX = 5;

    /**
     * Returns a String representation of the type of aggregate function that is present.
     *
     * @param aggFuncType The integer stored in the CypReturn object (view CypherTranslator for more information).
     * @return String meaning of the integer input (usually from the CypReturn getAggFunc() method.)
     */
    public static String convert(int aggFuncType) {
        switch (aggFuncType) {
            case 1:
                return "collect";
            case 2:
                return "sum";
            case 3:
                return "avg";
            case 4:
                return "min";
            case 5:
                return "max";
            default:
                return "false";
        }
    }

    /**
     * Returns the SQL equivalent syntax for the aggregate function being used. Mostly this is a straightforward
     * of the same keyword (i.e. sum() in Cypher unsurprisingly maps to sum() in SQL). collect() in Cypher however
     * becomes array_agg() in Postgres at least.
     *
     * @param aggFuncType The integer stored in the CypReturn object (view CypherTranslator for more information).
     * @return SQL syntax for the aggregate function represented by the integer argument.
     */
    public static String sqlEquiv(int aggFuncType) {
        switch (aggFuncType) {
            case 1:
                return "array_agg(";
            case 2:
                return "sum(";
            case 3:
                return "avg(";
            case 4:
                return "min(";
            case 5:
                return "max(";
            default:
                return "";
        }
    }
}
