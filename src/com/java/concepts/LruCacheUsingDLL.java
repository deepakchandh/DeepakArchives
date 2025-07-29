package com.java.concepts;

import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/lru-cache/
public class LruCacheUsingDLL {

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LruCacheUsingDLL(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // Dummy head and tail nodes for easier handling
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(node);
        insertAtHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node existing = map.get(key);
            existing.value = value;
            remove(existing);
            insertAtHead(existing);

        }
        else{
            if(map.size() == capacity){
                // Remove LRU from DLL and map
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            insertAtHead(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LruCacheUsingDLL lru = new LruCacheUsingDLL(2); // capacity = 2

        lru.put(1, 1);    // cache is {1=1}
        lru.put(2, 2);    // cache is {1=1, 2=2}
        System.out.println(lru.get(1)); // returns 1, cache becomes {2=2, 1=1}

        lru.put(3, 3);    // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lru.get(2)); // returns -1 (not found)

        lru.put(4, 4);    // evicts key 1, cache is {3=3, 4=4}
        System.out.println(lru.get(1)); // returns -1 (not found)
        System.out.println(lru.get(3)); // returns 3
        System.out.println(lru.get(4)); // returns 4
    }
}
