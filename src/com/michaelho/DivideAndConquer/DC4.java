package com.michaelho.DivideAndConquer;

import com.michaelho.DataObjects.Complex;

/**
 * The DC4 class explores a set of divide and conquer algorithms such as polynomials multiplication using
 * naive method and Fast Fourier Transform.
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

        double[] multiply(double[] p, double[] q, int n, Complex[] omega, Complex[] omegaInv) {
            // Generate complex objects with 2 times the size
            Complex[] pInteger = new Complex[n];
            Complex[] qInteger = new Complex[n];

            // Copy the coefficents into the real part of complex number and pad zeros to remaining terms.
            for (int i = 0; i < n/2; i++) {
                pInteger[i] = new Complex(p[i], 0);
                qInteger[i] = new Complex(q[i], 0);
            }

            for (int i = n/2; i < n; i++) {
                pInteger[i] = new Complex(0, 0);
                qInteger[i] = new Complex(0, 0);
            }

            int pow = 1;

            // Apply the FFT to the two factors
            Complex[] solp = fastFourierTransform(pInteger, omega, n, pow);
            Complex[] solq = fastFourierTransform(qInteger, omega, n, pow);

            // Multiply the results pointwise recursive
            Complex[] finalSol = new Complex[n];
            for (int i = 0; i < n; i++)
                finalSol[i] = solp[i].multiply(solq[i]);

            // Apply the FFT to the pointwise product
            Complex[] poly = fastFourierTransform(finalSol, omegaInv, n, pow);

            double[] result = new double[n-1];
            for (int i=0; i < n-1; i++)
                result[i] = poly[i].getReal() / n;

            return result;
        }

        Complex[] getOmega(int n, boolean inverse) {
            Complex[] omega = new Complex[n];
            return omegaComputation(omega, n, inverse);
        }

        private Complex[] omegaComputation(Complex[] x, int length, boolean inverse) {
            for(int i=0;i<length;i++){
                x[i] = new Complex(Math.cos((2*i*Math.PI)/length), (inverse ? -1 : 1)*Math.sin((2*i*Math.PI)/length));
            }
            return x;
        }

        /**
         * The function that calculates Fast Fourier Transform.
         *
         * @param a The complex numbers.
         * */
        private Complex[] fastFourierTransform(Complex[] a, Complex[] omega, int length, int pow) {

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

            Complex[] solutionEven = fastFourierTransform(aEven, omega, length/2, pow*2);
            Complex[] solutionOdd = fastFourierTransform(aOdd, omega, length/2, pow*2);

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
