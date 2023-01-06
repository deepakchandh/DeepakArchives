//$Id$
package com.java.graphs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// https://takeuforward.org/data-structure/detect-a-cycle-in-directed-graph-using-dfs/
public class DetectCycleInDirected {

	public static void main(String[] args) throws IOException
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
		while(T-->0)
		{
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for(int i = 0; i < V+1; i++)
                list.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
			if (isCyclics(V, list)) {
				System.out.println("Cyclic");
			}else{
				System.out.println("Not Cyclic");
			}
		}
	}


	// // https://takeuforward.org/data-structure/detect-a-cycle-in-directed-graph-using-dfs/
	private static boolean checkCycle(int node,  ArrayList<ArrayList<Integer>> adj, int vis[], int dfsVis[]) {
        vis[node] = 1; 
        dfsVis[node] = 1; 
        
        for(Integer it: adj.get(node)) {
            if(vis[it] == 0) {
                if(checkCycle(it, adj, vis, dfsVis) == true) {
                    return true; 
                }
            } else if(dfsVis[it] == 1) {
                return true; 
            }
        }
        dfsVis[node] = 0;
        return false; 
    }
	
    public static boolean isCyclics(int N, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[N]; 
        int dfsVis[] = new int[N]; 
        
        for(int i = 0;i<N;i++) {
            if(vis[i] == 0) {
                if(checkCycle(i, adj, vis, dfsVis) == true) return true; 
            }
        }
        return false; 
    }










	/*private static boolean isCyclic(int V, ArrayList<ArrayList<Integer>>adj)
	{
		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack, adj))
				return true;

		return false;
	}

	private static boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, ArrayList<ArrayList<Integer>>adj)
	{
		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i])
			return true;
		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = adj.get(i);

		for (Integer c: children)
			if (isCyclicUtil(c, visited, recStack, adj))
				return true;

		recStack[i] = false;

		return false;
	}*/
	
}
