package com.java.dynamic;

public class MinimumSumPartition {


    static int findMinRec(int arr[], int i, int sumCalculated,
                   int sumTotal)
    {
        // If we have reached last element.  Sum of one
        // subset is sumCalculated, sum of other subset is
        // sumTotal-sumCalculated.  Return absolute difference
        // of two sums.
        if (i == 0)
            return Math.abs((sumTotal - sumCalculated)
                    - sumCalculated);

        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        return Math.min(
                findMinRec(arr, i - 1, sumCalculated + arr[i - 1], sumTotal),
                findMinRec(arr, i - 1, sumCalculated, sumTotal));
    }

    // Returns minimum possible difference between sums
// of two subsets
    static int findMin(int arr[], int n)
    {
        // Compute total sum of elements
        int sumTotal = 0;
        for (int i = 0; i < n; i++)
            sumTotal += arr[i];

        // Compute result using recursive function
        return findMinRec(arr, n, 0, sumTotal);
    }


    static int findMinDp(int arr[], int n)
    {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        boolean dp[][] = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    public static void main(String[] args) {

        int arr[] = { 3, 1, 4, 2, 2, 1 };
        System.out.println(findMinDp(arr, arr.length));
    }
}
