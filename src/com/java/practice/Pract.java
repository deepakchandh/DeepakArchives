//$Id$
package com.java.practice;

import java.util.Stack;

public class Pract {
	
	static void printNGE(int arr[], int n)
	{
	    Stack<Integer> s = new Stack<Integer>();
	 
	    int arr1[] = new int[n];
	 
	    for (int i = n - 1; i >= 0; i--)
	    {
	        while (!s.isEmpty() && s.peek() <= arr[i])
	            s.pop();
	 
	        if (s.empty())
	            arr1[i] = -1;        
	        else
	            arr1[i] = s.peek();        
	 
	        s.push(arr[i]);
	    }
	 
	    for (int i = 0; i < n; i++)
	        System.out.print(arr1[i] + "  ");
	}
	
	public static void main(String args[]){
		int arr[] = { 1,3,2,4 };
	    int n = arr.length;
	    printNGE(arr, n);
//		System.out.println(ss);
	}

}
