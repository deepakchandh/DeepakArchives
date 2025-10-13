package com.java.dynamic;

// https://leetcode.com/problems/paint-house/description/
public class PaintHouse {

    /*
    Each house’s cost depends on the minimum cost of the previous house, but you can’t use the same color.

    red[i]   = cost[i][0] + min(blue[i-1], green[i-1])
    blue[i]  = cost[i][1] + min(red[i-1], green[i-1])
    green[i] = cost[i][2] + min(red[i-1], blue[i-1])
     */
    public static int minCost(int[][] costs) {
        // adjacent house cannot have same color
        if (costs == null || costs.length == 0) return 0;

        int[] prevRow = costs[0];

        for(int i=1;i<costs.length;i++){
            int currRow[] = costs[i];
            int red = currRow[0] + Math.min(prevRow[1], prevRow[2]);
            int blue = currRow[1] + Math.min(prevRow[0], prevRow[2]);
            int green = currRow[2] + Math.min(prevRow[0], prevRow[1]);
            prevRow = new int[]{red, blue, green};
        }
        int res = Math.min(prevRow[0], Math.min(prevRow[1], prevRow[2]));

        return res;
    }

    public static void main(String[] args) {
        int[][] costs1 = {
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };

        int[][] costs2 = {
                {7, 6, 2}
        };

        System.out.println("Minimum cost 1: " + minCost(costs1)); // Expected 10
//        System.out.println("Minimum cost 2: " + sol.minCost(costs2)); // Expected 2
    }

}
