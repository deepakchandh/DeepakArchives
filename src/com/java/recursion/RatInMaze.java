//$Id$
package com.java.recursion;

import java.util.*;

public class RatInMaze {

	private static void solve(int i, int j, int[][] arr, int n, ArrayList<String> ans, String string, int[][] vis,
			int[] di, int[] dj) {
		
		if (i==n-1 && j==n-1) {
			ans.add(string);
			return;
		}
		
		String dir = "DLRU"; 
		for(int ind=0;ind<4;ind++){
			int nexti = i + di[ind];
			int nextj = j + dj[ind];
			if (nexti >= 0  && nextj >= 0 && nexti < n && nextj < n && vis[nexti][nextj]==0 && arr[nexti][nextj]==1) {
				vis[i][j] = 1;
				solve(nexti, nextj, arr, n, ans, string+ dir.charAt(ind), vis, di, dj);
				vis[i][j] = 0;
			}
		}

	}

	public static ArrayList<String> findPath(int[][] m, int n) {
		int vis[][] = new int[n][n];
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				vis[i][j] = 0; 
			}
		}
		int[] di={1,0,0,-1};
		int[] dj={0,-1,1,0};
		
		ArrayList<String> ans = new ArrayList<>(); 
		if(m[0][0] == 1) 
			solve(0, 0, m, n, ans, "", vis, di, dj); 
		return ans; 
	}



	public static void main(String[] args)
	{
		int m[][] = { { 1, 0, 0, 0 },
					  { 1, 1, 0, 1 },
					  { 1, 1, 0, 0 },
					  { 0, 1, 1, 1 }
				 };
		ArrayList<String> anStrings = findPath(m, 4);
		anStrings.forEach(aa -> System.out.println(aa));




	}

}
