//$Id$
package com.java.practice;

public class BrainKernighan {

	static int  brian(int n){
		int count = 0;
		while(n > 0){
			n = n & (n-1);
			count ++;
		}
		return count;
	}
	
	public static void main(String args[]){
		Long ss = (long) (1 <<6);
		System.out.println(ss);
	}
}
