package com.java.slidingWindow;

import java.util.*;

public class SubarraysWithKDistinct {

    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private static int atMostK(int[] nums, int k) {
        int n = nums.length;
        int left = 0, res = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int right = 0; right < n; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            if (freq.get(nums[right]) == 1) { // new distinct
                k--;
            }

            while (k < 0) { // too many distinct
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                    k++;
                }
                left++;
            }

            // number of subarrays ending at right
            res += right - left + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        int result = subarraysWithKDistinct(nums, k);
        System.out.println("Number of subarrays with exactly " + k + " distinct integers = " + result);
    }
}
