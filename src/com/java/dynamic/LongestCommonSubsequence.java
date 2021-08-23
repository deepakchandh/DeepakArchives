//$Id$
package com.java.dynamic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestCommonSubsequence {

	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	int lcs( String s1, String s2, int m, int n )
	{
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i < s1.length(); ++i){
			for (int j = 0; j < s2.length(); ++j){
				char ch1= s1.charAt(i), ch2= s2.charAt(j);
 				if (ch1 == ch2) 
					dp[i + 1][j + 1] = 1 + dp[i][j];
				else 
					dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
 				System.out.print(dp[i+1][j+1]+" ");
			}
			System.out.println();
		}
//		for (int i = 0; i < s1.length(); ++i){
//			for (int j = 0; j < s2.length(); ++j){
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
		return dp[s1.length()][s2.length()];
	}

	/* Utility function to get max of 2 integers */
	int max(int a, int b)
	{
		return (a > b)? a : b;
	}

	public static void main(String[] args)
	{
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";

		
		//	    char[] X=s1.toCharArray();
		//	    char[] Y=s2.toCharArray();
		//	    int m = X.length;
		//	    int n = Y.length;

		System.out.println("Length of LCS is" + " " +
				lcs.lcs( s1, s2, s1.length(), s2.length() ) );
	}
}
