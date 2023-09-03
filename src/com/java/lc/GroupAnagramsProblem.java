package com.java.lc;

import java.util.*;

//https://leetcode.com/problems/group-anagrams/description/
public class GroupAnagramsProblem {

    public static List<List<String>> groupAnagrams(String[] strs) {
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


    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            char count[] = new char[26];
            for(char c : str.toCharArray())
                count[c-'a']++;
            String keyStr = String.valueOf(count);
            if(!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams2(new String[]{"eat","tea","tan","ate","nat","bat"}));

    }
}
