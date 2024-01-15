package com.java.practice;

import java.util.PriorityQueue;

public class MaxLengthOfPairChain {

    public static int findLongestChain(int[][] pairs) {
        // 2nd element comparison
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>( (a, b) -> {
//            return Integer.compare(a[1], b[1]);
//        });

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>( (a, b) -> a[1] - b[1]);

        for(int[] pair: pairs){
            pq.add(pair);
        }

        int[] current = pq.poll();
        int result = 1;

        while(pq.size() > 0){
            int[] next = pq.poll();
            if (current[1] >= next[0])
                continue;
            else {
                result++;
                current = next;
            }
        }
        return result;

    }

    public static void main(String[] args) {

        System.out.println(findLongestChain(new int[][]{{1,2}, {10, 11}, {7,8}, {4,6}}));

    }
}
