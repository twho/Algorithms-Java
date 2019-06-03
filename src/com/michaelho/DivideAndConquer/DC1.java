package com.michaelho.DivideAndConquer;

/**
 * The DC1 class explores a set of divide and conquer algorithms such as Gauss's idea and fast multiplication.
 *
 * @author Michael Ho
 * @since 2015-01-10
 * */
public class DC1 {

    /**
     * Calculate multiplication using naive approach which is less efficient.
     * Runtime: O(N^2)
     * LeetCode: https://leetcode.com/problems/multiply-strings/
     *
     * @param x The first number.
     * @param y The second number.
     *
     * @return The calculation result.
     * */
    int calculateByNaiveApproach(int x, int y) {
        String num1 = new StringBuilder(Integer.toString(x)).reverse().toString();
        String num2 = new StringBuilder(Integer.toString(y)).reverse().toString();

        String output = "";

        for (int idx1 = 0; idx1 < num1.length(); idx1++) {
            for (int idx2 = 0; idx2 < num2.length(); idx2++) {
                int val = Integer.valueOf(num1.substring(idx1, idx1+1))*Integer.valueOf(num2.substring(idx2, idx2+1));
                output = addDecimal(idx1+idx2, val, output);
            }
        }

        return Integer.valueOf(new StringBuilder(output).reverse().toString());
    }

    private String addDecimal(int decimal, int val, String output) {
        if (decimal < output.length()) {
            val += Integer.valueOf(output.substring(decimal, decimal+1));
            output = output.substring(0, decimal) + val%10 + output.substring(decimal+1);
        } else {
            output += val%10;
        }
        if (val/10 > 0) {
            return addDecimal(decimal+1, val/10, output);
        }
        return output;
    }

    int calculateByFastApproach(int x, int y) {
        return calculateBinaryByFastApproach(Integer.toBinaryString(x), Integer.toBinaryString(y));
    }

    /**
     * Using divide and conquer method, a faster approach calculates in less time complexity.
     * See https://www.geeksforgeeks.org/karatsuba-algorithm-for-fast-multiplication-using-divide-and-conquer-algorithm/
     * for the formula.
     *
     * @param binaryX The first number as binary string.
     * @param binaryY The second number as binary string.
     *
     * @return The calculation result in integer number.
     * */
    int calculateBinaryByFastApproach(String binaryX, String binaryY) {
        // Add zeros
        String extra = "";
        for (int i = 0; i < Math.abs(binaryY.length() - binaryX.length()); i++) {
            extra += "0";
        }
        if (binaryX.length() < binaryY.length()) {
            binaryX = extra + binaryX;
        } else if (binaryX.length() > binaryY.length()) {
            binaryY = extra + binaryY;
        }
        int n = binaryX.length();

        // Base cases
        if (n == 0) return 0;
        if (n == 1) return Integer.valueOf(binaryX)*Integer.valueOf(binaryY);

        int firstHalf = n/2;   // First half of string, floor(n/2)
        int secondHalf = (n-firstHalf); // Second half of string, ceil(n/2)

        String xL = binaryX.substring(0, firstHalf);
        String xR = binaryX.substring(firstHalf);

        // Find the first half and second half of second string
        String yL = binaryY.substring(0, firstHalf);
        String yR = binaryY.substring(firstHalf);

        // Recursively calculate the three products of inputs of size n/2
        int A = calculateBinaryByFastApproach(xL, yL);
        int B = calculateBinaryByFastApproach(xR, yR);
        int C = calculateBinaryByFastApproach(addBinary(xL, xR), addBinary(yL, yR));

        // Combine the three products to get the final result.
        return A*(1<<(secondHalf*2)) + (C - A - B)*(1<<(secondHalf)) + B;
    }

    private String addBinary(String x, String y){
        if (x.length() == 0 && y.length() == 0) {
            return "";
        } else if (x.length() == 0){
            return y;
        } else if (y.length() == 0) {
            return x;
        }
        int num1 = Integer.parseInt(x, 2);
        int num2 = Integer.parseInt(y, 2);
        return Integer.toBinaryString(num1 + num2);
    }
}
