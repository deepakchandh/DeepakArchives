package com.java.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GeneratePermutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int num : nums) {
            if (temp.contains(num))
                continue; // skip already used
            temp.add(num);
            backtrack(result, temp, nums);
            temp.remove(temp.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }


}
