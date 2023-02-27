# NoSQL 10

## Cassandra

To start Cassandra in your docker use the following command while the docker deamon is running.

``
docker run --name cassandra-10 -p 127.0.0.1:9042:9042 -p 127.0.0.1:9160:9160 -d cassandra
``

Adding Cassandra to your Databases in IntelliJ:
* Select Datasource and click Apache-Cassandra
* Change the Driver to 1.4 if necessary
* Rename the Database to cassandra-10 instead of @localhost
* Test Connection and Apply

