# CLV REST API

REST API for CLV Connected Vehicle Pilot.  This REST API returns gps data stored in Apache Accumulo in a JSON structure.

### Installation

Prereqs: Docker, Maven, JDK

Add your connection info to "config.properties" in the resources folder, example:
user:Accumulo_User_Name

pass:Accumulo_Password

table:Accumulo_Table

instance:Accumulo_Instance_Name

zooServers:Zookeeper_Host

auths:Accumulo_Authorizations

```sh
$ cd clvapi
$ docker build -t clvapi .
$ docker run -d -p 4567:4567 clvapi
```

Sample Query: curl -XGET localhost:4567/vehicleData

License
----

Apache

