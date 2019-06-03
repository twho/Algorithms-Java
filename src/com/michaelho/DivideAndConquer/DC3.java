package com.michaelho.DivideAndConquer;

/**
 * The DC3 class explores a set of divide and conquer algorithms such as program for geometric series.
 *
 * @author Michael Ho
 * @since 2015-01-12
 * */
public class DC3 {

    /**
     * The naive method to calculate the sum of geometric series.
     *
     * @param a The first term of the series.
     * @param r The common ratio.
     * @param n The number of terms.
     *
     * @return The result of the sum of the series.
     * */
    float sumOfGS(float a, float r, int n) {
        float sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a;
            a = a * r;
        }
        return sum;
    }

    /**
     * The method using formula to calculate the sum of geometric series.
     * The formula: a(1 – rn)/(1 – r)
     *
     * @param a The first term of the series.
     * @param r The common ratio.
     * @param n The number of terms.
     *
     * @return The result of the sum of the series.
     * */
    float sumOfGSByFormula(float a, float r, int n) {
        return (a * (1 - (int)(Math.pow(r, n)))) / (1 - r);
    }
}
