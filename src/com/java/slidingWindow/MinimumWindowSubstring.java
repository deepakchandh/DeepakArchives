package com.java.slidingWindow;

import java.util.*;
//https://leetcode.com/problems/minimum-window-substring/

//https://leetcode.com/problems/minimum-window-substring/solutions/1496754/java-tc-o-s-t-sc-o-t-space-optimized-sliding-window-using-two-pointers/?orderBy=most_votes&page=2
public class MinimumWindowSubstring {

    public static String minWindowSliding(String s, String t) {
        int minStart =0, minLen =Integer.MAX_VALUE, start=0, end=0;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int charLeft = t.length();

        while(end < s.length()){
            if(map.containsKey(s.charAt(end))){
                int cnt = map.get(s.charAt(end));
                if (cnt > 0) {
                    charLeft--;
                }
                map.put(s.charAt(end), cnt-1);
            }
            end++;

            // Sliding window.. Removing the older elements
            while(charLeft  == 0){
                if(minLen > end-start ){
                    minLen = end-start;
                    minStart = start;
                }

                if(map.containsKey(s.charAt(start))){
                    int count = map.get(s.charAt(start));
                    if (count == 0) {
                        charLeft++;
                    }
                    map.put(s.charAt(start), count+1);
                }

                start++;
            }

        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindowSliding("ADOBECODEBANC", "ABC"));
    }
}
