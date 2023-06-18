package com.java.practice;

import java.util.*;
// #Maps
public class LongestKsubStr {

    public static int longestkSubstr(String s, int k) {
        // code here
        int i = 0;
        int j = 0;
        Map<Character, Integer> mp = new HashMap<>();
        int result = -1;

        while(j < s.length())
        {
            mp.put(s.charAt(j), mp.getOrDefault(s.charAt(j), 0) + 1);

            while(mp.size() > k)
            {
                char a = s.charAt(i);
                mp.put(a, mp.getOrDefault(a, 0) - 1);

                if(mp.get(a) == 0)
                    mp.remove(a);
                i++;
            }

            if(mp.size() == k)
                result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(longestkSubstr("aabacbebebe", 3));

    }
}
