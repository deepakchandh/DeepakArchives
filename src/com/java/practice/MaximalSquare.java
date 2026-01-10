package com.java.practice;

// https://leetcode.com/problems/maximal-square/description/

public class MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int maxSide = 0;

        /*
        if matrix[i][j] == '1':
            dp[i][j] = min(top, left, top-left) + 1
        else:
            dp[i][j] = 0
         */

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(
                                Math.min(dp[i-1][j], dp[i][j-1]),
                                dp[i-1][j-1]
                        ) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        int result = maximalSquare(matrix);
        System.out.println("Maximal Square Area = " + result);
    }
}
