package com.michaelho.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The DP1 class explores a set of dynamic programming questions and solutions such as
 * Fibonacci, longest common subsequence (LCS), longest uncommon subsequence (LUS),
 * and longest increasing subsequence (LIS).
 *
 * @author Michael Ho
 * @since 2014-09-14
 * */
class DP1 {
    Fibonacci fib = new Fibonacci();
    LIS lis = new LIS();
    LUS lus = new LUS();
    LCS lcs = new LCS();

    /**
     * The Fibonacci class explores the methods used to calculate the Fibonacci number.
     * Methods include recursive and dynamic programming.
     * */
    class Fibonacci {
        /**
         * The recursive method used to calculate Fibonacci numbers. This method
         * is inefficient in runtime, which is O(2^N).
         *
         * @param x The number to be calculated Fibonacci numbers.
         * @return int The results of calculation.
         * */
        int count(int x) {
            if (x == 0) {
                return 0;
            } else if (x == 1) {
                return 1;
            }

            return count(x-1) + count(x-2);
        }
        /**
         * The dynamic programing method used to calculate Fibonacci numbers.
         * The method uses storage to reduced duplicate caluculation
         *
         * @param x The number to be calculated Fibonacci numbers.
         * @return int The results of calculation.
         * */
        int dpCount(int x) {
            int[] fArray = new int[x+1];
            fArray[0] = 0;
            fArray[1] = 1;

            for (int i = 2; i <= x; i++) {
                fArray[i] = fArray[i-1] + fArray[i-2];
            }

            return fArray[x];
        }
    }
    /**
     * The LCS class use the dynamic programming method to calculate the longest common subsequence
     * of given array. Subsequence is different from substring. Subsequence is a subset
     * of elements in order that can be derived from another sequence while substring has
     * to be a set of consecutive elements.
     * */
    class LCS {
        /**
         * The dynamic method used to find the longest length of subsequence. The runtime is O(N^2).
         *
         * @param s1 The first string to be calculated for longest increasing subsequence.
         * @param s2 The second string to be calculated for longest increasing subsequence.
         * @return int The results of calculation.
         */
        int lengthOfLCS(String s1, String s2) {
            return dpFindLongestLength(s1, s2, s1.length() - 1, s2.length() - 1);
        }

        /**
         * The recursive function used to find LCS.
         *
         * @param s1   The first string to be calculated for longest increasing subsequence.
         * @param s2   The second string to be calculated for longest increasing subsequence.
         * @param idx1 The index of the first string.
         * @param idx2 The index of the second string.
         * @return int The results of calculation.
         */
        private int dpFindLongestLength(String s1, String s2, int idx1, int idx2) {
            if (idx1 < 0 || idx2 < 0) {
                return 0;
            }
            if (s1.charAt(idx1) == s2.charAt(idx2))
                return 1 + dpFindLongestLength(s1, s2, idx1 - 1, idx2 - 1);
            else
                return Math.max(dpFindLongestLength(s1, s2, idx1, idx2 - 1),
                        dpFindLongestLength(s1, s2, idx1 - 1, idx2));
        }
    }
    /**
     * The LUS class explores the method to calculate the longest uncommon subsequence
     * of given array of strings.
     * */
    class LUS {
        /**
         * The method used to find the longest length of subsequence. The runtime is roughly O(N^2).
         *
         * @param strs The first string to be calculated for longest increasing subsequence.
         * @return int The length of LUS.
         * */
        int findLUSlength(String[] strs) {
            if (strs.length <= 1) return strs.length;

            List<String> strsList = Arrays.asList(strs);
            strsList.sort(Comparator.comparingInt(String::length).reversed()); // Compare by length

            for (int i = 0; i < strsList.size(); i++) {
                boolean isLUS = true;
                for (int j = 0; j < strsList.size(); j++) {
                    if (i != j && isSubsequence(strs[i], strs[j])) {
                        isLUS = false;
                        break;
                    }
                }
                if (isLUS) {
                    return strs[i].length();
                }
            }
            return -1;
        }

        boolean isSubsequence(String s1, String s2) {
            int i = 0;
            int j = 0;

            while (i < s1.length() && j < s2.length()) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    i++;
                }
                j ++;
            }
            return i == s1.length();
        }
    }
    /**
     * The LIS class explores the method to find the length of the longest increasing
     * subsequence. Subsequence is different from substring. Subsequence is a subset
     * of elements in order that can be derived from another sequence while substring
     * has to be a set of consecutive elements.
     * */
    class LIS {
        /**
         * The dynamic method used to find the longest length of subsequence. The runtime is O(N^2).
         *
         * @param array The array to be calculated for longest increasing subsequence.
         * @return int The results of calculation.
         * */
        int dpFindLongestLength(int[] array) {
            int max = 1;
            int[] countArr = new int[array.length];

            // O(N)
            for (int i = 0; i < array.length; i++) {
                countArr[i] = 1;
                // O(N)
                for (int j = 0; j < i; j ++) {
                    if (array[j] < array[i] && countArr[i] < countArr[j] + 1) {
                        countArr[i] = 1 + countArr[j];
                    }
                }
            }

            // O(N)
            for (int count : countArr) {
                max = Math.max(count, max);
            }

            return max;
        }
    }
}
