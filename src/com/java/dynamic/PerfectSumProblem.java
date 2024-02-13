package com.java.dynamic;
//https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1?page=1&company=Tesco&sortBy=submissions
public class PerfectSumProblem {
    static int count;
    static int sum;
    static int n;

    public static void perfectSumRecursive(int arr[],int i, int currSum){
        if (currSum == sum) {
            count++;
            return;
        }
        if (currSum < sum && i < n) {
            perfectSumRecursive(arr, i+1, currSum + arr[i]);
            perfectSumRecursive(arr, i+1, currSum);
        }
    }

    public static int perfectSum(int arr[],int n, int sum){
        int mod = 1000000007; // Modulo value

        // DP table to store the counts
        int[][] dp = new int[n + 1][sum + 1];

        // Initialize base cases
        dp[0][0] = 1;   //because If the sum is 0 then there exists null subset {} whose sum is 0

        // Fill the DP table
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < sum+1; j++) {

                if (arr[i - 1] <= j){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
                dp[i][j] %= mod;
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {

        // DP
        System.out.println(perfectSum(new int[]{5, 2, 3, 10, 6, 8}, 6, 10));

        // Recursion
//        count = 0;
//        n = 6;
//        sum = 10;
//
//        int[] pat = {2, 3, 5, 6, 8, 10};
//        perfectSumRecursive(pat, 0, 0);
//        System.out.println(count);
    }
}
