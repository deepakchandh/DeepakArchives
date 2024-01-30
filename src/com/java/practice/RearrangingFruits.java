package com.java.practice;

import java.util.*;
//https://leetcode.com/problems/rearranging-fruits/
//https://leetcode.com/problems/rearranging-fruits/solutions/3161421/antarnab-100-faster-easy/
//https://leetcode.com/problems/rearranging-fruits/solutions/3143992/java-sol/
public class RearrangingFruits {

    /*
    Approach

    Create a HashMap map to store the frequency of each element in both baskets
    Traverse both baskets and update the frequency of each element in map
    Create a list list to store elements that have a non-zero frequency in map
    Find the minimum element in map and store it in min
        For each element key in map:
        If key is not in map, skip
        If frequency of key is not divisible by 2, return -1
        If frequency of key is divisible by 2, add key to the list list for x/2 times
    Sort the list in ascending order
    If the size of the list is not divisible by 2, return -1
        Traverse the list from the middle to the start
        If list[i] is greater than 2 * min, add 2 * min to sum
        Else, add list[i] to sum
    Return sum
     */

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
            int x = Math.abs(map.get(key));
            if(x == 0)
                continue;
            if(x%2!=0)
                return -1;
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

    }

    public static void main(String[] args) {
//        System.out.println(minCost(new int[]{2,3,4,1}, new int[]{3,2,5,1}));
        System.out.println(minCost(new int[]{4,2,2,2}, new int[]{1,4,1,2}));

    }
}
