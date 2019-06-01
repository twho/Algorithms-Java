package com.michaelho.DataObjects;

/**
 * The hash node used in hash table implementation.
 * Reference: https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
 * */
public class HashNode<K, V> {
    public K key;
    public V value;

    // Reference to next node
    public HashNode<K, V> next;

    // Constructor
    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
