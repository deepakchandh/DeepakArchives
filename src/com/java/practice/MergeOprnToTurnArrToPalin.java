package com.java.practice;
//https://www.geeksforgeeks.org/find-minimum-number-of-merge-operations-to-make-an-array-palindrome/

//Original question from leetcode
public class MergeOprnToTurnArrToPalin {

    public static int minOperation(int[] nums){
        int l = 0, r = nums.length-1;
        int opr = 0;
        int leftSum = nums[l], rightSum = nums[r];
        while(l < r) {
            if(leftSum == rightSum) {
                l++;
                r--;
                leftSum = nums[l];
                rightSum = nums[r];
            } else if(leftSum < rightSum) {
                opr++;
                l++;
                leftSum += nums[l];
            } else {
                opr++;
                r--;
                rightSum += nums[r];
            }
        }
        return opr;
    }

    public static void main(String[] args) {
        System.out.println(minOperation(new int[]{4,3,2,1,2,3,1}));
//        System.out.println(minOperation(new int[]{11, 14, 15, 99}));

    }
}
