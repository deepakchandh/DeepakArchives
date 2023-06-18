package com.java.lc;

import java.util.*;

//https://leetcode.com/problems/group-anagrams/description/
public class GroupAnagramsProblem {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String s1 = strs[i];
            char[] arr = s1.toCharArray();
            Arrays.sort(arr);
            String str = new String(arr);

            map.putIfAbsent(str, new ArrayList<>());

            map.get(str).add(s1);

        }
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {



    }
}
