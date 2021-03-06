//$Id$
package com.java.graphs;
//https://github.com/striver79/StriversGraphSeries/blob/main/cycleCheckUGBfsJava
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BFS
public class DetectCycleInUnDirected {

	/*
	sample input
	1
	5 5
0 4
1 2
1 4
2 3
3 4
	 */

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		while(T-->0)
		{
			String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            if(isCycle(V, adj))
                System.out.println("Its a cycle");
            else
                System.out.println("Not a cycle");
		}
	}

	public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj)
	{
		boolean visited[] = new boolean[V];
		Arrays.fill(visited,false);
		for (int i = 0; i < V; i++)
			if (!visited[i] && isCyclicConntected(adj, i, V, visited))
				return true;
		return false;


	}

	static boolean isCyclicConntected( ArrayList<ArrayList<Integer>> adj, int s, int V, boolean visited[]){
		int parent[] = new int[V];
		Arrays.fill(parent, -1);

		Queue<Integer> q = new LinkedList<>();

		visited[s] = true;
		q.add(s);
//We use a parent array to keep track of the parent vertex for a vertex so that we do not consider the visited parent as a cycle. wo
		while (!q.isEmpty())
		{
			int u = q.poll();
			for (int i = 0; i < adj.get(u).size(); i++)
			{
				int v = adj.get(u).get(i);
				if (!visited[v])
				{
					visited[v] = true;
					q.add(v);
					parent[v] = u;
				}
				else if (parent[u] != v)
					return true;
			}
		}
		return false;						    
	}

}
