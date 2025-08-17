package com.java.arrays;

import java.util.*;

public class NextPermutation {

    //brute force


    public static void nextPermutation(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int num : nums) current.add(num);

        // Step 1: sort to get lexicographically smallest first
        Arrays.sort(nums);
        List<Integer> smallest = new ArrayList<>();
        for (int num : nums) smallest.add(num);

        // Step 2: generate all permutations in sorted order
        generatePermutations(nums, 0, permutations);

        // Step 3: find the index of current permutation
        int index = -1;
        for (int i = 0; i < permutations.size(); i++) {
            if (permutations.get(i).equals(current)) {
                index = i;
                break;
            }
        }

        // Step 4: find the next permutation or wrap to first
        List<Integer> next;
        if (index == permutations.size() - 1) {
            next = permutations.get(0); // wrap around
        } else {
            next = permutations.get(index + 1);
        }

        // Step 5: copy next permutation into nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = next.get(i);
        }
    }

    private static void generatePermutations(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums)
                perm.add(num);
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            generatePermutations(nums, start + 1, res);
            swap(nums, start, i); // backtrack
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
