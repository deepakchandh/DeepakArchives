package com.java.Greedy;

import java.util.Arrays;

public class MaxLengthPair {


    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (p1, p2)->p1[0]-p2[0]);
        int len = 0;
        int pre = Integer.MIN_VALUE;
        for(int[] pair : pairs){
            if(pair[0] > pre){  // not overlap
                len++;
                pre = pair[1];
            }
            else if(pair[1] < pre){ // overlap but with smaller second element
                pre = pair[1];
            }
        }
        return len;

    }
    public static void main(String args[])
    {
        int[][] pairs = new int[][]{
                {1,2}, {2,3}, {3,4}
        };

        System.out.println(findLongestChain(pairs));
    }

// [[1,2],[7,8],[4,5]]
}
