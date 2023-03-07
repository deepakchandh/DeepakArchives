package com.java.practice;

import java.util.*;

public class LongestCommonPrefix {

    static String longestCommonPrefix(String[] strs) {

        int size = strs.length;
        if(size == 0)
            return "";
        if(size == 1)
            return strs[0];

        Arrays.sort(strs);

        int i=0;
        int end = Math.min(strs[0].length(), strs[size-1].length());
        while(i<end && strs[0].charAt(i)==strs[size-1].charAt(i))
            i++;

        return strs[0].substring(0, i);
    }

    public static void main(String[] args) {
        String[] str = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(str));
    }
}
