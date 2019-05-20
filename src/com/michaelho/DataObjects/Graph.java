package com.michaelho.DataObjects;

import java.util.ArrayList;
import java.util.LinkedList;
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

    // Adds an edge to an undirected graph
    public ArrayList<Edge> getEdgesList() {
        return edges;
    }
}
