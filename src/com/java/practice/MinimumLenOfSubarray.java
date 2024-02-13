package com.java.practice;
// https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/

// also see //https://leetcode.com/problems/minimum-size-subarray-sum/description/
public class MinimumLenOfSubarray {

    static int smallestSubWithSum(int arr[], int n, int x)
    {
        // Initialize current sum and minimum length
        int curr_sum = 0, min_len = n + 1;

        // Initialize starting and ending indexes
        int start = 0, end = 0;
        while (end < n) {
            // Keep adding array elements while current sum
            // is smaller than or equal to x
            while (curr_sum <= x && end < n)
                curr_sum += arr[end++];

            // If current sum becomes greater than x.
            while (curr_sum > x && start < n) {
                // Update minimum length if needed
                if (end - start < min_len)
                    min_len = end - start;

                // remove starting elements
                curr_sum -= arr[start++];
            }
        }
        return min_len;
    }

    public static void main(String[] args) {
        int arr1[] = { 1, 4, 45, 7, 10, 19 };
        int x = 51;
        int n1 = arr1.length;
        int res1 = smallestSubWithSum(arr1, n1, x);
        if (res1 == n1 + 1)
            System.out.println("Not Possible");
        else
            System.out.println(res1);
    }
}
