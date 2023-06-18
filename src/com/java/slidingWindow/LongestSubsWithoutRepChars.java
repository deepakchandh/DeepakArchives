package com.java.slidingWindow;

import java.util.*;

public class LongestSubsWithoutRepChars {

    public static int lengthOfLongestSubstring(String s) {

        int i=0, j=0, maxx = 0;
        Set<Character> set = new HashSet<>();
        while( j < s.length()){

            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                maxx = Math.max(maxx, set.size());
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return maxx;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
