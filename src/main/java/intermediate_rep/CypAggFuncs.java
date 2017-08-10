package intermediate_rep;

public class CypAggFuncs {
    public static int AGG_NONE = 0;
    public static int AGG_COLLECT = 1;
    public static int AGG_SUM = 2;
    public static int AGG_AVG = 3;
    public static int AGG_MIN = 4;
    public static int AGG_MAX = 5;

    public static String convert(int countType) {
        switch (countType) {
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

    public static String sqlEquiv(int countType) {
        switch (countType) {
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
