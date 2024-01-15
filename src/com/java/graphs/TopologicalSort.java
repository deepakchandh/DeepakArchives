package com.java.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
//https://www.geeksforgeeks.org/topological-sorting/
// logic here is --> Simply do DFS and store all the result values in Stack. and then print elements of stack

public class TopologicalSort {

    static void findTopoSort(int node, int vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;
        for(Integer it: adj.get(node)) {
            if(vis[it] == 0) {
                findTopoSort(it, vis, adj, st);
            }
        }
        st.push(node);
    }
    static int[] topoSort(int N, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<Integer>();
        int vis[] = new int[N];

        for(int i = 0;i<N;i++) {
            if(vis[i] == 0) {
                findTopoSort(i, vis, adj, st);
            }
        }

        int topo[] = new int[N];
        int ind = 0;
        while(!st.isEmpty()) {
            topo[ind++] = st.pop();
        }
        // for(int i = 0;i<N;i++) System.out.println(topo[i] + " ");
        return topo;
    }
    public static void main(String args[]) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = 6;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            adj.add(arr);
        }

        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        int res[] = topoSort(6, adj);

        System.out.println("Toposort of the given graph is:");
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
    /*private int V;

    // Adjacency List as ArrayList of ArrayList's
    private ArrayList<ArrayList<Integer>> adj;

    // readily avbl method
    static int[] topoSort(int N, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<Integer>();
        int vis[] = new int[N];

        for(int i = 0;i<N;i++) {
            if(vis[i] == 0) {
                findTopoSort(i, vis, adj, st);
            }
        }

        int topo[] = new int[N];
        int ind = 0;
        while(!st.isEmpty()) {
            topo[ind++] = st.pop();
        }
        return topo;
    }

    static void findTopoSort(int node, int vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;
        for(Integer it: adj.get(node)) {
            if(vis[it] == 0) {
                findTopoSort(it, vis, adj, st);
            }
        }
        st.push(node);
    }









    // Constructor
    TopologicalSort(int v)
    {
        V = v;
        adj = new ArrayList<ArrayList<Integer> >(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w){
        adj.get(v).add(w);
    }

    List<Integer> topologicalSort()
    {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        while (stack.empty() == false)
            res.add(stack.pop());

        return res;
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent
        // to thisvertex
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack
        // which stores result
        stack.push(new Integer(v));
    }

    // Driver code
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological "
                + "sort of the given graph");
        // Function Call
        g.topologicalSort();
    }*/
}
