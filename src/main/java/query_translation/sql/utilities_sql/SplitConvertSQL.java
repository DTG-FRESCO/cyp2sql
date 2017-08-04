package query_translation.sql.utilities_sql;

public class SplitConvertSQL {
    public static String convert(String original) {
        // need to convert Cypher:   split(n.name[1], "db")[1]
        // into SQL:                 split_part(name[2], 'db', 2)

        return null;
    }
}
