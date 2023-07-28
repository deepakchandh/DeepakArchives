package com.java.dynamic;

// https://leetcode.com/problems/unique-paths/description/

//https://leetcode.com/problems/unique-paths/solutions/1175465/java-3-approaches-dp-recursion-memoization/
public class UniquePathLC {
// recursion logicc
    public static int uniquePathss(int m, int n) {
        // base case
        if(m == 1 || n == 1)
            return 1;

        // move down
        int downMove = uniquePathss(m-1, n);
        // move right
        int rightMove = uniquePathss(m, n-1);

        return downMove + rightMove;
    }

    // dp logic
    public static int uniquePaths(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for(int i = 0; i<m;i++){
            map[i][0] = 1;
        }
        for(int j= 0;j<n;j++){
            map[0][j]=1;
        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                map[i][j] = map[i-1][j]+map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));

    }
}
