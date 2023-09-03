package com.java.practice;

import java.util.*;

//https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/solutions/990288/java-non-prefix-sum-solution-explanation/
public class MinAdjSwapForKConsOnes {

    public static int minMoves(int[] nums, int k) {
        List<Integer> pos = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                pos.add(i);
            }
        }
        for (int i = 0; i < k; i++) {
            sum += Math.abs(pos.get(k / 2) - pos.get(i));
        }
        int res = sum;
        for (int i = 0; i + k < pos.size(); i++) {
            int mid = i + k / 2;
            sum -= pos.get(mid) - pos.get(i);
            sum += pos.get(i + k) - pos.get(mid + 1);
            sum += (k / 2) * (pos.get(mid + 1) - pos.get(mid));
            sum -= (k - k / 2 - 1) * (pos.get(mid + 1) - pos.get(mid));
            res = Math.min(res, sum);
        }
        int extra = k % 2 == 1 ? (k / 2 + 1) * (k / 2) : (k / 2 + 1) * (k / 2) / 2 + (k / 2) * (k / 2 - 1) / 2;
        return res - extra;
    }

    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1,0,0,1,0,1}, 2));

    }
}
