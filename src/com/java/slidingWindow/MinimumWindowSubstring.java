package com.java.slidingWindow;

import java.util.*;
//https://leetcode.com/problems/minimum-window-substring/

//https://leetcode.com/problems/minimum-window-substring/solutions/1496754/java-tc-o-s-t-sc-o-t-space-optimized-sliding-window-using-two-pointers/?orderBy=most_votes&page=2
public class MinimumWindowSubstring {

    public static String minWindowSliding(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 ||
                s.length() < t.length()) {
            return new String();
        }
        int[] map = new int[128];
        int count = t.length();
        int start = 0, end = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
        /// UPVOTE !
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        char[] chS = s.toCharArray();

        while (end < chS.length) {
            char c = chS[end];
            end++;
            if (map[c] > 0) {
                count--;
            }
            map[c]--;

            while (count == 0) {
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                char cStart = chS[start];
                start++;
                if (map[cStart] == 0) {
                    count++;
                }
                map[cStart]++;
            }
        }

        return minLen == Integer.MAX_VALUE ? new String() :
                new String(chS, startIndex, minLen);

    }

    public static void main(String[] args) {
        System.out.println(minWindowSliding("ADOBECODEBANC", "ABC"));
    }
}
