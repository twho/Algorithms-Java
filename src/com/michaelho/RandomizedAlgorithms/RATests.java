package com.michaelho.RandomizedAlgorithms;

import com.michaelho.Main;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RATests {
    private RA1 ra1 = new RA1();

    @Test
    public void testModularExp() {
        int x = 7, y = 25, N = 23;
        int expected = 21;
        assertEquals(expected, ra1.ma.modularExp(x, y, N));
    }

    @Test
    public void testMultiplicativeInverses() {
        int x = 3, N = 11;
        int expected = 4;
        assertEquals(expected, ra1.ma.multiplicativeInverse(x, N));
    }

    @Test
    public void testEuclidAlgorithm() {
        int[] x = {69, 35, 31}, y = {23, 10, 2}, expected = {23, 5, 1};
        for (int i = 0; i < x.length; i ++) {
            assertEquals(expected[i], ra1.gcd.euclidAlgorithm(x[i], y[i]));
        }
    }

    @Test
    public void testExtEuclidAlgorithm() {
        int[] x = {30, 35}, y = {20, 15};
        int[] expected1 = {10, 5}, expected2 = {1, 1}, expected3 = {-1, -2};
        for (int i = 0; i < x.length; i ++) {
            int[] result = ra1.gcd.extEuclidAlgorithm(x[i], y[i]);
            assertEquals(expected1[i], result[0]);
            assertEquals(expected2[i], result[1]);
            assertEquals(expected3[i], result[2]);
        }
    }
}