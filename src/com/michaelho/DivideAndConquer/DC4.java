package com.michaelho.DivideAndConquer;

import com.michaelho.DataObjects.Complex;

/**
 * The DC4 class explores a set of divide and conquer algorithms such as polynomials multiplication using
 * naive method, and Fast Fourier Transform Algorithm.
 *
 * @author Michael Ho
 * @since 2015-01-12
 * */
public class DC4 {
    Polynomial poly = new Polynomial();
    FastFourierTransform fft = new FastFourierTransform();

    class Polynomial {

        /**
         * Naive approach to multiply 2 polynomials.
         * Runtime: O(N^2)
         *
         * @param A The first polynomial represented in integer array.
         * @param B The second polynomial represented in integer array.
         *
         * @return the multiplication result as an integer array.
         * */
        int[] multiplication(int[] A, int[] B) {
            int[] output = new int[A.length + B.length - 1];

            for (int i = 0; i < output.length; i++) {
                output[i] = 0;
            }

            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    output[i+j] += A[i]*B[j];
                }
            }

            return output;
        }

        double[] multiplyInDouble(double[] A, double[] B) {
            double[] output = new double[A.length + B.length - 1];

            for (int i = 0; i < output.length; i++) {
                output[i] = 0;
            }

            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    output[i+j] += A[i]*B[j];
                }
            }

            return output;
        }
    }

    class FastFourierTransform {

        /**
         * The function that calculates Fast Fourier Transform.
         *
         * @param a The complex numbers.
         * */
        Complex[] FFT(Complex[] a, Complex[] omega, int length, int pow) {

            if (length == 1)
                return a;

            Complex[] aEven = new Complex[length];
            Complex[] aOdd = new Complex[length];

            // send them into two different array of objects
            for(int k = 0; k < length; k++){
                if(k%2 == 0)
                    aEven[k/2] = a[k];
                else
                    aOdd[k/2] = a[k];
            }

            Complex[] solutionEven = FFT(aEven, omega, length/2, pow*2);
            Complex[] solutionOdd = FFT(aOdd, omega, length/2, pow*2);

            Complex[] polySol = new Complex[length];
            for (int i = 0; i < length; i++)
                polySol[i] = new Complex(0,0);

            // combine the pieces
            for (int i = 0; i < length/2; i++) {
                polySol[i] = solutionEven[i].add(omega[i*pow].multiply(solutionOdd[i]));
                polySol[i+length/2] = solutionEven[i].subtract(omega[i*pow].multiply(solutionOdd[i]));
            }

            return polySol;
        }
    }
}
