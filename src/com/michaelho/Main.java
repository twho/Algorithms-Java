package com.michaelho;

import com.michaelho.DivideAndConquer.DC1;
import com.michaelho.DivideAndConquer.DCTests;
import com.michaelho.DynamicProgramming.DPTests;
import com.michaelho.GraphAlgorithms.GRTests;
import com.michaelho.RandomizedAlgorithms.RATests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {

    public static void main(String[] args) {
        runTest(DPTests.class);
        runTest(RATests.class);
        runTest(DCTests.class);
        runTest(GRTests.class);
    }

    public static void print(String s) {
        System.out.println(s);
    }

    private static void runTest(Class toTest) {
        int count = toTest.getName().split("\\.").length;
        String className = toTest.getName().split("\\.")[count-1];

        Result result = JUnitCore.runClasses(toTest);
        print(className + " runs " + result.getRunCount() + " tests.");
        for (Failure failure : result.getFailures()) {
            print(className + " exception: " + failure.getException());
            print(className + " failure: " + failure);
        }

        print(className + " isSuccessful: " + result.wasSuccessful());
    }
}