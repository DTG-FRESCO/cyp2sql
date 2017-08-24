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

    public static int convert(String s) {
        switch (s) {
            case "collect":
                return 1;
            case "sum":
                return 2;
            case "avg":
                return 3;
            case "min":
                return 4;
            case "max":
                return 5;
            default:
                return -1;
        }
    }
}
