package com.java.practice;
//https://leetcode.com/problems/subarray-product-less-than-k/
public class NumSubarrayProductLessThanK {


    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0)
            return 0;
        int cnt = 0;
        int pro = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            pro *= nums[j];
            while (i <= j && pro >= k) {
                pro /= nums[i];
                i++;
            }
            cnt += j - i + 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{1,5,2,6}, 100));
    }
}


