# Cypher to SQL Translation (Cyp2SQL) version 1.2

## Quick Usage
To use this tool without going near the code, include the .jar in your classpath and use the tool as indicated by the tutorial below:

```java
import database.postgres.InsertSchemaPostgres;
import database.postgres.PostgresDriver;
import intermediate_rep.DecodedQuery;
import production.C2SMain;
import production.C2SProperties;
import schema_conversion.SchemaConvert;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class C2STestUsage {
    public static void main(String args[]) {
        // these need to be set before anything can occur.
        String propsFile = "C:/Users/ocraw/IdeaProjects/cyp2sql-next/c2s_props.properties";
        C2SProperties props = new C2SProperties(propsFile);

        // name of the blank database to either:
        //     - convert the schema too
        //     - execute the translated Cypher on
        String dbName = "testa";

        String thingToDo = "translate";     // alternative is "convert"
        String executeOn = "toFile";        // alternative is "postgres"

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
                String cypher = "MATCH (glo:Global) WHERE id(glo) < 2000 RETURN glo " +
                        "UNION MATCH (glo:Global) WHERE id(glo) > 395000 RETURN glo;";

                try {
                    // obtain the intermediate representation
                    DecodedQuery dQ = C2SMain.getDQ(cypher, props);

                    // convert the intermediate representation to SQL
                    String sql = C2SMain.getTranslation(cypher, dQ, props);
                    System.out.println(sql);

                    switch (executeOn) {
                        case "postgres":
                            // execute directly on Postgres if desired (the script will pipe
                            // the results back into this tool).
                            String postgresOutput = C2SMain.runPostgres(sql, dbName, scriptLoc);
                            System.out.println(postgresOutput);
                            break;
                        case "toFile":
                            // alternatively, print the results to a local file.
                            File f = new File(props.getSqlRes());
                            int numRecords = PostgresDriver.select(sql, dbName, f, true, props);
                            System.out.println(numRecords);
                            break;
                    }
                } catch (IOException | SQLException e) {
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

The library can be built with Maven to ensure all of the dependencies are present:

```bash
$ git clone git@gitlab.dtg.cl.cam.ac.uk:fresco-projects/cyp2sql-next.git
$ cd cyp2sql-next
$ mvn install
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
6. The properties file, `c2s_props.properties` then needs to be edited with the correct details (the main technical manual contains more information on this if necessary).

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

