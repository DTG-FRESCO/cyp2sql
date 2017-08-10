package query_translation.sql.utilities_sql;

import intermediate_rep.DecodedQuery;

/**
 * For classes that take a DecodedQuery object and convert to SQL, they should extend this class. Included
 * in this class are some helper field, including an alphabet char array and number char array, and
 * the translate method definition.
 */
abstract class AbstractTranslation {
    static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] extendID = "123456789".toCharArray();
    static boolean useOptimalTable = false;

    public abstract StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery);
}
