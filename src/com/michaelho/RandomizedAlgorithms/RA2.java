package com.michaelho.RandomizedAlgorithms;

import com.michaelho.Main;

/**
 * The RA2 class explores a set of randomized algorithms such as RSA algorithm.
 *
 * @author Michael Ho
 * @since 2014-10-02
 * */
class RA2 {
    FermatTheorem ft = new FermatTheorem();
    EulerTheorem et = new EulerTheorem();
    RSA rsa = new RSA();
    PrimalityTest pt = new PrimalityTest();

    /**
     * The class contains Fermat's little theorem and Fermat's last theorem.
     * */
    class FermatTheorem {
        /**
         * Calculate modular inverse by applying Fermat's little theorem. This method
         * calculate The modular inverse of x^y.
         *
         * @param x The number as basis.
         * @param y The number of power.
         * @return The modular inverse of x^y.
         * */
        int calculateModInverse(int x, int y) {
            RA1 ra1 = new RA1();
            if (ra1.gcd.euclidAlgorithm(x, y) != 1)
                return -1;
            else {
                // Based on Fermat's little theorem, if a and m are relatively prime,
                // then modulo inverse is x^(y-2) mod y.
                return power(x, y - 2, y);
            }
        }

        private int power(int x, int y, int m) {
            if (y == 0)
                return 1;
            int p = power(x, y / 2, m) % m;
            p = (p * p) % m;

            return (y % 2 == 0) ? p : (x * p) % m;
        }
    }

    /**
     * The class contains Euler's theorem and Euler Totient Function.
     * */
    class EulerTheorem {

        /**
         * Euler Totient Function, count all number under n and is a co-prime number of n.
         * For example, phi(5) = 4 since 1, 2, 3, 4 are the 4 numbers that are co-prime of 5 and are under 5.
         * Reference: https://www.geeksforgeeks.org/eulers-totient-function-for-all-numbers-smaller-than-or-equal-to-n/
         *
         * @param n The n is the above description.
         * @return the phi function result.
         * */
        int phi(int n) {
            int result = 1;
            RA1 ra1 = new RA1();
            for (int i = 2; i < n; i++)
                if (ra1.gcd.euclidAlgorithm(i, n) == 1)
                    result++;
            return result;
        }
    }

    /**
     * This class consists of algorithms required to perform RSA, which are generating public key pair, private key pair,
     * encryption, and decryption.
     * */
    class RSA {
        RA1 ra1 = new RA1();

        /**
         * Generate public key pair.
         *
         * @param p The selected prime number.
         * @param q Another selected prime number which is prime to p.
         *
         * @return a public key pair which consists of n and e where n is p*q and e is prime to (p-1)*(q-1).
         * */
        int[] getPublicKeyPair(int p, int q) {
            int e = 2;
            int phi = (p-1)*(q-1);
            while (e < phi) {
                if (ra1.gcd.euclidAlgorithm(e, phi) == 1)
                    break;
                else
                    e++;
            }
            return new int[] {p*q, e};
        }

        /**
         * Generate private key.
         *
         * @param p The p chosen for generating public key.
         * @param q The q chosen for generating public key.
         *
         * @return a double that represents private key.
         * */
        double getPrivateKey(int p, int q, int e) {
            return ra1.ma.multiplicativeInverse(e, (p-1)*(q-1));
        }

        /**
         * The implementation of RSA encryption, which requires public key.
         *
         * @param message The message to be encrypted.
         * @param publicKeyPair The public key pair which must have n (=p*q) and e.
         *
         * @return the encrypted message as a double.
         * */
        double encrypt(Double message, int[] publicKeyPair) {
            // Encryption c = (msg ^ e) % n
            int n = publicKeyPair[0];
            int e = publicKeyPair[1];
            return ra1.ma.modularExp(message.intValue(), e, n);
        }

        /**
         * The implementation of RSA decryption, which requires private key.
         *
         * @param message The encrypted message to be decrypted.
         * @param n The number where n = p*q.
         * @param d The private key.
         *
         * @return the decrypted message.
         * */
        double decrypt(Double message, int n, Double d) {
            return ra1.ma.modularExp(message.intValue(), d.intValue(), n);
        }
    }

    /**
     * This class consists of methods used to determine if the number is a prime number. Methods such as
     * naive method and Fermat's method.
     * Reference: https://www.geeksforgeeks.org/primality-test-set-1-introduction-and-school-method/
     * */
    class PrimalityTest {

        /**
         * Determine if the number is a prime number.
         *
         * @param n The number to be determined.
         * @return a boolean indicate whether the number is a prime number.
         * */
        boolean isPrime(int n) {
            // Corner case
            if (n <= 1) return false;

            for (int i = 2; i*i < n; i++)
                if (n % i == 0)
                    return false;
            return true;
        }

        /**
         * Determine if the number is a prime number using Fermat's method.
         * Reference: https://www.geeksforgeeks.org/primality-test-set-2-fermet-method/
         *
         * @param n The number to be determined.
         * @param k Try k times.
         * */
        boolean isPrimeUsingFermat(int n, int k) {
            // Handle edge cases where n = 1, 2, 3, 4
            if (n <= 1 || n == 4) return false;
            if (n <= 3) return true;

            RA1 ra1 = new RA1();

            // Try k times
            while (k > 0) {
                // Pick a random number in [2..n-2]
                int x = 2 + (int)(Math.random() % (n - 4));

                // Fermat's little theorem
                if (ra1.ma.modularExp(x, n - 1, n) != 1)
                    return false;

                k--;
            }

            return true;
        }
    }
}