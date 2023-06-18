package com.java.lc;
//https://leetcode.com/problems/jump-game/submissions/925142334/
public class JumpGame {

    public static Boolean jumpMan(int[] nums){
        int max =0;
        for(int i=0;i<nums.length;i++){
            if(i>max)
                return false;
            max = Math.max(nums[i]+i, max);
        }
        return true;

    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2,3,1,1,4};
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(jumpMan(nums));
    }
}
