package com.michaelho.DynamicProgramming;

import com.michaelho.DataObjects.Graph;
import com.michaelho.DataObjects.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DPTests {

    private DP1 dp1 = new DP1();
    private DP2 dp2 = new DP2();
    private DP3 dp3 = new DP3();

    @Test
    public void testFibonacci() {
        int input = 5;
        int expected = 5;
        fibTest(input, expected);
        fibDpTest(input, expected);
    }

    private void fibTest(int input, int expected) {
        int output = dp1.fib.count(input);
        assertEquals(expected, output);
    }

    private void fibDpTest(int input, int expected) {
        int output = dp1.fib.dpCount(input);
        assertEquals(expected, output);
    }

    @Test
    public void testLIS() {
        int[] input = {5, 7, 4, -3, 9, 1, 10, 4, 5, 8, 9, 3};
        int expected = 6;
        runLIS(input, expected);
    }

    private void runLIS(int[] input, int expected) {
        int output = dp1.lis.dpFindLongestLength(input);
        assertEquals(expected, output);
    }

    @Test
    public void testLCS() {
        String input1 = "BCDBCDA";
        String input2 = "ABECBA";
        int expected = 4;
        runLCS(input1, input2, expected);
    }

    private void runLCS(String input1, String input2, int expected) {
        int output = dp1.lcs.lengthOfLCS(input1, input2);
        assertEquals(expected, output);
    }

    @Test
    public void testLUS1() {
        String[] input = {"aba", "cdc", "eae"};
        int expected = 3;
        runLUS(input, expected);
    }

    @Test
    public void testLUS2() {
        String[] input = {"aabbcc", "aabbcc","cb","abc"};
        int expected = 2;
        runLUS(input, expected);
    }

    private void runLUS(String[] strs, int expected) {
        int output = dp1.lus.findLUSlength(strs);
        assertEquals(expected, output);
    }

    @Test
    public void testKnapsack1() {
        int[] wt = new int[]{10, 20, 30};
        int[] val = new int[]{60, 100, 120};
        int w = 50;
        int expected = 220;
        runKnapsack1(wt, val, w, expected);
    }

    private void runKnapsack1(int[] wt, int[] val, int w, int expected) {
        int output = dp2.knapSackSol1(wt, val, 0, w);
        assertEquals(expected, output);
    }

    @Test
    public void testKnapsackUnltdSup() {
        int[] wt = new int[]{5, 10, 15};
        int[] val = new int[]{10, 30, 20};
        int w = 100;
        int expected = 300;
        runKnapsackUnltdSup(wt, val, w, expected);
    }

    private void runKnapsackUnltdSup(int[] wt, int[] val, int w, int expected) {
        int output = dp2.knapSackSol2(wt, val, w);
        assertEquals(expected, output);
    }

    @Test
    public void testChainMatrixMultiply() {
        int[] matrix = new int[]{1, 2, 3, 4, 3};
        int expected = 30;
        runChainMatrixMutiply(matrix, expected);
    }

    private void runChainMatrixMutiply(int[] m, int expected) {
        int output = dp2.chainMatrixMultiply(m, 1, m.length-1);
        assertEquals(expected, output);
    }

    @Test
    public void testBalancedBST() {
        int[] treeArr = new int[]{3, 9, 20, 0, 0, 15, 7};
        assertTrue(dp2.isBalancedBinaryTree(new TreeNode(treeArr)));
    }

    private Graph createGraph(boolean negative) {
        int V = 5;  // Number of vertices in graph
        int E = 8;  // Number of edges in graph

        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.addEdge(0, 1, negative ? -1 : 1);

        // add edge 0-2
        graph.addEdge(0, 2, 4);

        // add edge 1-2
        graph.addEdge(1, 2, 3);

        // add edge 1-3
        graph.addEdge(1, 3, 2);

        // add edge 1-4
        graph.addEdge(1, 4, 2);

        // add edge 3-2
        graph.addEdge(3, 2, 5);

        // add edge 3-1
        graph.addEdge(3, 1, 1);

        // add edge 4-3
        graph.addEdge(4, 3, negative ? -3 : 3);

        return graph;
    }

    @Test
    public void testBellmanFord() {
        Graph graph = createGraph(true);
        int[] dist = dp3.bf.shortestPath(graph, 0);
        int[] result = {0, -1, 2, -2, 1};
        for (int i = 0; i < dist.length; i++) {
            assertEquals(result[i], dist[i]);
        }
    }

    @Test
    public void testDijkstra() {
        Graph graph = createGraph(false);
        int[] dist = dp3.dij.shortestPath(graph, 0);
        int[] result = {0, 1, 4, 3, 3};
        for (int i = 0; i < dist.length; i++) {
            assertEquals(result[i], dist[i]);
        }
    }
}

