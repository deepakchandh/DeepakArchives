package com.java.practice;
//https://leetcode.com/problems/ugly-number-ii
//https://leetcode.com/problems/ugly-number-ii/solutions/69362/o-n-java-solution/
public class UglyNumbers {

    public static int nthUglyNumber(int n) {

        int[] nums = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        nums[0] = 1;
        for(int i = 1; i < nums.length; i++){
            nums[i] = Math.min(nums[index2] * 2, Math.min(nums[index3] * 3, nums[index5] * 5));
            if(nums[i] == nums[index2] * 2)
                index2++;
            if(nums[i] == nums[index3] * 3)
                index3++;
            if(nums[i] == nums[index5] * 5)
                index5++;
        }
        return nums[n - 1];

    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));

    }
}
