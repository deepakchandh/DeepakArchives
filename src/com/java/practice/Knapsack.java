//$Id$
package com.java.practice;

public class Knapsack {
	
	private static int unboundedKnapsack(int W, int n,
			int[] val, int[] wt)
	{

		// dp[i] is going to store maximum value
		// with knapsack capacity i.
		int dp[] = new int[W + 1];

		// Fill dp[] using above recursive formula
		for(int i = 0; i <= W; i++){
			for(int j = 0; j < n; j++){
				if(wt[j] <= i){
					int v1 = dp[i];
					int v2 =  dp[i - wt[j]];// weight part
					int v3 = val[j];
					
					dp[i] = Math.max(dp[i], dp[i - wt[j]] + val[j]);
				}
			}
		}
		return dp[W];
	}
	
	static int knapSack(int W, int wt[],
			int val[], int n)
	{
		int i, j;
		int K[][] = new int[n + 1][W + 1];

		// Build table K[][] in bottom up manner
		for (i = 1; i <= n; i++)
		{
			for (j = 1; j <= W; j++)
			{
				//if (i == 0 || w == 0)
				//	K[i][w] = 0;
				if (wt[i - 1] <= j){
					int v1 =val[i - 1]+ K[i - 1][j - wt[i - 1]];
					int v2 = K[i - 1][j];
					K[i][j]= Math.max(val[i - 1]+ K[i - 1][j - wt[i - 1]],K[i - 1][j]);
				}
				else
					K[i][j] = K[i - 1][j];
			}
		}

		return K[n][W];
	}

	// Driver code
	public static void main(String args[])
	{
//		int val[] = new int[] { 60, 100, 120 };
//		int wt[] = new int[] { 1, 2, 3 };
//		int W = 5;
		int W = 9;
		int val[] = {10, 30, 20};
		int wt[] = {1, 2, 3};
		int n = val.length;
		System.out.println(knapSack(W, wt, val, n));
		System.out.println(unboundedKnapsack(W, n, val, wt));
	}
}
