package com.michaelho.DynamicProgramming;

import com.michaelho.DataObjects.TreeNode;

/**
 * The DP2 class explores a set of dynamic programming questions and solutions such as
 * knapsack problem, knapsack with repetition items, chain matrix multiplication and
 * balanced binary tree.
 *
 * @author Michael Ho
 * @since 2014-09-20
 * */
class DP2 {

    /**
     * The dynamic programming used to calculate knapsack problems. The runtime is
     * O(NW) where N is the number of objects and W is the weight limit of the knapsack.
     * Note that the runtime is not polynomial in the input size.
     * Reference: https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
     *
     * @param wt The array represents the values of input objects.
     * @param val The array represents the weights of input objects.
     * @param idx The index of the object.
     * @param w The total weight allowed remaining.
     * @return int The results of calculation.
     * */
    int knapSackSol1(int[] wt, int[] val, int idx, int w) {
        // Base case
        if (w == 0 || idx == wt.length) {
            return 0;
        }

        if (wt[idx] <= w)
            return Math.max(val[idx] + knapSackSol1(wt, val, idx+1, w-wt[idx]),
                    knapSackSol1(wt, val, idx+1, w));
        else
            return knapSackSol1(wt, val, idx+1, w);
    }

    /**
     * The dynamic programming used to calculate knapsack problems with unlimited supply.
     * The runtime is O(NW) where N is the number of objects and W is the weight limit
     * of the knapsack. The runtime is the same as previous version of knapsack problem.
     *
     * @param wt The array represents the weights of input objects.
     * @param val The array represents the values of input objects.
     * @param w The total weight allowed remaining.
     * @return int The results of calculation.
     * */
    int knapSackSol2(int[] wt, int[] val, int w) {
        if (w == 0) {
            return 0;
        }

        int[] maxVals = new int[w+1];
        for (int b = 0; b <= w; b++) {
            for (int i = 0; i < val.length; i++) {
                if (wt[i] <= b) {
                    maxVals[b] = Math.max(maxVals[b], maxVals[b-wt[i]] + val[i]);
                }
            }
        }

        return maxVals[w];
    }

    /**
     * The dynamic programming used to calculate chain multiply matrix problems. Note
     * that the calculation merely focuses on how to decide which order to perform the
     * multiplication. The runtime is O(N^3) where N is the number of matrices. Example
     * input like {10, 20, 30, 40}, which stands for 3 matrices 10x20, 20x30 and 30x40.
     *
     * @param m The array of matrix, refer to the description above for the expressions.
     * @param i The start of the serial calculation.
     * @param j The tail of the serial calculation.
     * @return The result of the minimum cost calculation.
     * */
    int chainMatrixMultiply(int[] m, int i, int j) {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;
        for (int l = i; l < j; l ++) {
            int count = chainMatrixMultiply(m, i, l) +
                        chainMatrixMultiply(m, l+1, j) +
                        m[i-1]*m[l]*m[j];

            min = Math.min(min, count);
        }
        return min;
    }

    /**
     * The method is used to determine if the binary tree is balanced. The definition of
     * a balanced binary tree means that each two subtrees of a root has the height difference
     * of 1 or less.
     *
     * @param root The root node of the binary tree.
     * @return The boolean indicates if the tree is balanced.
     * */
    boolean isBalancedBinaryTree(TreeNode root) {
        if (root == null)
            return true;

        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1
                && isBalancedBinaryTree(root.left)
                && isBalancedBinaryTree(root.right);
    }

    /**
     * Calculate the height of the tree.
     *
     * @param tree The root node of a tree.
     * @return The height as integer that represents the tree height.
     * */
    int getTreeHeight(TreeNode tree) {
        if (tree == null)
            return 0;
        return 1 + Math.max(getTreeHeight(tree.left), getTreeHeight(tree.right));
    }

    // TODO: Complete the problem.
    int palindromicSubstrings(String str) {
        return 0;
    }
}
