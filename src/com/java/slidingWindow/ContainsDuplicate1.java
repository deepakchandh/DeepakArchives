package com.java.slidingWindow;

import java.util.HashMap;

//https://leetcode.com/problems/contains-duplicate-ii/solutions/1500887/Java-or-TC:-O(N)-or-SC:-O(min(N-K))-or-Sliding-Window-using-HashSet/
public class ContainsDuplicate1 {

    /*public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (nums.length <= 1 || k == 0) {
            return false;
        }

        HashSet<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            if (!window.add(nums[i])) {
                return true;
            }
        }

        return false;
    }*/

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3},2));


    }
}
