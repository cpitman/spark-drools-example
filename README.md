Spark Drools Example
====================

(Work In Progress)

This project shows a simple example of how to integrate drools into an 
Apache Spark job. The steps are pretty simple:

1. Load a knowledge base using any sources supported by drools
2. Broadcast the knowledge base to all workers
3. Use the broadcasted rules within the process

Broadcasting the rules ensures that they are only loaded and compiled once, sent to workers in their compiled form, and then reused throughout the job.


Setup
-----

The included Vagrantfile(www.vagrantup.com) will spin up a VM with 
spark and a Java 1.8 build environment. Once ssh'ed into vagrant you
can run this example by doing the following:

    cd /vagrant
    mvn package
    /opt/spark-1.4.1-bin-hadoop2.6/bin/spark-submit --class "com.awesome.App" --master local[4] target/SparkDroolsExample-1.0-SNAPSHOT.jar
