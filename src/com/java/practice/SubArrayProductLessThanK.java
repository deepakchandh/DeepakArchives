package com.java.practice;

public class SubArrayProductLessThanK {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;  // If k is ≤1, product of positive ints cannot be < k

        long prod = 1;
        int left = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];

            // shrink window while product is too large
            while (prod >= k && left <= right) {
                prod /= nums[left];
                left++;
            }

            // now all subarrays ending at right with start in [left..right] are valid
            result += (right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
    }
}
