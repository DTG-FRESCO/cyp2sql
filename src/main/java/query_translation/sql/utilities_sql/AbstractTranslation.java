package query_translation.sql.utilities_sql;

import intermediate_rep.DecodedQuery;

public abstract class AbstractTranslation {
    static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] extendID = "123456789".toCharArray();
    static boolean useOptimalTable = false;

    public abstract StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery);
}
