couchbase-java
==============

Example of Java,Spring and Couchbase as the backbone of a REST API.


To run this example, clone the repo, run mvn clean install and then mvn jetty:run to start the server.

One call avaiable only as a demo

```` curl -XPOST -d "source=facebook" http://localhost:8080/users --header "Content-Type:application/json" ````

The couchbase client is wrapped in a connecting class to provide a placeholder for additional logic such as choosing hosts from properties files, failover strategies with XDCR etc, personal preference nothing else! :)

The Couchbase wrapper looks for an enviromnent system url to be set, if not present it defaults to local host.  To add additional environments add them to the couchbase.properties file as a comma seperated array.  Remember that adding a node to a cluster doesn't require a restart in the application as the client sdk topology is automatically updated.
