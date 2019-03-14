package com.michaelho.DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DPTests {

    private DP1 dp1 = new DP1();

    @Test
    public void testFibonacci() {
        int input = 5;
        int expected = 5;
        fibTest(input, expected);
        fibDpTest(input, expected);
    }

    private void fibTest(int input, int expected) {
        int output = dp1.fib.count(input);
        assertEquals(expected, output);
    }

    private void fibDpTest(int input, int expected) {
        int output = dp1.fib.dpCount(input);
        assertEquals(expected, output);
    }

    @Test
    public void testLIS() {
        int[] input = {5, 7, 4, -3, 9, 1, 10, 4, 5, 8, 9, 3};
        int expected = 6;
        runLIS(input, expected);
    }

    private void runLIS(int[] input, int expected) {
        int output = dp1.lis.dpFindLongestLength(input);
        assertEquals(expected, output);
    }

    @Test
    public void testLCS() {
        String input1 = "BCDBCDA";
        String input2 = "ABECBA";
        int expected = 4;
        runLCS(input1, input2, expected);
    }

    private void runLCS(String input1, String input2, int expected) {
        int output = dp1.lcs.lengthOfLCS(input1, input2);
        assertEquals(expected, output);
    }

    @Test
    public void testLUS1() {
        String[] input = {"aba", "cdc", "eae"};
        int expected = 3;
        runLUS(input, expected);
    }

    @Test
    public void testLUS2() {
        String[] input = {"aabbcc", "aabbcc","cb","abc"};
        int expected = 2;
        runLUS(input, expected);
    }

    private void runLUS(String[] strs, int expected) {
        int output = dp1.lus.findLUSlength(strs);
        assertEquals(expected, output);
    }
}

