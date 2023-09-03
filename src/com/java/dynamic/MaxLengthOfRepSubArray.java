package com.java.dynamic;

//https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/

public class MaxLengthOfRepSubArray {

    public static int findLength(int[] nums1, int[] nums2) {
        int n= nums1.length;
        int m= nums2.length;
        int dp[][]=new int[n][m];
        int ans=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(nums1[i]==nums2[j]) {
                    if(i==0 || j==0)
                        dp[i][j]=1;
                    else
                        dp[i][j]=1+dp[i-1][j-1];
                    ans=Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {

        System.out.println(findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));

    }
}
