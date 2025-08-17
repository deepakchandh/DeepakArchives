package com.java.dynamic;

//https://leetcode.com/problems/house-robber/description/

//https://leetcode.com/problems/house-robber-ii/solutions/3634247/house-robber-1-and-house-robber-2/
// #dp
public class HouseRobber {

    public static  int rob(int[] nums) {

        int dp[] = new int[nums.length+1];

        if (nums.length == 1) return nums[0];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }

        return dp[nums.length-1];

//        return rob(nums, nums.length - 1);
    }


    public int robCircular(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int max1 = robLinearDP(nums, 0, n - 2); // exclude last house
        int max2 = robLinearDP(nums, 1, n - 1); // exclude first house
        return Math.max(max1, max2);
    }

    private int robLinearDP(int[] nums, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[len];
        dp[0] = nums[start];
        if (len == 1) return dp[0];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        }

        return dp[len - 1];
    }

    private static int rob(int[] nums, int i){
        if(i < 0)
            return 0;
        return Math.max(rob(nums, i-2) + nums[i], rob(nums, i-1));
    }



    public static void main(String[] args) {
        System.out.println(rob(new int[]{114,117,207,117,235,82,90}));

    }
}
