package com.java.backtracking;
//https://leetcode.com/problems/subsets/description/

import java.util.*;

//#backTracking
public class Subsets {

   static List<List<Integer>> sumList = new ArrayList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackWithSum(list, new ArrayList<>(), nums, 0, 3);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i=start;i<nums.length;i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    private static void backtrackWithSum(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start, int sum){
        if(sum == 0){
            sumList.add(tempList);
        }
        list.add(new ArrayList<>(tempList));
        for(int i=start;i<nums.length;i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3, 1}));
        System.out.println(sumList);
    }
}
