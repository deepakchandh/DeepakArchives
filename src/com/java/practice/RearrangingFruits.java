package com.java.practice;

import java.util.*;
//https://leetcode.com/problems/rearranging-fruits/
//https://leetcode.com/problems/rearranging-fruits/solutions/3161421/antarnab-100-faster-easy/
//https://leetcode.com/problems/rearranging-fruits/solutions/3143992/java-sol/
public class RearrangingFruits {

    public static long minCost(int[] basket1, int[] basket2) {


        long sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int ele : basket1)
            map.put(ele,map.getOrDefault(ele,0)+1);
        for(int ele : basket2)
            map.put(ele,map.getOrDefault(ele,0)-1);

        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for(int key : map.keySet()){
            min = Math.min(min,key);
            int x =Math.abs(map.get(key));
            if(x == 0)   continue;
            if(x%2!=0) return -1;
            for(int i=0;i<x/2;i++)
                list.add(key);
        }

        Collections.sort(list);
        if(list.size()%2 != 0)  return -1;
        for(int i=list.size()/2 -1;i>=0;i--){
            if(list.get(i) > 2*min)
                sum+=2*min;
            else sum+=list.get(i);
        }
        return sum;



       /* TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int a : basket1)
            count.put(a, count.getOrDefault(a, 1)+1);
//            count.merge(a, 1, Integer::sum);
        for (int a : basket2)
            count.put(a, count.getOrDefault(a, 1)-1);
//            count.merge(a, -1, Integer::sum);





        List<Integer> swaps = new ArrayList<>();
        long res = 0, small = count.firstKey();
        for (int a : count.keySet()) {
            if (count.get(a) % 2 > 0) return -1;
            int v = Math.abs(count.get(a)) / 2;
            for (int i = 0; i < v; ++i)
                swaps.add(a);
        }
        for (int i = 0; i < swaps.size() / 2; ++i)
            res += Math.min(swaps.get(i), small * 2);
        return res;*/

        /*int min=basket1[0];
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int f:basket1){
            min=Math.min(min,f);
            hm.put(f,hm.getOrDefault(f,0)+1);
        }

        for(int f:basket2){
            min=Math.min(min,f);
            hm.put(f,hm.getOrDefault(f,0)-1);

        }

        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int key:hm.keySet()){
            if(hm.get(key)%2!=0){
                return -1;
            }

            for(int i=1;i<=Math.abs(hm.get(key))/2;i++){
                pq.add(key);
            }
        }


        int k=pq.size()/2;
        long ans=0;
        while(k-->0){
            if(pq.peek()<2*min){
                ans+=pq.remove();
            }else{
                ans+=2*min;
            }
        }

        return ans;*/

    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{2,3,4,1}, new int[]{3,2,5,1}));

    }
}
