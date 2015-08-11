Spark Drools Example
====================

(Work In Progress)

This project shows a simple example of how to integrate drools into an 
Apache Spark job.


Setup
-----

The included Vagrantfile(www.vagrantup.com) will spin up a VM with 
spark and a Java 1.8 build environment. Once ssh'ed into vagrant you
can run this example by doing the following:

    cd /vagrant
    mvn package
    /opt/spark-1.4.1-bin-hadoop2.6/bin/spark-submit --class "com.awesome.App" --master local[4] target/SparkDroolsExample-1.0-SNAPSHOT.jar
