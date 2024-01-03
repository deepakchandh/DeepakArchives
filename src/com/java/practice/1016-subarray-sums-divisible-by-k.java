package com.java.practice;

import java.util.*;
//https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/3070544/prefix-sum-hashmap-o-n-java-video-explanation/
class Solution1 {
    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0,1);
        int prefix = 0,  res = 0;
        for(int ele: nums){
            prefix = (prefix + ele)%k;
            if(prefix<0)
                prefix +=k;

            if(count.containsKey(prefix)){
                res = res+count.get(prefix);
                count.put(prefix,count.get(prefix)+1);
            }else{
                count.put(prefix,1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }
}