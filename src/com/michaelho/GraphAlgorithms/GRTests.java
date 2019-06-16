package com.michaelho.GraphAlgorithms;

import com.michaelho.DataObjects.Graph;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GRTests {
    GR1 gr1 = new GR1();

    @Test
    public void testDFSTraverse() {
        Graph graph = Graph.createGraph(false);
        int[] expected = new int[] { 0, 1, 2, 3, 4 };
        assertArrayEquals(expected, gr1.dfs.traverse(graph));
    }

    @Test
    public void testTopologicalSort() {
        Graph graph = Graph.getGraphForTopologicalSort();
        int[] expected = new int[] { 5, 4, 2, 3, 1, 0 };
        assertArrayEquals(expected, gr1.dfs.topologicalSort(graph));
    }

    @Test
    public void testFindSCC() {
        Graph graph = Graph.getGraphForFindingSCC();
        String expected = "[[0, 1, 2], [3], [4]]";
        assertEquals(expected, gr1.scc.findSCCs(graph).toString());
    }
}
