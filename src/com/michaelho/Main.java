package com.michaelho;

import com.michaelho.DynamicProgramming.DPTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DPTests.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("DPTests successful: " + result.wasSuccessful());
    }

    public static void print(String s) {
        System.out.println(s);
    }
}