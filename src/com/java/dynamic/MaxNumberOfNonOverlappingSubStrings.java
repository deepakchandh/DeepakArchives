package com.java.dynamic;

public class MaxNumberOfNonOverlappingSubStrings {

    public static int maxPalindromes(String s, int k) {
        int res = 0;
        for (int i = 0; i + k - 1 < s.length(); i++) {
            if(checkPalindrome(s, i, i + k - 1)){
                res++;
                i = i + k - 1;
            }else if (i + k < s.length() && checkPalindrome(s, i, i + k )){
                res++;
                i = i + k ;
            }
        }
        return res;
    }

    private static boolean checkPalindrome(String s, int left, int right){
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(maxPalindromes("abaccdbbd", 3));
    }
}
