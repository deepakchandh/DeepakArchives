//$Id$
package com.java.recursion;

public class SingleNonDup {
// Binary Search.
	
	public static void main(String[] args)
    {
        int[] arr={3,3,7,7,10,11,11};
        int ss  = singleNonDuplicate(arr);
        System.out.println(ss);
    }

	private static int singleNonDuplicate(int[] arr) {
		int low =0, high= arr.length -2;
		
		while(low <= high){
			int mid  = (low + high) / 2; // >> 1 (div by 2)
			if (arr[mid] == arr[mid ^ 1]) {
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		return arr[low];
		
	}
	
}
