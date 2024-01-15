package com.java.concepts;

import java.util.*;

//https://www.geeksforgeeks.org/lru-cache-implementation/

public class LRUCache {
    private Deque<Integer> doublyQueue;

    private HashSet<Integer> hashSet;

    private final int CACHE_SIZE;

    LRUCache(int capacity)
    {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    /* Refer the page within the LRU cache */
    public void refer(int page)
    {
        if (!hashSet.contains(page)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                int last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
        }
        else { /* The found page may not be always the last
                element, even if it's an intermediate
                element that needs to be removed and added
                to the start of the Queue */
            doublyQueue.remove(page); // removes first occurance of the element.
        }
        doublyQueue.push(page);
        hashSet.add(page);
    }

    // display contents of cache
    public void display()
    {
        Iterator<Integer> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        LRUCache cache = new LRUCache(4);
        cache.refer(1);
        cache.refer(2);
        cache.refer(3); // 3 2 1
        cache.refer(1);// -> 1 3 2
        cache.refer(4); // -> 4 1 3 2
        cache.refer(5); // -> 5 4 1 3
        cache.refer(1); // -> 1 5 4 3


        cache.display();
    }
}
