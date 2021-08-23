//$Id$
package com.java.practice;
// extn of kadane
public class MaxProductSubArray {

	public static int maxProduct(int[] A) {
		if (A == null || A.length == 0) {
            return 0;
        }
		int max=A[0], min = A[0], res = A[0];
		
		for(int i=1;i<A.length;i++){
			int temp = max;
			max = Math.max(Math.max(max*A[i], min*A[i]), A[i]);
			min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
			if (max > res) {
				res = max;
			}
		}
		 return res;
	}
	
	public static void main(String args[]){
		int[] arr = {-1, -3, -10, 0, 60};
		int ss = maxProduct(arr);
		System.out.println(ss);
	}
}
