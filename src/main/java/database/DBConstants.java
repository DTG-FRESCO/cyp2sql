package database;

/**
 * Class containing a list of useful queries for the translation to SQL.
 */
class DBConstants {
    static final String QUERY_MAPPING = "CREATE TABLE query_mapping (cypher TEXT, sql TEXT, object BYTEA, " +
            "neoT DOUBLE PRECISION, pgT DOUBLE PRECISION);";

    static final String ADJLIST_FROM = "CREATE MATERIALIZED VIEW adjList_from AS (select idl as LeftNode, " +
            "array_agg(idr ORDER BY idr asc) AS RightNode FROM edges e JOIN nodes n on e.idl = n.id GROUP BY idl);";

    static final String ADJLIST_TO = "CREATE MATERIALIZED VIEW adjList_to AS (select idr as LeftNode, " +
            "array_agg(idl ORDER BY idl asc) AS RightNode FROM edges e JOIN nodes n on e.idr = n.id GROUP BY idr);";

    static final String FOR_EACH_FUNC = "CREATE FUNCTION doForEachFunc(int[], field TEXT, newV TEXT) RETURNS void AS $$ " +
            "DECLARE x int; r record; l text; BEGIN if array_length($1, 1) > 0 THEN FOREACH x SLICE 0 " +
            "IN ARRAY $1 LOOP FOR r IN SELECT label from nodes where id = x LOOP " +
            "EXECUTE 'UPDATE nodes SET ' || field || '=' || quote_literal(newV) || ' WHERE id = ' || x; " +
            "l := replace(r.label, ', ', '_'); " +
            "EXECUTE 'UPDATE ' || l || ' SET ' || field || '=' || quote_literal(newV) || ' WHERE id = ' || x; " +
            "END LOOP; END LOOP; END IF; END; $$ LANGUAGE plpgsql;";

    static final String CYPHER_ITERATE = "CREATE OR REPLACE FUNCTION cypher_iterate(int[]) RETURNS int[] AS $$ \n" +
            "    DECLARE\n" +
            "\t\tr int[];\n" +
            "\t\tt int[];\n" +
            "\t\tz int[];\n" +
            "\t\tlastResults int[];\n" +
            "\t\tcount int;\n" +
            "\tBEGIN\n" +
            "\t\tt := array_unique($1);\n" +
            "\t\tr := $1;\n" +
            "\t\tlastResults := t;\n" +
            "\t\traise notice 'Size of input array: %', array_length(r,1);\n" +
            "\t\traise notice 'Unique elements to iterate with: %', array_length(t,1);\n" +
            "\t\t--raise notice 'All elements are: %', r;\n" +
            "\t\t--raise notice 'Unique elements are: %', t;\n" +
            "\t\tcount := 0;\n" +
            "\t\tloop EXIT WHEN array_length(t,1) is null or lastResults = z;\n" +
            "\t\t\tlastResults := z;\n" +
            "\t\t\tfor z in select loop_work(t) LOOP\n" +
            "\t\t\t\traise notice 'Size of z: %', array_length(z,1);\n" +
            "\t\t\t\tif (z <> lastResults or count = 0) then r := array_cat(r, z); end if;\n" +
            "\t\t\t\traise notice 'Size of r: %', array_length(r,1);\n" +
            "\t\t\t\tt := array_unique(z);\n" +
            "\t\t\t\tcount := count + 1;\n" +
            "\t\t\tEND LOOP;\n" +
            "\t\tend loop;\n" +
            "\t\tRETURN r;\n" +
            "\tEND; \n" +
            "$$ LANGUAGE plpgsql;";

    static final String UNIQUE_ARR_FUNC = "CREATE OR REPLACE FUNCTION public.array_unique(arr anyarray)\n" +
            "returns anyarray as $body$\n" +
            "    select array( select distinct unnest($1) )\n" +
            "$body$ language 'sql';";

    // required to allow the tool to add new nodes to the database without any issue.
    static final String AUTO_SEQ_QUERY = "CREATE SEQUENCE nodes_id_seq;\n" +
            "ALTER TABLE nodes ALTER id SET DEFAULT NEXTVAL('nodes_id_seq');";

}
