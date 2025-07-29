package com.java.practice;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/sum-of-subarray-minimums/description/

public class SumSubarrayMins {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = 1_000_000_007;

        int[] left = new int[n];   // count of elements greater on the left
        int[] right = new int[n];  // count of elements greater or equal on the right

        // Step 1: Find left[] using monotonic increasing stack
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        // Step 2: Find right[] using monotonic increasing stack (from right to left)
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        // Step 3: Each element contributes arr[i] * left[i] * right[i]
        long total = 0;
        for (int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * left[i] * right[i];
            total = (total + contribution) % MOD;
        }

        return (int) total;
    }
    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{11,81,94,43,3}));

    }

    /*
    📌 Dry Run Example: arr = [3,1,2,4]
Index	arr[i]	left[i]	right[i]	Contribution
0	    3	    1	    1	    3 × 1 × 1 = 3
1	    1	    2	    3	    1 × 2 × 3 = 6
2	    2	    1	    2	    2 × 1 × 2 = 4
3	    4	    1	    1	    4 × 1 × 1 = 4

Total = 3 + 6 + 4 + 4 = 17 ✅
     */
}
