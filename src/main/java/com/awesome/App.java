package com.awesome;

import java.util.List;
import java.util.Arrays;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

public class App {
  public static void main(String[] args) {
    List<Applicant> inputData = Arrays.asList(
      new Applicant(1, "John", "Doe", 10000, 568),
      new Applicant(2, "John", "Greg", 12000, 654),
      new Applicant(3, "Mary", "Sue", 100, 568),
      new Applicant(4, "Greg", "Darcy", 1000000, 788),
      new Applicant(5, "Jane", "Stuart", 10, 788)
    );

    SparkConf conf = new SparkConf().setAppName("Simple Application");
    JavaSparkContext sc = new JavaSparkContext(conf);

    KieBase rules = loadRules();
    Broadcast<KieBase> broadcastRules = sc.broadcast(rules);

    JavaRDD<Applicant> applicants = sc.parallelize(inputData);

    long numApproved = applicants.map( a -> applyRules(broadcastRules.value(), a) )
                                 .filter( a -> a.isApproved() )
                                 .count();

    System.out.println("Number of applicants approved: " + numApproved);
  }

  public static KieBase loadRules() {
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kieContainer = kieServices.getKieClasspathContainer();

    return kieContainer.getKieBase();
  }

  public static Applicant applyRules(KieBase base, Applicant a) {
    StatelessKieSession session = base.newStatelessKieSession();
    session.execute(CommandFactory.newInsert(a));
    return a;
  }
}
