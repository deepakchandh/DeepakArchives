package com.java.dynamic;

public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int ele : nums ){
            sum+=ele;
        }
        if(sum%2!=0) return false;
        sum = sum/2;


        boolean dp[] =  new boolean[sum+1];
        dp[0] =true;

        for(int num : nums){
            for(int i=sum;i>0;i--){
                if(i>=num){
                    dp[i] =  dp[i] || dp[i-num];
                }
            }
        }
        return dp[sum];

    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,5,5,11}));
    }
}
