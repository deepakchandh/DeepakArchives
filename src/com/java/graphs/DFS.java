//$Id$
package com.java.graphs;

import java.util.*;
import java.io.*;
public class DFS {

	// readymade method

	// https://takeuforward.org/data-structure/depth-first-search-dfs-traversal-graph/
	public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj)
	{
		ArrayList<Integer> storeDfs = new ArrayList<>();

		//boolean array to keep track of visited vertices
		boolean vis[] = new boolean[V+1];

		//If you are starting from node 2, then i should start from 2.
		for(int i = 1;i<=V;i++) {
			if(!vis[i])
				dfs(i, vis, adj, storeDfs);
		}

		return storeDfs;
	}

	public static void dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> storeDfs) {
		storeDfs.add(node);
		//marking current node as visited
		vis[node] = true;

		//getting neighbour nodes
		for(Integer it: adj.get(node)) {
			if(vis[it] == false) {
				dfs(it, vis, adj, storeDfs);
			}
		}
	}







	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		while(T-->0)
		{
			String[] s = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			ArrayList<ArrayList<Integer>>adj = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < V; i++)
				adj.add(new ArrayList<Integer>());
			for(int i = 0; i < E; i++){
				String[] S = br.readLine().trim().split(" ");
				int u = Integer.parseInt(S[0]);
				int v = Integer.parseInt(S[1]);
				adj.get(u).add(v);
				adj.get(v).add(u);
			}
//			DFS(V, adj);
			ArrayList<Integer>ans = dfsOfGraph(V, adj);
			for (int i =0 ;i < ans.size (); i++) 
				System.out.print (ans.get (i) + " ");
			System.out.println();
		}
	}

}


