package com.java.arrays;

import java.util.*;

// https://algodaily.com/challenge_slides/subsets-summing-zero
public class SubsetsSummingZero {

    /*
    How it works
        * Maintain running sum.

        * If running sum repeats at index i and j,
        then the subarray between (i+1) and j sums to zero.

        * Store all previous indices for a given sum (to catch multiple zero-sum ranges).
     */

    public static List<int[]> findSumZeroSubarrays(int[] arr) {
        Map<Integer, List<Integer>> prefixMap = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        int sum = 0;
        // arr = [4, -5, 1, -3, 2, -8, 5, -2, 9] will help u understand it
        prefixMap.put(0, new ArrayList<>(Arrays.asList(-1))); // handles subarray from start

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (prefixMap.containsKey(sum)) {
                for (int startIndex : prefixMap.get(sum)) {
                    result.add(new int[]{startIndex + 1, i});
                }
            }

            if (!prefixMap.containsKey(sum)) {
                prefixMap.put(sum, new ArrayList<>());
            }
            prefixMap.get(sum).add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, -2, -4, 7, -1, 6, 8, -8, 4};
        List<int[]> res = findSumZeroSubarrays(arr);

        for (int[] range : res) {
            System.out.println(Arrays.toString(range));
        }
    }
}
