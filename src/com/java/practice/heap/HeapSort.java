//$Id$
package com.java.practice.heap;

public class HeapSort {
	
	
	
	
	 public static void main(String args[])
	    {
	        int arr[] = { 12, 11, 13, 5, 6, 7 };
	        int n = arr.length;
	 
	        HeapSort ob = new HeapSort();
//	        ob.sort(arr);
	 
	        System.out.println("Sorted array is");
	        for (int i = 0; i < n; ++i)
	            System.out.print(arr[i] + " ");
	        System.out.println();	        
	        
	    }
}
