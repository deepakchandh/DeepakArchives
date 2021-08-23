//$Id$
package com.java.practice.heap;
import java.util.*;

class CountSubarraysInXOR {

	static long subarrayXor(int arr[], int n, int m)
	{
		long ans = 0; 
		HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
		int xorr = 0;
		for(int i=0;i<n;i++){
			xorr = xorr ^ arr[i];
			
			if (map.containsKey(xorr ^ m)) {
				ans += map.get(xorr ^ m);
			}
			if (xorr == m) {
				ans++;
			}
			if (map.containsKey(xorr)) {
				map.put(xorr, map.get(xorr) + 1);
			}else {
				map.put(xorr, 1);
			}
		}
		
		return ans;
	}

	// Driver code
	public static void main(String[] args)
	{
		
		int arr[] = { 4, 2, 2, 6, 4 };
		int n = arr.length;
		int m = 6;

		System.out.print(
			"Number of subarrays having given XOR is "
			+ subarrayXor(arr, n, m));
	}
}
/*
 * 
 * long ans = 0; 
		HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
		int xorr = 0;

		for (int i = 0; i < n; i++) {
			xorr = xorr ^ arr[i];
			
			if (map.containsKey(xorr ^ m)) {
				ans += map.get(xorr ^ m);
			}
			if (xorr == m) {
				ans++;
			}
			
			if (map.containsKey(xorr)) {
				map.put(xorr, map.get(xorr) + 1);
			}else{
				map.put(xorr, 1);
			}
		}
		return ans;
 */
