# Cypher to SQL Translation (Cyp2SQL)

## Quick Usage
To use this tool without going near the code, include the .jar in your classpath and use the tool as indicated by the tutorial below:

```java
import database.postgres.InsertSchemaPostgres;
import intermediate_rep.DecodedQuery;
import production.C2SMain;
import production.C2SProperties;
import schema_conversion.SchemaConvert;

import java.io.IOException;

public class C2STestUsage {
    public static void main(String args[]) {
        // these need to be set before anything can occur.
        String propsFile = "C:/Users/ocraw/IdeaProjects/cyp2sql-next/c2s_props.properties";
        C2SProperties props = new C2SProperties(propsFile);

        // name of the blank database to either:
        //     - convert the schema too
        //     - execute the translated Cypher on
        String dbName = "testb";

        String thingToDo = "translate";

        switch (thingToDo) {
            case "convert":
                // the properties file needs to be edited first with all the correct values.
                // a dump from Neo4j is also needed.
                boolean successConvert = SchemaConvert.translate(props);
                if (successConvert) InsertSchemaPostgres.executeSchemaChange(dbName, props);
                break;
            case "translate":
                // location of the script to allow results from Postgres to be piped back to 
                // this class. View and adapt the scripts if necessary.
                String scriptLoc = "C:/Users/ocraw/IdeaProjects/cyp2sql-next/pgdbPlay.bat";

                // Cypher query to translate and then execute.
                String cypher = "MATCH (a:Meta) RETURN DISTINCT a.type " +
                 "UNION MATCH (a:Process) RETURN DISTINCT a.type;";

                try {
                    // obtain the intermediate representation
                    DecodedQuery dQ = C2SMain.getDQ(cypher, props);
                    System.out.println(dQ.getUnionParts().get(0).getMc());

                    // convert the intermediate representation to SQL
                    String sql = C2SMain.getTranslation(cypher, dQ, props);
                    System.out.println(sql);

                    // execute directly on Postgres if desired (the script will pipe 
                    // the results back into this tool).
                    String postgresOutput = C2SMain.runPostgres(sql, dbName, scriptLoc);
                    System.out.println(postgresOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.err.println("Not a valid option...");
                System.exit(1);
        }
    }
}
```

## Installation

1. Clone the repository. 
2. Create a new folder anywhere in your userspace 
(for example, `./testFolder`).
3. Navigate to this folder, and create a new folder inside for the workspace of the tool (again, it can be
called anything, but something sensible like `./testFolder/workarea` would be good).
4. Create a new file, `./testFolder/lists.txt`, and include in the file any fields of 
the existing Neo4j graph that *may* handle values of type list.
5. Create a new folder for the results of the queries to be stored in, and then within that folder, create two text files - 
`./testFolder/results/neo4j.txt` & `./testFolder/results/postgres.txt`

The properties file, `c2s_props.properties` then needs to be edited with the correct details (view the main documentation for more information).

A new, blank database in Postgres should also be setup. For the results from Postgres to be placed into the console, a password may need to be entered into a local Postgres config file - https://www.postgresql.org/docs/8.3/static/libpq-pgpass.html provides more information. 

### Obtaining a dump from an existing Neo4j graph
The path should point to the folder containing the Neo4j graph. For Windows:

```
java -classpath "C:\Program Files\Neo4j CE 3.0.6\bin\neo4j-desktop-3.2.2.jar" 
    org.neo4j.shell.StartClient 
    -path "C:\Users\user\Documents\Neo4j graphs\Test1KOPUS" 
    -c dump > "C:\Users\user\Documents\Graph Dumps\dumpOPUS1k.txt"
```

For a Unix based OS:

```
neo4j-shell -path ../data/databases/dump_prov1000.graphdb/ -c dump > 2017-08-01-dump.txt
```


## Usage
To convert an existing Neo4j graph to a relational schema:

