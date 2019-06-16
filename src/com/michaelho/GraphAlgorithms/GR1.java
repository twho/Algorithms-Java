package com.michaelho.GraphAlgorithms;

import com.michaelho.DataObjects.Edge;
import com.michaelho.DataObjects.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The GR1 class explores a set of graph algorithms such as DFS, topological sort,
 * and find strongly connected components (SCCs).
 *
 * @author Michael Ho
 * @since 2015-01-16
 * */
public class GR1 {
    DFS dfs = new DFS();
    SCC scc = new SCC();

    /**
     * The DFS class contains methods related to depth first search such as DFS itself and topological sort.
     * */
    class DFS {

        /**
         * Traverse through the graph using DFS method.
         * Runtime: O(V+E)
         *
         * @param graph The input graph.
         *
         * @return The values of vertices in the graph output in DFS order.
         * */
        int[] traverse(Graph graph) {
            boolean[] visited = new boolean[graph.V];
            int v = graph.getEdgesList().get(0).src.val;

            List<Integer> list = new ArrayList<>();
            DFSHelper(graph, list, v, visited);

            return list.stream().mapToInt(Integer::intValue).toArray();
        }

        /**
         * The function that is recursively used in DFS traversal.
         *
         * @param graph The input graph.
         * @param list The list that is used as output.
         * @param v The value of current vertex.
         * @param visited The boolean array that records whether the vertex is visited.
         * */
        private void DFSHelper(Graph graph, List<Integer> list, int v, boolean[] visited) {
            visited[v] = true;
            list.add(v);

            List<Edge> adjList = graph.getAdjacencyList(v);
            for (int i = 0; i < adjList.size(); i++) {
                if (!visited[adjList.get(i).dest.val]) {
                    DFSHelper(graph, list, adjList.get(i).dest.val, visited);
                }
            }
        }

        /**
         * The function that output the value of vertices in topological order. Note that a graph can
         * have multiple topological output.
         * Runtime: O(V+E)
         *
         * @param graph The input graph.
         *
         * @return The values of vertices in the graph output in topological order.
         * */
        int[] topologicalSort(Graph graph) {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[graph.V];

            for (int i = 0; i < graph.V; i++)
                if (!visited[i])
                    topologicalSortHelper(graph, stack, i, visited);

            List<Integer> output = new ArrayList<>();
            while (!stack.empty())
                output.add(stack.pop());

            return output.stream().mapToInt(Integer::intValue).toArray();
        }

        private void topologicalSortHelper(Graph graph, Stack<Integer> stack, int v, boolean[] visited) {
            visited[v] = true;

            List<Edge> adjList = graph.getAdjacencyList(v);
            for (int i = 0; i < adjList.size(); i++) {
                if (!visited[adjList.get(i).dest.val]) {
                    topologicalSortHelper(graph, stack, adjList.get(i).dest.val, visited);
                }
            }
            stack.push(v);
        }
    }

    /**
     * The SCC class contains methods used to find strongly connected components.
     * */
    class SCC {

        /**
         * Find all strongly connected component in the graph.
         * Runtime: O(V+E)
         *
         * @param graph The input graph.
         * */
        List<List<Integer>> findSCCs(Graph graph) {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[graph.V];

            // Fill vertices in stack according to their finishing times
            for (int i = 0; i < graph.V; i++)
                if (!visited[i])
                    markAdjacentVisited(graph, i, visited, stack);

            // Create a reversed graph
            Graph gr = reverseGraph(graph);

            // Mark all the vertices as not visited (For second DFS)
            for (int i = 0; i < graph.V; i++)
                visited[i] = false;

            List<List<Integer>> output = new ArrayList<>();
            while (!stack.empty()) {
                // Pop a vertex from stack
                int v = stack.pop();

                if (!visited[v]) {
                    List<Integer> list = new ArrayList<>();
                    dfs.DFSHelper(gr, list, v, visited);
                    output.add(list);
                }
            }
            return output;
        }

        private void markAdjacentVisited(Graph graph, int v, boolean[] visited, Stack<Integer> stack)  {
            visited[v] = true;

            List<Edge> adjList = graph.getAdjacencyList(v);
            for (int i = 0; i < adjList.size(); i++) {
                int adj = adjList.get(i).dest.val;
                if(!visited[adj])
                    markAdjacentVisited(graph, adj, visited, stack);
            }

            // All vertices reachable from v are processed by now, push v to Stack
            stack.push(v);
        }

        /**
         * The function to get the reversed graph.
         *
         * @param graph The graph to be reversed.
         *
         * @return A reversed graph of the input graph.
         * */
        private Graph reverseGraph(Graph graph) {
            Graph g = new Graph(graph.V, graph.E);

            for (int v = 0; v < graph.V; v++) {
                List<Edge> adjList = graph.getAdjacencyList(v);
                for (int i = 0; i < adjList.size(); i++) {
                    g.addEdge(adjList.get(i).dest.val, adjList.get(i).src.val, 1);
                }
            }
            return g;
        }
    }
}
