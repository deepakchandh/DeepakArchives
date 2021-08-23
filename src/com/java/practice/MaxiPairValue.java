//$Id$
//https://www.geeksforgeeks.org/maximum-value-pair-array/
package com.java.practice;

public class MaxiPairValue {

	static int checkBit(int pattern, int arr[], int n) // pattern = theValue, [], 4
	{
	    int count = 0;
	    for (int i = 0; i < n; i++)
	        if ((pattern & arr[i]) == pattern){
	        	System.out.println("pattern "+pattern);
	        	System.out.println(arr[i]);
	        	count++;
	        }
	            
	    return count;
	}
	  
	// Function for finding maximum and value pair
	static int maxAND (int arr[], int n)
	{
	    int res = 0, count;
	  
	    for (int bit = 31; bit >= 0; bit--)
	    {
	    	int temp = res | (1 << bit);
	        count = checkBit(temp, arr, n);
	        System.out.println("bit "+bit+" count:"+count);
	        
	        // if count >= 2 set particular
	        // bit in result
	        if ( count >= 2 ){
	        	res |= (1 << bit); 
	        	System.out.println("res: "+res);
	        }
	    }
	    return res;
	}
	   
	// driver function
	public static void main(String argc[])
	{
	    int arr[] = {4, 8, 12, 16};
	    int n = arr.length;
	    System.out.println("Maximum AND Value = " +
	                       maxAND(arr, n));
	}
}
