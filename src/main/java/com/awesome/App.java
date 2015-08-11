package com.awesome;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

public class App {
  public static void main(String[] args) {
    String logFile = "/opt/spark-1.4.1-bin-hadoop2.6/README.md"; // Should be some file on your system
    SparkConf conf = new SparkConf().setAppName("Simple Application");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> logData = sc.textFile(logFile).cache();

    long numAs = logData.filter( (String s) -> s.contains("a") ).count();

    long numBs = logData.filter( (String s) -> s.contains("b") ).count();

    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
  }
}
