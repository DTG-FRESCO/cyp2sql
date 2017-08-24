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
 * Class for mapping the different types of count() that are possible to return. They are represented internally
 * as integers in Java. Tbe convert() method can be used to get the String interpretation of the integer.
 */
public class CypCount {
    // no count is present in the return item stored in the CypReturn object.
    public static int COUNT_FALSE = 0;

    // the count function is present in the return item stored in the CypReturn object.
    public static int COUNT_TRUE = 1;

    // the count function is present, and the field being returned must be distinct.
    public static int COUNT_DISTINCT = 2;

    /**
     * Returns a String representation of the type of count that is present. If 0 is passed to this function,
     * then false will be returned. 1 maps to the meaning count(...). 2 maps to the meaning count(distinct ...)
     *
     * @param countType The integer stored in the CypReturn object (view CypherTranslator for more information).
     * @return String meaning of the integer input (usually from the CypReturn getCount() method.)
     */
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
