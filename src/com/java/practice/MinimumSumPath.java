//$Id$
package com.java.practice;
//https://leetcode.com/problems/minimum-path-sum/
public class MinimumSumPath {


	public static int minPathSum(int[][] grid) {

		int m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(i == 0 && j != 0) 
					grid[i][j] += grid[i][j-1];
				if(i != 0 && j == 0) 
					grid[i][j] += grid[i-1][j];
				if (i != 0 && j != 0) 
					grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
			}
		}
		return grid[m-1][n-1];

	}

	public static void main(String[] args)
	{
		int arr[][] = { {1,3,1}, {1,5,1}, {4,2,1} };

		System.out.println("Minimum number of jumps to reach end is : "
				+ minPathSum(arr));
	}

}
