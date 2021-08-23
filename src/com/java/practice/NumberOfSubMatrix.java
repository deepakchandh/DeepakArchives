//$Id$
package com.java.practice;

import java.util.*;
class NumberOfSubMatrix
{

	static int n = 3;
	static class pair
	{
		int first, second;
		public pair(int first, int second)
		{
			this.first = first;
			this.second = second;
		}
	}

	static void findPrefixCount(int p_arr[][],
			boolean arr[][])
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = n - 1; j >= 0; j--)
			{
				if (!arr[i][j])
					continue;

				if (j != n - 1)
					p_arr[i][j] += p_arr[i][j + 1];

				p_arr[i][j] += arr[i][j] == true ? 1 : 0;
			}
		}
	}
	
	static int matrixAllOne(boolean arr[][])
	{
		int [][]p_arr = new int[n][n];

		findPrefixCount(p_arr, arr);

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				System.out.print(p_arr[i][j]+" ");
			System.out.println();
		}


		// variable to store the final answer
		int ans = 0;

		/* Loop to evaluate each column of
		the prefix matrix uniquely.
		For each index of a column we will try to
		determine the number of sub-matrices
		starting from that index
		and has all 1s */
		for (int j = 0; j < n; j++)
		{
			int i = n - 1;

			Stack<pair> q = new Stack<pair>();

			int to_sum = 0;
			while (i >= 0)
			{
				int c = 0;
				while (q.size() != 0 &&
						q.peek().first > p_arr[i][j])
				{
					to_sum -= (q.peek().second + 1) *
							(q.peek().first - p_arr[i][j]);

					c += q.peek().second + 1;
					q.pop();
				}

				to_sum += p_arr[i][j];

				ans += to_sum;

				q.add(new pair(p_arr[i][j], c));

				i--;
			}
		}
		return ans;
	}

	//Driver Code
	public static void main(String[] args)
	{
		boolean arr[][] = { { true, true, false },
				{ true, false, true },
				{ false, true, true } };

		System.out.println(matrixAllOne(arr));
	}
}

//This code is contributed by PrinciRaj1992

