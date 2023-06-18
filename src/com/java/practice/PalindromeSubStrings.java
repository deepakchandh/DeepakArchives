package com.java.practice;

public class PalindromeSubStrings {


    static int count =1;
    public static int countSubstrings(String s) {
        if(s.length()==0)
            return 0;
        for(int i=0; i<s.length()-1; i++){
            checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
            checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
        }
        return count;
    }

    private static void checkPalindrome(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            count++;
            i--; j++;
        }
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }
}
