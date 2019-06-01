package com.michaelho.RandomizedAlgorithms;

import com.michaelho.DataObjects.HashNode;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * The RA3 class explores a set of randomized algorithms such as chain hashing, hash table implementation
 * and bloom filter.
 *
 * @author Michael Ho
 * @since 2014-10-07
 * */
class RA3 {
    /**
     * The map class use integer as key and string as value. Note that key and value can be changed
     * to other types.
     * */
    MapClass<Integer, String> map = new MapClass<>();
    BloomFilter bf = new BloomFilter(15, (float) 0.01);

    public class MapClass<K, V> {
        // bucketArray is used to store array of chains
        private ArrayList<HashNode<K, V>> bucketArray;

        // Current capacity of array list
        private int numBuckets;

        // Current size of array list
        private int size;

        // Load factor threshold
        private double loadThreshold = 0.7;

        // Constructor (Initializes capacity, size and empty chains.
        public MapClass() {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;

            // Create empty chains
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
        }

        public int size() { return size; }
        public boolean isEmpty() { return size() == 0; }

        /**
         * The hash function to find the index of a key.
         *
         * @param key The key to input to hash function.
         *
         * @return The index of the key the mapped to the array list.
         * */
        private int hash(K key) {
            return key.hashCode() % numBuckets;
        }

        // Method to remove a given key
        public V remove(K key) {
            int bucketIndex = hash(key);

            // Get head of chain
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Search for key in its chain
            HashNode<K, V> prev = null;
            while (head != null) {
                // If Key found
                if (head.key.equals(key))
                    break;

                // Else keep moving in chain
                prev = head;
                head = head.next;
            }

            // If key was not there
            if (head == null)
                return null;

            // Reduce size
            size--;

            // Remove key
            if (prev != null)
                prev.next = head.next;
            else
                bucketArray.set(bucketIndex, head.next);

            return head.value;
        }

        // Returns value for a key
        public V get(K key) {
            int bucketIndex = hash(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Search key in chain
            while (head != null) {
                if (head.key.equals(key))
                    return head.value;
                head = head.next;
            }

            // If key not found
            return null;
        }

        // Adds a key value pair to hash
        public void add(K key, V value) {
            int bucketIndex = hash(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Check if key is already present, replace the old value if it exists.
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            // Insert key in chain
            size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode = new HashNode<>(key, value);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);

            // If load factor goes beyond threshold, then double hash table size
            if ((1.0 * size) / numBuckets >= loadThreshold) {
                ArrayList<HashNode<K, V>> temp = bucketArray;
                bucketArray = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;
                for (int i = 0; i < numBuckets; i++)
                    bucketArray.add(null);

                for (HashNode<K, V> headNode : temp) {
                    while (headNode != null) {
                        add(headNode.key, headNode.value);
                        headNode = headNode.next;
                    }
                }
            }
        }
    }

    /**
     * Implementation of a bloom filter.
     * Reference: https://www.geeksforgeeks.org/bloom-filters-introduction-and-python-implementation/
     * */
    class BloomFilter {

        private byte[] bitArray;
        private int size, hashCount;
        private MessageDigest md;

        /**
         * Constructor of bloom filter.
         *
         * @param capacity The number of items expected to be stored in bloom filter.
         * @param falsePostiveProb False possibility in float.
         * */
        public BloomFilter(int capacity, float falsePostiveProb) {
            // Size of bit array to use
            this.size = this.getSize(capacity, falsePostiveProb);

            // Number of hash functions
            this.hashCount = this.getHashCount(size, capacity);

            // Bit array of given size
            bitArray = new byte[this.size];

            // Initialize all bits as 0
            for (int i = 0; i < bitArray.length; i ++) {
                bitArray[i] = 0;
            }

            // Used for hashing
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("Error : MD5 Hash not found");
            }
        }

        /**
         * Calculate the size of bit array(m) to used using the following formula.
         * m = -(count * log(prob)) / (log(2)^2)
         *
         * @param count The number of items expected to be stored in filter.
         * @param prob The false positive probability.
         *
         * @return The size of bit array to used.
         * */
        public int getSize(int count, float prob) {
            double m = -(count * Math.log(prob)/(Math.pow(Math.log(2), 2)));
            return (int) m;
        }

        /**
         * Calculate the number of hash functions using the following formula.
         * k = (size/count) * log(2)
         *
         * @param size The size of bit array.
         * @param count The number of items expected to be stored in filter.
         *
         * @return the hash function(k) to be used.
         * */
        private int getHashCount(int size, int count) {
            double k = (size/count) * Math.log(2);
            return (int) k;
        }

        /**
         * Function to add an object.
         * */
        public void add(Object data) {
            int[] keys = getCompleteHash(data);
            for (int i = 0; i < keys.length; i++) {
                this.bitArray[keys[i]] = 1;
                System.out.println(keys[i] + " size: " + this.size);
            }
        }

        /* Function to get set array for an object */
        private int[] getCompleteHash(Object data) {
            int[] hashKeys = new int[hashCount];
            hashKeys[0] = getHash(data.hashCode());
            for (int i = 1; i < hashCount; i++)
                hashKeys[i] = (getHash(hashKeys[i - 1]));
            return hashKeys;
        }

        // Hashing
        private int getHash(int i) {
            md.reset();
            byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
            md.update(bytes, 0, bytes.length);
            return Math.abs(new BigInteger(1, md.digest()).intValue()) % (bitArray.length - 1);
        }

        /**
         * Check if the data exists in the database.
         *
         * @param data The data to be checked.
         *
         * @return The boolean indicates whether the data exists in the database.
         * */
        public boolean contains(Object data) {
            int[] keys = getCompleteHash(data);
            for (int i = 0; i < keys.length; i++) {
                if (this.bitArray[keys[i]] != 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
