//$Id$
//https://www.geeksforgeeks.org/maximum-xor-of-two-numbers-in-an-array/
package com.java.practice;

import java.util.HashSet;

public class MaxXorOfTwoNum {

	static int max_xor(int arr[], int n)
	{
	    int maxx = 0, mask = 0;
	    HashSet<Integer> set = new HashSet<Integer>();
	    for (int bit = 30; bit >= 0; bit--)
	    {
	        mask |= (1 << bit);
	        System.out.println("mask "+mask);
	        System.out.println("i valu"+bit);
	        for (int j = 0; j < n; ++j)
	        {
	            set.add(arr[j] & mask );
	        }
	        System.out.println(set);
            System.out.println();
	 
	        int newMaxx = maxx | (1 << bit);
	        if (set.size() > 1) {
				System.out.println(" newMaxx "+newMaxx);
			}
	 
	        for (int prefix : set)
	        {
	 
	            // find two pair in set
	            // such that a^b = newMaxx
	            // which is the highest
	            // possible bit can be obtained
	            if (set.contains(newMaxx ^ prefix))
	            {
	                maxx = newMaxx;
	                break;
	            }
	        }
	        set.clear();
	    }
	    return maxx;
	}
	 
	// Driver Code
	public static void main(String[] args)
	{
	    int arr[] = { 25, 8, 5, 3 };
	    int n = arr.length;
	 
	    System.out.println(max_xor(arr, n));
	}
}
