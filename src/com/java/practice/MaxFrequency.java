package com.java.practice;

import java.util.Arrays;

public class MaxFrequency {


    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        long sum = 0;
        int left = 0;
        int maxFreq = 1;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Calculate cost of making window [left..right]
            while ((long) nums[right] * (right - left + 1) - sum > k) {
                sum -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }

    public static void main(String[] args) {

        System.out.println(maxFrequency(new int[]{1,2,4}, 5));
    }
}
