package com.java.backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/subsets-ii/description/
//#backTracking
public class Subsets2 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i=start;i<nums.length;i++){
            if(i > start && nums[i] == nums[i-1])  // skip duplicates
                continue;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3, 1}));
    }

}
/// not [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]