//$Id$
package com.java.Greedy;

import java.util.Arrays;

class MinimumPlatform{

	public static int findPlatform(int arr[], int dep[],
			int n)
	{
		Arrays.sort(arr);
		Arrays.sort(dep);
		
        int platformNeeded=1, result=1;
        
        int j=0, i=1;
        while(i<n && j<n){
            if(arr[i] <= dep[j]){
                platformNeeded++;
                i++;
            }else if(arr[i] > dep[j] ){
                 platformNeeded--;
                 j++;
            }
            result = Math.max(result, platformNeeded);
        }
        return result;
	}

	//Driver Code
	public static void main(String[] args)
	{
		int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
		int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };
		int n = 6;

		System.out.println("Minimum Number of " +
				"Platforms Required = " +
				findPlatform(arr, dep, n));
	}
}