```
java -jar cyp2sql-next.jar -s c2s_props.properties <nameOfDB>
```

The last command line argument should be set to the name of a blank database created on Postgres. 
If the database already has some content inside it, the tool will throw an error.

To translate queries from Cypher to SQL (make sure there is an instance of the Neo4j database up and running):

```
java -jar cyp2sql-next.jar -t c2s_props.properties <nameOfDB> -r
```

This will convert Cypher to SQL and execute it on the database. For debugging purposes, use a slightly different configuration:

```
java -jar cyp2sql-next.jar -t c2s_props.properties <nameOfDB> -dp|-dn
```

`-dp` will print the results from both Neo4j and Postgres to a local file. `-dn` will not print any results to file.

## Documentation
For any further guidance and instructions, there is both the technical guide and a folder containing
the Javadoc of the source code.

## Examples
Whilst the goal of this project would be to cover most of the syntax available in Cypher,
this is simply not possible yet, although below serves as a rough guide as to what
can be translated. There is more information in the technical manual.

- `MATCH p=shortestPath((f {name:"omega"})-[*1..6]->(t:Meta)) RETURN count(t)`
- `MATCH (a)-[*1..3]->(c:Process) RETURN count(c)`
- `MATCH (a:Local)-[*4..9]->(b) RETURN DISTINCT b.node_id, b.sys_time AS time_alias ORDER BY b.node_id DESC`
- `MATCH (n {name:["/var/db/entropy/saved-entropy.7", "/var/db/entropy/saved-entropy.8"]}) RETURN n.node_id ORDER BY n.node_id ASC`
- `MATCH (a:Global)-[]->(b) RETURN b.node_id AS conn_id`
- `MATCH (a:Global)-[]->(b) RETURN count(b.node_id)`
- `MATCH (a:Global)-->(b:Local)-->(c:Process)<--(d:Local)<--(b) RETURN count(b)`
- `MATCH ()-[r]-() RETURN DISTINCT r.state ORDER BY r.state`
- `MATCH (n:Local)<--(m:Global) RETURN m.node_id AS thing, m.type AS ty ORDER BY m.sys_time LIMIT 3`
- `MATCH (a:Global) RETURN count(a) AS funky`
- `MATCH (a:Meta) WHERE a.sys_time < 0 RETURN count(a)`
- `MATCH (a:Meta) WHERE a.sys_time < 0 OR a.node_id > 845 RETURN count(a)`
- `MATCH (a)-[r:LOC_OBJ]-(b) RETURN b.name, r.state ORDER BY b.node_id ASC LIMIT 15`
- `MATCH ()<-[r:LOC_OBJ {state:12}]-(idA {type:2}) RETURN count(r)`
- `MATCH (n) WHERE id(n) = 345 RETURN n.mono_time, n.sys_time, n.name`
- `MATCH (a)-->(b)-->(c)-->(d) WHERE id(d) < 123 RETURN count(a) AS cool`
- `MATCH (n) WHERE exists(n.value) AND exists(n.timestamp) RETURN count(n)`
- `MATCH (n)--()--()--()--(n) WHERE exists(n.status) RETURN count(n)`
- `MATCH (s)-[e]-(d) WHERE id(s) = 349 AND NOT 'Process' in labels(s) AND NOT 'Global' in labels(d) RETURN d.node_id ORDER BY d.node_id ASC`
- `MATCH (n:Process)<-[e:PROC_OBJ]-(c:Local) WHERE id(n) = 916 AND e.state in [5] RETURN c.name, e.state ORDER BY c.name DESC`
- `MATCH (a)-[e]-(b) WHERE id(a) IN [100, 200, 300, 400] AND id(b) IN [101, 201, 202, 302, 404] RETURN e.state`
- `MATCH (a) WHERE any(name in a.name WHERE name = 'uid') RETURN count(a)`
- `MATCH (a) WHERE any(lab in labels(a) WHERE lab IN ['Global', 'Meta']) RETURN count(a)`
- `MATCH (n) WHERE 'Process' in labels(n) WITH n MATCH (m) WHERE m.status = n.status RETURN count(n)`
- `MATCH (n) WHERE 'Local' in labels(n) AND NOT exists(n.pid) WITH n MATCH (m:Global)-[r]->(n) WHERE id(m) > 900 RETURN n.node_id, r.state`
- `MATCH (n:Global)-->(m:Local) WHERE n.node_id < m.node_id RETURN count(m)`
- `MATCH (n:Meta)<--(m:Process)-->(p) WHERE n.node_id > m.node_id AND p.node_id <= m.node_id RETURN count(m)`
- `MATCH (n) WHERE id(n) < 3 WITH n MATCH (m) WHERE id(m) < id(n) WITH m MATCH (p) WHERE p.node_id < m.node_id RETURN count(p)`
- `MATCH (n) WHERE 'Meta' in labels(n) OR any(name in n.name WHERE name = 'postgres') WITH n MATCH (m:Process) WHERE id(m) > id(n) WITH m MATCH (p)-->(m) WITH p MATCH (j)<-[:PROC_OBJ_PREV]-(p) WHERE p.sys_time = j.sys_time RETURN count(j)`
- `MATCH (a:Global {name:'postgres'})-->(b:Global) WITH b MATCH (c) WHERE c.sys_time = b.sys_time WITH c MATCH (c)<--(d) RETURN DISTINCT d.node_id ORDER BY d.node_id LIMIT 5`
- `MATCH (a:Meta) RETURN count(distinct a.name)`
- `MATCH (a:Local)-->(b)<--(c:Process)<--(d) RETURN min(d.node_id)`
- `MATCH (n) WHERE 'Global' in labels(n) AND any(name in n.name WHERE name = 'master') OR (exists(n.pid) AND n.status = 2) WITH n MATCH (m:Meta) WHERE m.node_id > n.node_id RETURN DISTINCT n LIMIT 10`
- `MATCH (a) WHERE a.node_id < 345 OR ((a.node_id > 800 AND 'Process' in labels(a)) OR a.node_id = 983) RETURN count(a)`
- `MATCH (a) WHERE (any(x in a.name where x = 'master') OR any(y in a.value where y in ['postgres', 'nginx'])) AND ('Global' in labels(a) OR 'Meta' in labels(a)) RETURN count(a)`
- `MATCH (a)-[e]->(b)-[f]->(c) WHERE a.type = b.type AND c.pid < b.pid RETURN count(f)`
- `MATCH (a)-[z]->(b)-[w]->(c) WHERE a.node_id < b.node_id RETURN w.state, c.type ORDER BY c.node_id ASC`
- `MATCH (a)-[e]->(b:Process) WHERE e.state > 5 WITH b MATCH (c) WHERE (exists(c.pid) AND c.pid < b.pid) WITH c MATCH (c)<--(d:Local) WHERE any(n in d.name WHERE n = '4') RETURN count(d) AS cool_thing`
- `MATCH (a)-->(b)-->(c) WHERE c.node_id < b.node_id WITH c MATCH (d)--(c) WHERE exists(d.ref_count) WITH d MATCH (e)-->(d)<--(f) WHERE f.node_id > e.node_id WITH f MATCH (g)<-[ww]-(f) WHERE ww.state = 5 WITH g MATCH (g)-->(ii)-->(i) RETURN DISTINCT i.node_id ORDER BY i.node_id ASC`
- `MATCH (a)-->(b)-->(c:Process) WHERE a.node_id < b.node_id RETURN a.node_id, b.node_id, case c.type when 3 then 'boo' else 'hiss' end`
- `MATCH (a:Global)-[:LOC_OBJ]->(b) WITH a, count(b) AS num_things WHERE num_things > 2 RETURN a.name ORDER BY a.sys_time DESC`