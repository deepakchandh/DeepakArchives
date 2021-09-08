package com.java.graphs;

import java.util.*;
//https://leetcode.com/problems/cheapest-flights-within-k-stops/submissions/
public class CheapestFlights {


    //bfs
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k)
    {
        Map<Integer,Map<Integer,Integer>> map = new HashMap<>(); // {0={1=100, 2=500}, 1={2=100}}
        for(int[] f : flights){
            map.computeIfAbsent(f[0],m->new HashMap<>()).put(f[1],f[2]);
        }
        k++;
        int min = Integer.MAX_VALUE;
        int[] count = new int[n];
        count[src]++;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src,0});
        while(!queue.isEmpty()){
            int size = queue.size();
            if(k--==0) break;
            while(size-->0){
                int[] cur = queue.poll();
                Map<Integer,Integer> next = map.getOrDefault(cur[0],null);
                if(next==null) continue;
                for(Map.Entry<Integer,Integer> entry : next.entrySet()){
                    if(cur[1] + entry.getValue() >= min) continue;
                    if(entry.getKey()==dst){
                        min = Math.min(min,cur[1] + entry.getValue());
                        continue;
                    }
                    if(count[entry.getKey()]++<=n){
                        queue.add(new int[]{entry.getKey(),cur[1] + entry.getValue()});
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String args[])
    {

        int mat[][] = {{0,1,100},
                {1,2,100},
                {0,2,500}
        };
        System.out.println(findCheapestPrice(3, mat, 0, 2,1));;

    }

}
