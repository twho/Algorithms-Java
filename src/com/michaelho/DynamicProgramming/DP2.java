package com.michaelho.DynamicProgramming;

import com.michaelho.Main;

/**
 * The DP2 class explores a set of dynamic programming questions and solutions such as
 * .
 *
 * @author Michael Ho
 * @since 2014-09-20
 * */
class DP2 {
    /**
     * The dynamic programming used to calculate Fibonacci numbers. This method
     * is inefficient in runtime, which is O(NW) where N is the number of objects
     * and W is the weight limit of the knapsack.
     *
     * @param wt
     * @param val
     * @return int The results of calculation.
     * */
    int knapSackSol1(int[] wt, int[] val, int idx, int w) {
        // Base case
        if (w == 0 || idx == 0) {
            return 0;
        }

        if (wt[idx] <= w)
            return Math.max(val[idx] + knapSackSol1(wt, val, idx-1, w-wt[idx]),
                    knapSackSol1(wt, val, idx-1, w));
        else
            return knapSackSol1(wt, val, idx-1, w);
    }
}