- `MATCH (a:Meta) RETURN count(distinct a.name)`
- `MATCH (a:Meta) WHERE a.sys_time < 0 OR a.node_id > 845 RETURN count(a)`
- `MATCH (n) WHERE id(n) = 345 RETURN n.mono_time, n.sys_time, n.name`
- `MATCH (n {name:["/var/db/entropy/saved-entropy.7", "/var/db/entropy/saved-entropy.8"]}) RETURN n.node_id ORDER BY n.node_id ASC`
- `MATCH (n) WHERE exists(n.value) AND exists(n.timestamp) RETURN count(n)`
- `MATCH (a:Global)-[]->(b) RETURN b.node_id AS conn_id`
- `MATCH (a)-[r:LOC_OBJ]-(b) RETURN b.name, r.state ORDER BY b.node_id ASC LIMIT 15`
- `MATCH ()<-[r:LOC_OBJ {state:12}]-(idA {type:2}) RETURN count(r)`
- `MATCH (n:Local)<--(m:Global) RETURN m.node_id AS thing, m.type AS ty ORDER BY m.sys_time LIMIT 3`
- `MATCH (a)-->(b)-->(c)-->(d) WHERE id(d) < 123 RETURN count(a) AS cool`
- `MATCH (a:Global)-->(b:Local)-->(c:Process)<--(d:Local)<--(b) RETURN count(b)`
- `MATCH ()-[r]-() RETURN DISTINCT r.state ORDER BY r.state`
- `MATCH (n)--()--()--()--(n) WHERE exists(n.status) RETURN count(n)`
- `MATCH (s)-[e]-(d) WHERE id(s) = 349 AND NOT 'Process' in labels(s) AND NOT 'Global' in labels(d) RETURN d.node_id ORDER BY d.node_id ASC`
- `MATCH (n:Process)<-[e:PROC_OBJ]-(c:Local) WHERE id(n) = 916 AND e.state in [5] RETURN c.name, e.state ORDER BY c.name DESC`
- `MATCH (a)-[e]-(b) WHERE id(a) IN [100, 200, 300, 400] AND id(b) IN [101, 201, 202, 302, 404] RETURN e.state`
- `MATCH (a)-[*1..3]->(c:Process) RETURN count(c)`
- `MATCH (a:Local)-[*4..9]->(b) RETURN DISTINCT b.node_id, b.sys_time AS time_alias ORDER BY b.node_id DESC`
- `MATCH p=shortestPath((f {name:"omega"})-[*1..6]->(t:Meta)) RETURN count(t)`
- `MATCH (a) WHERE any(name in a.name WHERE name = 'uid') RETURN count(a)`
- `MATCH (a) WHERE any(lab in labels(a) WHERE lab IN ['Global', 'Meta']) RETURN count(a)`
- `MATCH (n) WHERE 'Process' in labels(n) WITH n MATCH (m) WHERE m.status = n.status RETURN count(n)`
- `MATCH (n) WHERE 'Local' in labels(n) AND NOT exists(n.pid) WITH n MATCH (m:Global)-[r]->(n) WHERE id(m) > 900 RETURN n.node_id, r.state`
- `MATCH (n:Global)-->(m:Local) WHERE n.node_id < m.node_id RETURN count(m)`
- `MATCH (n:Meta)<--(m:Process)-->(p) WHERE n.node_id > m.node_id AND p.node_id <= m.node_id RETURN count(m)`
- `MATCH (n) WHERE id(n) < 3 WITH n MATCH (m) WHERE id(m) < id(n) WITH m MATCH (p) WHERE p.node_id < m.node_id RETURN count(p)`
- `MATCH (n) WHERE 'Meta' in labels(n) OR any(name in n.name WHERE name = 'postgres') WITH n MATCH (m:Process) WHERE id(m) > id(n) WITH m MATCH (p)-->(m) WITH p MATCH (j)<-[:PROC_OBJ_PREV]-(p) WHERE p.sys_time = j.sys_time RETURN count(j)`
- `MATCH (a:Global {name:'postgres'})-->(b:Global) WITH b MATCH (c) WHERE c.sys_time = b.sys_time WITH c MATCH (c)<--(d) RETURN DISTINCT d.node_id ORDER BY d.node_id LIMIT 5`
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
- `MATCH (a)-->(b) WITH b MATCH (c)<--(b) WHERE id(c) < 643 WITH c MATCH ()-[r]-(c) RETURN count(r)`
- `MATCH (n) WHERE id(n) IN [10, 110, 317] AND exists(n.pid) RETURN n.status, n.pid`
- `MATCH (proc:Process)<-[po:PROC_OBJ]-(loc:Local)<-[lo:LOC_OBJ]-(gl:Global)-->(:Local)-->(proc2:Process) WHERE id(proc) IN [137, 149, 162, 278] RETURN DISTINCT proc2.pid ORDER BY proc2.pid ASC`

## Performance Results
The tool has been modelled around a graph database I had access to from the University of Cambridge. 
I have also tested the tool against this galaxies dataset: https://dl.dropboxusercontent.com/u/14493611/the_universe_is_a_graph.html 
(many thanks to the original author of this post).

The OPUS dataset from the University of Cambridge contains 400k nodes and 812k relationships. The galaxies dataset contains around 20k nodes and 584k relationships.

