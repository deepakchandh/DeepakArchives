package com.java.lc;

import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int ans=1;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]==ans)
                ans++;
        }
        return ans;
    }

    public static int firstMissingPositive(Vector<Integer> nums) {
        int n = nums.size();
        for (int i = 0; i < n; i++)
            if (nums.get(i) <= 0)
                nums.set(i, n+1);
        for (int i = 0; i < n; i++)
            if (Math.abs(nums.get(i)) <= n && nums.get(Math.abs(nums.get(i)) - 1) > 0)
                nums.set(Math.abs(nums.get(i)) - 1, nums.get(Math.abs(nums.get(i)) - 1) * -1);


        for (int i = 0; i < n; i++)
            if (nums.get(i) > 0)
                return i + 1;
        return n + 1;
    }
    public static int firstMissingPositiveUsingBinarySearch(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int index = nums[start] - 1;
            if (index == start)
                start++;
            else if (index < 0 || index > end || nums[start] == nums[index])
                nums[start] = nums[end--];
            else {
                nums[start] = nums[index];
                nums[index] = index + 1;
            }
        }
        return start + 1;
    }


    public static void main(String[] args) {

        int[] num = new int[]{3,4,-1,1};

//        System.out.println(firstMissingPositiveUsingBinarySearch(num));
//        System.out.println(firstMissingPositive(new Vector<Integer>(){3,4,-1,1}));


    }
}
