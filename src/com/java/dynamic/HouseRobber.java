package com.java.dynamic;

//https://leetcode.com/problems/house-robber/description/

//https://leetcode.com/problems/house-robber-ii/solutions/3634247/house-robber-1-and-house-robber-2/
// #dp
public class HouseRobber {

    public static  int rob(int[] nums) {

        int prev1 = 0, prev2 = 0;
        for(int num: nums){
            int tmp = prev1;
            prev1 = Math.max(prev2+num, prev1);
            prev2 = tmp;
        }
        return prev1;

//        return rob(nums, nums.length - 1);
    }


    public int robCircular(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1)); // circular and non-circular
    }

    public int rob(int[] nums, int lo, int hi) {
        int prev1 = 0, prev2 = 0;
        for (int j = lo; j <= hi; j++) {
            int tmp = prev1;
            prev1 = Math.max(prev2+nums[j], tmp);
            prev2 = tmp;
        }
        return prev1;

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
