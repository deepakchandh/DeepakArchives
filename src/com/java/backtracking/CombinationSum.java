package com.java.backtracking;

import java.util.*;

public class CombinationSum {

    public List<List<Integer>> combinationSumResl(int[] candidates, int target) {
        Arrays.sort(candidates); // sort for pruning
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start,
                           List<Integer> path, List<List<Integer>> result) {

        if(remain == 0){
            result.add(new ArrayList<>(path));
            return;
        }


        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain)
                break; // prune early
            path.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSumResl(new int[]{2,5,6,9}, 9));
    }
}
