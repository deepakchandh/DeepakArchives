package com.java.practice;

import java.util.Arrays;
// https://leetcode.com/problems/minimize-maximum-pair-sum-in-array
public class MinPairSum {

    public static int minPairSum(int[] nums) {
        int start = 0, sum = 0, maxSum = 0;
        int end = nums.length - 1;

        Arrays.sort(nums);

        while (start < end) {
            sum = nums[start] + nums[end];

            start++;
            end--;

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(minPairSum(new int[]{3,5,2,3}));
    }
}
