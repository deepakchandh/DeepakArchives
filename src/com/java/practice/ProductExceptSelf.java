package com.java.practice;
//https://leetcode.com/problems/product-of-array-except-self/solutions/65622/simple-java-solution-in-o-n-without-extra-space/
public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        // left = [1, 1, 2, 6]
        int right =1;
        for (int i = n - 1; i >= 0; i--) {
            left[i] *= right;
            right *= nums[i];
        }
        return left;

    }

    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1,2,3,4}));
        //60,40,30,24
    }
}
