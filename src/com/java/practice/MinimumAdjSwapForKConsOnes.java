package com.java.practice;

import java.util.*;

//https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/description/
public class MinimumAdjSwapForKConsOnes {

    public static int minMoves(int[] nums, int k) {

        if(k == 1)
            return 0;
        List<Integer> onesIndexList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++)
            if(nums[i] == 1)
                onesIndexList.add(i);
        int totalOnes = onesIndexList.size();
        List<Integer> preSumList = new ArrayList<>(totalOnes);
        preSumList.add(onesIndexList.get(0));

        for(int i = 1; i < totalOnes; i++)
            preSumList.add(onesIndexList.get(i) + preSumList.get(i-1));

        int ans = Integer.MAX_VALUE;

        for(int mid = (k-1)/2 ; mid < totalOnes - k/2; mid++) {
            int radius = (k-1)/2;
            int right = k%2 == 0 ? preSumList.get(mid+radius+1) - preSumList.get(mid) - onesIndexList.get(mid) :       // even case
                    preSumList.get(mid+radius) - preSumList.get(mid);                                  // odd case
            int left =  (mid == 0 ? 0 : preSumList.get(mid-1)) - (mid-radius == 0 ? 0 : preSumList.get(mid-radius-1));
            int save = (radius+1)*radius + (k%2 == 0 ? radius+1 : 0);
            ans = Math.min(ans, right - left - save);
        }
        return ans;

    }

    public static void main(String[] args) {

        System.out.println(minMoves(new int[]{1,0,0,1,0,1},2));
    }
}
