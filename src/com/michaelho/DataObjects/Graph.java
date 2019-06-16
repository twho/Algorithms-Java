package com.michaelho.DataObjects;

import com.michaelho.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure of graph
 * Refer to GeeksForGeeks website.
 *
 * @author Michael Ho
 * @since 2019-05-19
 * */
public class Graph {
    public int V;
    public int E;
    ArrayList<Edge> edges;

    /**
     * The constructor of graph data structure.
     *
     * @param v The count of vertices in the graph
     * @param e The count of edges in the graph.
     * */
    public Graph(int v, int e) {
        this.V = v;
        this.E = e;

        // define the size of array as
        // number of vertices
        edges = new ArrayList<>(e);
    }

    // Adds an edge to an undirected graph
    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest);
        edge.weight = weight;
        this.edges.add(edge);
    }

    public List<Edge> getEdgesList() {
        return edges;
    }

    public List<Edge> getAdjacencyList(int v) {
        List<Edge> adjacencyList = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).src.val == v) {
                adjacencyList.add(edges.get(i));
            }
        }
        return adjacencyList;
    }

    public static Graph createGraph(boolean negative) {
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

    public static Graph getGraphForTopologicalSort() {
        int V = 6;  // Number of vertices in graph
        int E = 6;  // Number of edges in graph

        Graph graph = new Graph(V, E);

        // add edge 5-2
        graph.addEdge(5, 2, 1);

        // add edge 5-0
        graph.addEdge(5, 0, 1);

        // add edge 4-0
        graph.addEdge(4, 0, 1);

        // add edge 4-1
        graph.addEdge(4, 1, 1);

        // add edge 2-3
        graph.addEdge(2, 3, 1);

        // add edge 3-1
        graph.addEdge(3, 1, 1);

        return graph;
    }

    public static Graph getGraphForFindingSCC() {
        int V = 5;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph

        Graph graph = new Graph(V, E);

        // add edge 1-0
        graph.addEdge(1, 0, 1);

        // add edge 0-2
        graph.addEdge(0, 2, 1);

        // add edge 0-3
        graph.addEdge(0, 3, 1);

        // add edge 2-1
        graph.addEdge(2, 1, 1);

        // add edge 3-4
        graph.addEdge(3, 4, 1);

        return graph;
    }
}