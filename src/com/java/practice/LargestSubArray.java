//$Id$
package com.java.practice;

import java.util.HashMap;

public class LargestSubArray {

	public static void main(String arg[])
    {
        int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
        System.out.println("Length of the longest 0 sum subarray is "
                           + maxLen(arr));
    }

	private static int maxLen(int[] arr) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		int maxi=0, sum =0;
		for(int i=0;i<arr.length;i++){
			sum += arr[i];
			if (sum ==0) {
				maxi = i+1;
			}
			if (hashMap.containsKey(sum)) {
				maxi = Math.max(maxi, i- hashMap.get(sum));
			}else {
				hashMap.put(sum, i);
			}
			
		}
		return maxi;
		
	}
	
}
