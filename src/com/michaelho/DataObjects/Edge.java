package com.michaelho.DataObjects;

public class Edge {
    public Node src;
    public Node dest;
    public int weight;

    public Edge() {
        src = new Node(0);
        dest = new Node(0);
        weight = 0;
    }

    public Edge(int srcVal, int destVal) {
        src = new Node(srcVal);
        dest = new Node(destVal);
        weight = 0;
    }
}