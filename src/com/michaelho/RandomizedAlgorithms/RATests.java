package com.michaelho.RandomizedAlgorithms;

import com.michaelho.Main;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RATests {
    private RA1 ra1 = new RA1();
    private RA2 ra2 = new RA2();

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

    @Test
    public void testFermatTheorem() {
        int x = 3, N = 11;
        int expected = 4;
        assertEquals(expected, ra2.ft.calculateModInverse(x, N));
    }

    @Test
    public void testEulerTheorem() {
        int[] numbers = {3, 5, 7, 15};
        int[] expected = {2, 4, 6, 8};
        for (int i = 0; i < numbers.length; i++) {
            assertEquals(expected[i], ra2.et.phi(numbers[i]));
        }
    }

    @Test
    public void testRSA1() {
        int p = 3, q = 7;
        int[] publicKeyPair = ra2.rsa.getPublicKeyPair(p, q);
        double privateKey = ra2.rsa.getPrivateKey(p, q, publicKeyPair[1]);

        double msg = 19.0;
        double result = ra2.rsa.encrypt(msg, publicKeyPair);
        assertNotEquals(msg, result);
        assertEquals(msg, ra2.rsa.decrypt(result, p*q, privateKey), msg*0.05);
    }

    @Test
    public void testRSA2() {
        int p = 5, q = 11;
        int[] publicKeyPair = ra2.rsa.getPublicKeyPair(p, q);
        double privateKey = ra2.rsa.getPrivateKey(p, q, publicKeyPair[1]);

        double msg = 32.0;
        double result = ra2.rsa.encrypt(msg, publicKeyPair);
        assertNotEquals(msg, result);
        assertEquals(msg, ra2.rsa.decrypt(result, p*q, privateKey), msg*0.05);
    }

    @Test
    public void testPrimality() {
        int[] input = {3, 5, 12, 13, 16, 17};
        boolean[] expected = {true, true, false, true, false, true};
        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], ra2.pt.isPrime(input[i]));
        }
    }

    @Test
    public void testPrimalityFermat() {
        int k = 5;
        int[] input = {3, 5, 12, 13, 16, 17};
        boolean[] expected = {true, true, false, true, false, true};
        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], ra2.pt.isPrimeUsingFermat(input[i], k));
        }
    }
}