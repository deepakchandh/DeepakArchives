//$Id$
package com.java.recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	
	
	
	public static void main(String[] args) {
		int[] nums = { 1,2,3};
		
		List<List<Integer>> ansList = new ArrayList<List<Integer>>();
		
		findSubSet(0, nums, new ArrayList<>(), ansList);
		for(List<Integer> ss: ansList){
			System.out.println("");
			for(Integer val: ss){
				System.out.print(val+"-");
			}
		}
		
	}

	private static void findSubSet(int index, int[] nums, List<Integer> ds, List<List<Integer>> ansList) {
		ansList.add(new ArrayList<>(ds));
		for(int i= index; i< nums.length;i++){
			if (i != index && nums[i] == nums[i-1]) {
				continue;
			}
			ds.add(nums[i]);
			findSubSet(i+1, nums, ds, ansList);
			ds.remove(ds.size()-1);
		}
		
	}

}
