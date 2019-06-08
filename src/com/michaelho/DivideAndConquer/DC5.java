package com.michaelho.DivideAndConquer;

import com.michaelho.DataObjects.Complex;

/**
 * The DC5 class explores a set of divide and conquer algorithms such as polynomials multiplication using
 * Fast Fourier Transform Algorithm.
 *
 * @author Michael Ho
 * @since 2015-01-12
 * */
public class DC5 {
    DC4 dc4 = new DC4();

    /**
     * Calculate the multiplication of 2 polynomials.
     *
     * @param p The coefficients of the first polynomials in double.
     * @param q The coefficients of the second polynomials in double.
     * @param omega The omega w in the FFT formula.
     * @param omegaInv The omega inverse w^-1 in the FFT formula.
     *
     * @return The result of the multiplication.
     * */
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
        Complex[] solp = dc4.fft.FFT(pInteger, omega, n, pow);
        Complex[] solq = dc4.fft.FFT(qInteger, omega, n, pow);

        // Multiply the results point-wise recursive
        Complex[] finalSol = new Complex[n];
        for (int i = 0; i < n; i++)
            finalSol[i] = solp[i].multiply(solq[i]);

        // Apply the FFT to the point-wise product
        Complex[] poly = dc4.fft.FFT(finalSol, omegaInv, n, pow);

        double[] result = new double[n-1];
        for (int i=0; i < n-1; i++)
            result[i] = poly[i].getReal() / n;

        return result;
    }

    /**
     * Calculate the omega w value.
     *
     * @param n The length of the coefficient array.
     * @param inverse Set true if needs to calculate inverse values.
     *
     * @return The coefficients of the omega w in the formula.
     * */
    Complex[] getOmega(int n, boolean inverse) {
        Complex[] omega = new Complex[n];
        for(int i=0;i<n;i++){
            omega[i] = new Complex(Math.cos((2*i*Math.PI)/n), (inverse ? -1 : 1)*Math.sin((2*i*Math.PI)/n));
        }
        return omega;
    }
}
