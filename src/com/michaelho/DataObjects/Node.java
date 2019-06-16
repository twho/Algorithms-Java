package com.michaelho.DataObjects;

public class Node {
    public int val;
    public Node next;

    Node(int d) {
        val = d;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Node node = (Node) o;

        // field comparison
        return node.val == this.val;
    }
}
