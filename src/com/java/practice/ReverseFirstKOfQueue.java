package com.java.practice;

import java.util.*;

public class ReverseFirstKOfQueue {

    static Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        solve(q, k);
        int s = q.size() - k;
        while( s-- > 0){
            int x = q.poll();
            q.add(x);
        }
        return q;
    }
    static void solve(Queue<Integer> q, int k){
        if(k == 0) return;
        int e = q.poll();
        solve(q, k - 1);
        q.add(e);
    }

    // driver code
    public static void main (String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);


        int k = 3;
        queue = reverseFirstK(queue, k);
        // printing queue
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }
}
