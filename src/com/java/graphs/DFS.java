//$Id$
package com.java.graphs;

import java.util.*;
import java.io.*;
public class DFS {
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
	
	/*static void DFS(int V, ArrayList<ArrayList<Integer>> adj)
	{
		Vector<Boolean> visited = new Vector<Boolean>(V);
		// Mark all the vertices as not visited
		for (int i = 0; i < V; i++)
			visited.add(false);

		for (int i = 0; i < V; i++)
			if (!visited.get(i))
				DFSUtil(i, visited,adj);
	}
	
	static void DFSUtil(int s, Vector<Boolean> visited, ArrayList<ArrayList<Integer>> adj)
	{
		// Create a stack for DFS
		Stack<Integer> stack = new Stack<>();
		
		stack.push(s);
		
		while(stack.empty() == false)
		{
			s = stack.pop();
			if(visited.get(s) == false)
			{
				System.out.print(s + " ");
				visited.set(s, true);
			}
			Iterator<Integer> itr = adj.get(s).iterator();
			while (itr.hasNext())
			{
				int v = itr.next();
				if(!visited.get(v))
					stack.push(v);
			}
		}
	}*/

	public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj)
	{
		boolean visited[] = new boolean[V];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		doDfs(adj, visited, 0, ans);
		return ans;
	}

	static void doDfs(ArrayList<ArrayList<Integer>> g, boolean[] visited, int currentNode, ArrayList<Integer> ans){
		if(visited[currentNode]){
			return;
		}
		ans.add(currentNode);
		visited[currentNode] = true;
		Iterator<Integer> iter = g.get(currentNode).iterator();
		while(iter.hasNext()){
			int i = iter.next();
			doDfs(g, visited,i, ans);
		}
	}
}


