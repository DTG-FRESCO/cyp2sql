# Cypher to SQL Transaltion (Cyp2SQL)

## Installation

1. Clone the repository. 
2. Create a new folder anywhere in your userspace 
(for example, `./testFolder`).
3. Navigate to this folder, and create a new folder inside for the workarea of the tool (again, it can be
called anything, but something sensible like `./testFolder/workarea` would be good).
4. Create a new file, `./testFolder/lists.txt`, and include in the file any fields of 
the existing Neo4j graph that *may* handle values of type list.
5. Create a new folder for the results of the queries to be stored in, and then within that folder, create two text files - 
`./testFolder/results/neo4j.txt` & `./testFolder/results/postgres.txt`

The properties file, `c2s_props.properties` then needs to be edited with the correct details (view the main documentation for more information).

A new, blank database in Postgres should also be setup.

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
- `MATCH (a) WHERE any(name in a.name WHERE name IN ['uid']) RETURN count(a)`
- `MATCH (a) WHERE any(lab in labels(a) WHERE lab IN ['Global', 'Meta']) RETURN count(a)`
- `MATCH (n) WHERE 'Process' in labels(n) WITH n MATCH (m) WHERE m.status = n.status RETURN count(n)`
- `MATCH (n) WHERE 'Local' in labels(n) AND NOT exists(n.pid) WITH n MATCH (m:Global)-->(n) WHERE id(m) > 900 RETURN n.node_id`
- `MATCH (n) WHERE 'Local' in labels(n) AND NOT exists(n.pid) WITH n MATCH (m:Global)-[r]->(n) WHERE id(m) > 900 RETURN n.node_id, r.state`
- `MATCH (n:Global)-->(m:Local) WHERE n.node_id < m.node_id RETURN count(m)`
- `MATCH (n:Meta)<--(m:Process)-->(p) WHERE n.node_id > m.node_id AND p.node_id <= m.node_id RETURN count(m)`
- `MATCH (n) WHERE id(n) < 3 WITH n MATCH (m) WHERE id(m) < id(n) WITH m MATCH (p) WHERE p.node_id < m.node_id RETURN count(p)`
- `MATCH (n) WHERE 'Meta' in labels(n) OR any(name in n.name WHERE name IN ['postgres', 'cron', 'dhclient']) WITH n MATCH (m:Process) WHERE id(m) > id(n) WITH m MATCH (p)-->(m) WITH p MATCH (j)<-[:PROC_OBJ_PREV]-(p) WHERE p.sys_time = j.sys_time RETURN count(j)`