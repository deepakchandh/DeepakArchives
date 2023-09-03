package com.java.graphs;

import java.util.*;

public class LongestCycleInGraph {

    static int answer = -1;
    public static int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] visit = new boolean[n];
        for(int i=0;i<n;i++){
            if(!visit[i]){
                Map<Integer, Integer> cycleLength = new HashMap<>();
                cycleLength.put(i, 1);
                dfs(i, edges, cycleLength, visit);
            }
        }
        return answer;
    }

    public static void dfs(int node, int[] edges, Map<Integer, Integer> dist, boolean[] visit) {
        visit[node] = true;
        int neighbour = edges[node];

        if(neighbour != -1 && !visit[neighbour]){
            dist.put(neighbour, dist.get(node) + 1);
            dfs(neighbour, edges, dist, visit);
        }
        else if (neighbour != -1 && dist.containsKey(neighbour)) {
            answer = Math.max(answer, dist.get(node) - dist.get(neighbour) + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(longestCycle(new int[]{3,3,4,2,3}));
    }
}
