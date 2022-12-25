package com.java.practice;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
public class BalancingParanthesis {
// backtracking concept
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max * 2){
            list.add(str);
            return;
        }
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
}
