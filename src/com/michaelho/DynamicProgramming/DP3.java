package com.michaelho.DynamicProgramming;

import com.michaelho.DataObjects.Edge;
import com.michaelho.DataObjects.Graph;

import java.util.ArrayList;

/**
 * The DP3 class explores a set of dynamic programming questions and solutions such as Dijstra's algorithm,
 * .
 *
 * @author Michael Ho
 * @since 2014-09-21
 * */
class DP3 {
    /**
     * The Dijkstra's method used to calculate shortest path among vertices in a graph. This method does not
     * consider negative cycles in the graph.
     * Reference: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
     *
     * @param graph The graph data structure to find shortest path algorithm.
     * @param src   The source (start point) of the graph.
     * */
    int[] shortestPathDijkstra(Graph graph, int src) {
        int V = graph.V;
        int[] dist = new int[V];

        // sptSet[i] is true if vertex i is included in shortest path tree
        // or shortest distance from src to i is finalized
        boolean[] sptSet = new boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, V);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            ArrayList<Edge> edges = graph.getEdgesList();
            for (int v = 0; v < V; v++)
                // Update dist[v] only if it is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                for (Edge edge : edges) {
                    if (!sptSet[v] && edge.src.val == u && edge.dest.val == v
                            && dist[u] != Integer.MAX_VALUE && dist[u] + edge.weight < dist[v])
                        dist[v] = dist[u] + edge.weight;
                }
        }
        return dist;
    }
    /**
     * Calculate the shortest distance of two vertices.
     *
     * @param dist The distance array which is used to store shortest distance of src to other vertices.
     * @param sptSet The boolean array that indicates whether the vertex is processed.
     * @param V The vertex to calculate shortest distance.
     * */
    int minDistance(int[] dist, boolean[] sptSet, int V) {
        // Initialize min value
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    /**
     * The dynamic programming method used to calculate the shortest path among vertices with negative cycle.
     * Dijkstra’s algorithm requires all edge weight ≥ 0 while Bellman Ford algorithm address the issue.
     * Bellman Ford algorithm detects negative cycle. The runtime is O(VE).
     * Reference: https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
     *
     * @param graph The graph data structure to find shortest path algorithm.
     * @param src   The source (start point) of the graph.
     */
    int[] shortestPathBellmanFord(Graph graph, int src) {
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];

        // Initialize distances from src to all other vertices as INFINITE
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // The distance to self is 0
        dist[src] = 0;

        // Relax all edges |V| - 1 times.
        // A simple shortest path from src to any other vertex
        // can have at-most |V| - 1 edges.
        ArrayList<Edge> edges = graph.getEdgesList();
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges.get(j).src.val;
                int v = edges.get(j).dest.val;
                int weight = edges.get(j).weight;

                // Update the current solution if there is a shorter one.
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Check for negative-weight cycles. The above step guarantees
        // shortest distances if graph doesn't contain negative weight cycle.
        // If we get a shorter path, then there is a cycle.
        for (int j=0; j < E; j++) {
            int u = edges.get(j).src.val;
            int v = edges.get(j).dest.val;
            int weight = edges.get(j).weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u]+weight < dist[v])
                System.out.println("Graph contains negative weight cycle");
        }
        return dist;
    }
}
