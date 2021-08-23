//$Id$
package com.java.practice;

public class SubsetSum {

	
	 /*static int cutRod(int price[],int n)
	    {
	        int val[] = new int[n+1];
	        val[0] = 0;
	 
	        for (int i = 1; i<=n; i++)
	        {
	            int max_val = Integer.MIN_VALUE;
	            for (int j = 0; j < i; j++){
	            	int test = price[j] + val[i-j-1];
	            	max_val = Math.max(max_val,price[j] + val[i-j-1]);
	            }
	            val[i] = max_val;
	        }
	 
	        return val[n];
	    }*/
	
	
	static int cutRod(int price[], int n){
		int val[] = new int[n+1];
		val[0] =0;
		
		for(int i=1;i<=n;i++){
			int maxVal = Integer.MIN_VALUE;
			for(int j=0;j<i;j++){
				maxVal = Math.max(maxVal, price[j] + val[i-j-1]);
			}
			val[i] = maxVal;
		}
		
		return val[n];
	}
	
	

	/* Driver code*/
	public static void main(String args[])
	{
		 int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
	        int size = arr.length;
//		int n = set.length;
		int ss = cutRod(arr, size);
		System.out.println(ss);
	}

}
