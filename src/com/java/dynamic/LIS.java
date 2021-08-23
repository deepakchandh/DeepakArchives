//$Id$
package com.java.dynamic;

public class LIS {

	 static int lis(int arr[], int n)
	    {
	        int lis[] = new int[n];
	        int i, j, max = 0;
	 
	        for (i = 0; i < n; i++)
	            lis[i] = 1;
	 
	        for (i = 1; i < n; i++){
	        	for (j = 0; j < i; j++){
	            	 if (arr[i] > arr[j] && lis[i] < lis[j] + 1){
	                	 lis[i] = lis[j] + 1;
	                }
	            }
	        }
	        for(int ind=0;ind<n;ind++)
	        	System.out.print(lis[ind]+" ");
	        
	        /* Pick maximum of all LIS values */
	        for (i = 0; i < n; i++)
	            if (max < lis[i])
	                max = lis[i];
	 
	        return max;
	    }
	 
	    public static void main(String args[])
	    {
//	        int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
	    	int arr[] = {30, 40, 2, 5, 1,
					7, 45, 50, 8};
	        int n = arr.length;
	        System.out.println("Length of lis is " + lis(arr, n)
	                           + "\n");
	    }
	    
}
