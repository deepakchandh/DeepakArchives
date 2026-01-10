package com.java.practice;

import java.util.Arrays;

public class MaximumTastiness {

    public static int maximumTastiness(int[] price, int k) {
        // 1. Sort the array
        Arrays.sort(price);

        // 2. Define binary search bounds
        int left = 0;
        int right = price[price.length - 1] - price[0]; // max possible difference

        // 3. Binary search on tastiness
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (canPickWithGap(price, k, mid)) {
                left = mid;      // mid is feasible, try larger
            } else {
                right = mid - 1; // mid is too large
            }
        }
        return left;
    }

    // Greedy check: can we pick k candies with at least `gap` difference?
    private static boolean canPickWithGap(int[] price, int k, int gap) {
        int count = 1;               // pick the first candy
        int prev = price[0];

        for (int i = 1; i < price.length; i++) {
            if (price[i] - prev >= gap) {
                prev = price[i];
                count++;
                if (count == k) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(maximumTastiness(new int[]{1,2,5,8,13,21}, 3));
    }
}
