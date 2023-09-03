package com.java.practice;

import java.util.*;

public class RearrangingFruits {

    public static long minCost(int[] basket1, int[] basket2) {

        int min=basket1[0];
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

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{4,2,2,2}, new int[]{1,4,1,2}));

    }
}
