package com.java.practice;

import java.util.*;


//https://leetcode.com/problems/top-k-frequent-elements/description/
public class TopKFrequentEle {

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums){
            map.put(num, map.containsKey(num)? map.get(num) + 1 : 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pque =
                new PriorityQueue<Map.Entry<Integer, Integer>>((o1, o2) -> o2.getValue() - o1.getValue());
        pque.addAll(map.entrySet());
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            ret.add(pque.poll().getKey());
        }
        int[] arr = new int[ret.size()];
        int i=0;
        for(Integer a: ret){
            arr[i++] = a;
        }

        return arr;
    }

    public static void main(String[] args) {

    }
}
