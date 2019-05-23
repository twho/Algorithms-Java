package com.michaelho.RandomizedAlgorithms;

/**
 * The RA1 class explores a set of randomized algorithms such as modular arithmetic and euclid algorithms
 * used to calculate greatest common divisors.
 *
 * @author Michael Ho
 * @since 2014-09-30
 * */
class RA1 {
    ModularArithmetic ma = new ModularArithmetic();
    GreatestCommonDivisor gcd = new GreatestCommonDivisor();

    /**
     * The class contains modular multiplicative inverse algorithm.
     * */
    class ModularArithmetic {
        /**
         * Modular exponentiation, the power in Modular Arithmetic.
         * Reference: https://www.geeksforgeeks.org/modular-exponentiation-power-in-modular-arithmetic/
         *
         * @param x The number as base to be calculated.
         * @param y The exponential number.
         * @param N The mod number.
         * @return The result of modular exponential.
         * */
        int modularExp(int x, int y, int N) {
            int res = 1;

            x = x % N;
            while (y > 0) {
                // If y is odd, multiply x with result
                if (y % 2 == 1)
                    res = res * x % N;

                // y must be even now
                y = y / 2;
                x = x * x % N;
            }
            return res;
        }

        /**
         * Multiplicative inverse is another name for reciprocal, which is try to find the number to multiply
         * in order to get 1. For example, if x = 3, N = 11, the result will be 4 since 3*4%11 = 1.
         * Given two integers x and N, find modular multiplicative inverse of x under modulo N.
         * Reference: https://www.geeksforgeeks.org/modular-multiplicative-inverse-1-n/
         *
         * @param x The integer as base number.
         * @param N the modulo N.
         * @return The multiplicative inverse of the x mod N, which is indicated as x^-1 mod N.
         * */
        int multiplicativeInverse(int x, int N) {
            x = x % N;
            for (int i = 1; i < N; i++) {
                if ((x * i) % N == 1)
                    return i;
            }

            // If the multiplicative inverse does not exist.
            return -1;
        }
    }

    /**
     * The class covers all greatest common divisor solutions in the course.
     * */
    class GreatestCommonDivisor {

        /**
         * The euclid algorithm used to calculate greatest common divisor.
         * The runtime is O(Log min(a, b))
         *
         * @param x The larger number to calculate GCD.
         * @param y The smaller number to calculate GCD.
         * @return The greatest common divisor derived by the algorithm.
         * */
        int euclidAlgorithm(int x, int y) {
            if (x == 0)
                return y;

            return euclidAlgorithm(y%x, x);
        }

        /**
         * Extended euclid algorithm where xa + yb = gcd(x, y)
         *
         * @param x The larger number.
         * @param y The smaller number.
         * @return The results of extended Euclid's algorithm.
         * */
        int[] extEuclidAlgorithm(int x, int y) {
            if (y == 0)
                return new int[] {x, 1, 0};

            int[] values = extEuclidAlgorithm(y, x % y);
            int d = values[0];
            int a = values[2];
            int b = values[1] - (x / y) * values[2];
            return new int[] {d, a, b};
        }
    }
}
