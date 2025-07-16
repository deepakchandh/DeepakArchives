package com.java.practice;
// https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
public class BeautySum {

    public static  int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] freq = new int[26];
            for (int j = i; j < s.length(); j++) {
                freq[s.charAt(j) - 'a']++;
                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        maxFreq = Math.max(maxFreq, freq[k]);
                        minFreq = Math.min(minFreq, freq[k]);
                    }
                }
                res += (maxFreq - minFreq);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(beautySum("aabcb"));

    }
}
