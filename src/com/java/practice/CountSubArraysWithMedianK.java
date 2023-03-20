package com.java.practice;

import java.util.*;

//https://leetcode.com/problems/count-subarrays-with-median-k/description/
//https://leetcode.com/problems/count-subarrays-with-median-k/solutions/2851944/java-python-3-c-1-pass-o-n-codes-count-the-prefix-sum-of-the-balance-of-greater-samller/?orderBy=most_votes
public class CountSubArraysWithMedianK {

    public static int countSubarrays(int[] nums, int k) {
        Map<Integer, Integer> prefixSumOfBalance = new HashMap<>();
        prefixSumOfBalance.put(0, 1);
        int ans = 0, runningBal = 0;
        boolean found = false;
        for (int num : nums) {
            if (num < k)
                --runningBal;
            else if (num > k)
                ++runningBal;
            else
                found = true;
            if (found)
                ans += prefixSumOfBalance.getOrDefault(runningBal, 0) + prefixSumOfBalance.getOrDefault(runningBal - 1, 0);
            else
                prefixSumOfBalance.put(runningBal, prefixSumOfBalance.getOrDefault(runningBal, 0) + 1);
        }
        return ans;

    }

    public static void main(String[] args) {
//        System.out.println(countSubarrays(new int[]{3,2,1,4,5}, 4));
        System.out.println(countSubarrays(new int[]{7, 1, 3, 4, 2, 5, 6}, 4)); // 4 (7,1) (7, 1, 3, 4, 2, 5, 6) (3, 4, 2, 5, 6) (1, 3, 4)

    }
}
