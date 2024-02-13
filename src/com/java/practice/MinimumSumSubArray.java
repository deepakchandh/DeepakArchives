package com.java.practice;
//https://leetcode.com/problems/minimum-size-subarray-sum/description/

//  Also see this, https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/ ==> MinimumLenOfSubarray
public class MinimumSumSubArray {

    public static int minSubArrayLen(int target, int[] nums) {

        int i=0;
        int j=0;
        int min=Integer.MAX_VALUE;
        int sum=0;
        while(j<nums.length){
            sum+=nums[j];
            if(sum>=target){
                while(sum>=target){
                    min=Math.min(min,j-i+1);
                    sum-=nums[i];
                    i++;
                }
            }
            j++;
        }
        if(min==Integer.MAX_VALUE)
            return 0;
        else
            return min;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));

    }
}
