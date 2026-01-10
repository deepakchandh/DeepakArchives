package com.java.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LexicographicallySmallestArray {

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Pair values with original indices
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new int[]{nums[i], i});
        }

        // Sort by value
        Collections.sort(pairs, Comparator.comparingInt(a -> a[0]));

        int[] ans = new int[n];
        int i = 0;

        // Process groups
        while (i < n) {
            int j = i + 1;

            // Extend group while difference <= limit
            while (j < n && pairs.get(j)[0] - pairs.get(j - 1)[0] <= limit) {
                j++;
            }

            // Extract indices for this group
            List<Integer> idxList = new ArrayList<>();
            for (int k = i; k < j; k++) {
                idxList.add(pairs.get(k)[1]);
            }
            // Sort the original positions
            Collections.sort(idxList);

            // Put sorted values into sorted positions
            for (int k = i; k < j; k++) {
                ans[idxList.get(k - i)] = pairs.get(k)[0];
            }

            i = j; // move to next group
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lexicographicallySmallestArray(new int[]{1,5,3,9,8}, 2));
    }
}
