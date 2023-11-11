class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        /*
        nums = [1,4,6,8,10]
        Output: [24,15,13,15,21]
        */
        int prefix[] = new int[nums.length];
        int suffix[] = new int[nums.length];
        for(int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        for(int j = nums.length-2; j >= 0; j--) {
            suffix[j] = suffix[j+1] + nums[j+1];
        }

        for(int i=0;i<nums.length;i++){
            prefix[i] = (i*nums[i] - prefix[i])  +   (suffix[i] - (nums.length - 1 - i) * nums[i]);
        }
        
        return prefix;



        
    }
}