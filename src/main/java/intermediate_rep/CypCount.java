package intermediate_rep;

/**
 * Class for mapping the different types of count() that are possible to return to integers in Java.
 */
public class CypCount {
    // no count is present in the return item stored in the CypReturn object.
    public static int COUNT_FALSE = 0;

    // the count function is present in the return item stored in the CypReturn object.
    public static int COUNT_TRUE = 1;

    // the count function is present, and the field being returned must be distinct.
    public static int COUNT_DISTINCT = 2;

    public static String convert(int countType) {
        switch (countType) {
            case 0:
                return "false";
            case 1:
                return "true";
            case 2:
                return "distinct";
            default:
                return "error?";
        }
    }
}
