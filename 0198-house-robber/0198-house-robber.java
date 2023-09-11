class Solution {
    public int rob(int[] nums) {
        
        // return rob(nums, nums.length - 1);

        int prev1 = 0, prev2 = 0;
        for(int num: nums){
            int tmp = prev1;
            prev1 = Math.max(prev2+num, tmp);
            prev2 = tmp;
        }
        //[2,7,9,3,1]
// 2,0.. 7,2.. 11,7.. 11,11.. 12,11// ->12 
        return prev1;

    }

    // // recursion
    // private int rob(int[] nums, int i){
    //     if(i < 0)
    //         return 0;
    //     return Math.max(rob(nums, i-2) + nums[i], rob(nums, i-1));
    // }

}