| Query                                                                                                                                                                                                   | Neo4j OPUS      | Postgres OPUS |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|---------------|
| MATCH ()<-[r:LOC_OBJ {state:12}]-(idA {type:2}) RETURN count(r);                                                                                                                                        | ≈ 605ms.        | ≈ 150ms.      |
| MATCH (a) WHERE any(lab in labels(a) WHERE lab IN ['Global', 'Meta']) RETURN count(a);                                                                                                                  | ≈ 835ms.        | ≈ 80ms.       |
| MATCH (a:Global)-->(b:Global) WHERE any(n in a.name WHERE n = 'postgres') WITH b MATCH (c) WHERE c.sys_time = b.sys_time WITH c MATCH (c)<--(d) RETURN DISTINCT d.node_id ORDER BY d.node_id LIMIT 5;   | ≈ 1720ms.       | ≈ 1615ms.     |
| MATCH (m) WHERE exists(m.ref_count) RETURN id(m), m.name;                                                                                                                                               | ≈ 935ms .       | ≈ 160ms.      |

| Query                                                                                                                                                  | Neo4j Galaxies    | Postgres Galaxies |
|--------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|-------------------|
| MATCH (a)-[e]->(b)-[f]->(c) WHERE a.vll_gwc = b.vll_gwc AND id(b) < 1000 AND id(c) > 20000 RETURN count(f);                                            | ≈ 4600ms.         | ≈ 285ms.          |
| MATCH (a)-->(b) WHERE a.vll_gwc = '3' OR a.vll_gsc = '5' AND a.nn_gsc = '6' RETURN DISTINCT id(a), id(b);                                              | ≈ 780ms.          | ≈ 515ms.          |
| MATCH (a:Galaxy)-->(b:Galaxy) WHERE id(b) > 15000 WITH b MATCH (c) WHERE c.vll_gsc = '4' WITH c MATCH (d)<--(c) WHERE d.nn_gwc = '3' RETURN count(d);  | ≈ 1.16 * 10^7 ms. | ≈ 8.83 * 10^5 ms. |

I have, with some bias of course, shown queries that perform better when translated and executed on Postgres. 
There are many queries where Neo4j massively outperforms the relational representation. 
It is very dependent on the graph schema, the graph size, how specific the query is (in terms of indexing performance), and the type of query itself.

## Tested on
I have tested the tool on some GraphGists (https://neo4j.com/graphgists/) - in each case the database is successfully converted over, although no guarantee can be made that all queries can be translated correctly.

* Formula 1 2013 Season
    * Source: https://neo4j.com/graphgist/formula-1-2013-season
    * Example queries that can be translated:
        * `MATCH (driver:Driver)-[f:FINISHED]->(circuit:GrandPrix) RETURN driver.name AS fullname, SUM(f.points) AS total_points ORDER BY total_points DESC;`
* Flight Analyzer
    * Source: https://neo4j.com/graphgist/flight-analyzer
    * Example queries that can be translated:
        * `MATCH (a:Airport {name:'SEA'})-[:ORIGIN]-(f1:Flight)-[d:DESTINATION]-(a2:Airport)-[:ORIGIN]-(f2:Flight)-[:DESTINATION]-(a3:Airport {name:'SFO'}) RETURN f1.date, f1.airline, a2.name, f2.date, f2.airline, a3.name;`
        * `MATCH (a)<-[:DESTINATION]-(f:Flight)-[:ASSIGN]-(t:Ticket) WITH a, avg(t.price) AS aver ORDER BY aver DESC RETURN a.name;`
        * `MATCH (a:Airport)<-[r:DESTINATION]-(f:Flight) WITH a, count(r) AS num_flights WHERE num_flights > 3 RETURN a.name, num_flights ORDER BY num_flights DESC;`    
* Six Nations Championship
    * Source: https://neo4j.com/graphgist/england-6-nations-2016-squad
* Game of Thrones
    * Source: https://neo4j.com/graphgist/game-of-thrones
    * Example queries  that can be translated (note: use of the label 'group' not possible in current version of tool)
        * `MATCH (p:Person)-[r]-(h:House) WHERE h.name = "Lannister" RETURN COUNT(r) AS lannisters;`
        * `MATCH (p:Person {alive: "f"}) RETURN p;`
* Fitness and Nutritional Information
    * Source: https://neo4j.com/graphgist/fitness-and-nutritional-recommendations
* Belgian Beers
    * Source: https://neo4j.com/graphgist/scraping-wikipedia-and-loading-into-a-graphgist