package com.michaelho.DivideAndConquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class DCTests {
    DC1 dc1 = new DC1();
    DC2 dc2 = new DC2();
    DC3 dc3 = new DC3();
    DC4 dc4 = new DC4();
    DC5 dc5 = new DC5();

    @Test
    public void testMultiplication1() {
        int input1 = 123;
        int input2 = 456;
        int expected = 56088;
        assertEquals(expected, dc1.calculateByNaiveApproach(input1, input2));
        assertEquals(expected, dc1.calculateByFastApproach(input1, input2));
    }

    @Test
    public void testMultiplication2() {
        int input1 = 712;
        int input2 = 45;
        int expected = 32040;
        assertEquals(expected, dc1.calculateByNaiveApproach(input1, input2));
        assertEquals(expected, dc1.calculateByFastApproach(input1, input2));
    }

    @Test
    public void testKthSmallest() {
        int[] arr = new int[] {7, 10, 4, 5, 6, 4, 21, 17};
        int k = 5;
        int expected = 7;
        assertEquals(expected, dc2.kthSmallest(arr, 0, arr.length-1, k));
    }

    @Test
    public void testKthLargest() {
        int[] arr = new int[] {7, 10, 4, 5, 6, 3, 21, 17};
        int k = 3;
        int expected = 10;
        assertEquals(expected, dc2.kthLargest(arr, 0, arr.length-1, k));
    }

    @Test
    public void testQuickSort() {
        int[] arr = new int[] {18, 7, 10, 4, 5, 6, 3, 21, 17};
        dc2.quickSort(arr, 0, arr.length-1);
        int[] expected = new int[] {3, 4, 5, 6, 7, 10, 17, 18, 21};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSumGS() {
        float a = (float) 1;
        float r = (float) 0.5;
        int n = 10;
        float expected = (float) 1.99805;
        assertEquals(expected, dc3.sumOfGS(a, r, n), 0.01*expected);
        assertEquals(expected, dc3.sumOfGSByFormula(a, r, n), 0.01*expected);
    }

    @Test
    public void testNaivePolynomialMultiplication() {
        int[] A = {5, 0, 10, 6};
        int[] B = {1, 2, 4};
        int[] expected = new int[] { 5, 10, 30, 26, 52, 24 };
        assertArrayEquals(expected, dc4.poly.multiplication(A, B));
    }

    @Test
    public void testFFTPolynomialMultiplication1() {
        // Currently it is only good for 2^n data points
        double[] a1= new double[] {5, 0, 10, 6};
        double[] b1 = new double[] {1, 2, 4, 5};
        int n1 = a1.length*2;

        double[] result1 = dc5.multiply(a1, b1, n1, dc5.getOmega(n1, false), dc5.getOmega(n1, true));
        assertArrayEquals(dc4.poly.multiplyInDouble(a1, b1), result1, 0.01);
    }

    @Test
    public void testFFTPolynomialMultiplication2() {
        double[] a2 = new double[] {5, 0, 10, 6, 4, 5, 5, 0};
        double[] b2 = new double[] {1, 2, 4, 5, 5, 0, 10, 6};
        int n2 = a2.length*2;

        double[] result2 = dc5.multiply(a2, b2, n2, dc5.getOmega(n2, false), dc5.getOmega(n2, true));
        assertArrayEquals(dc4.poly.multiplyInDouble(a2, b2), result2, 0.01);
    }
}
