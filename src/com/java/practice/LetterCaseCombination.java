package com.java.practice;

import java.util.*;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCaseCombination {

    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        res.add("");
        String[] digitToChar = {
                "", "", "abc", "def", "ghi", "jkl",
                "mno", "qprs", "tuv", "wxyz"
        };

        for (char digit : digits.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            for (String curStr : res) {
                for (char c : digitToChar[digit - '0'].toCharArray()) {
                    tmp.add(curStr + c);
                }
            }
            res = tmp;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(letterCombinations("34"));

    }
}
