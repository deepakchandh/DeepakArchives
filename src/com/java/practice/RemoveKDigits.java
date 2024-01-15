//$Id$
package com.java.practice;

import java.util.*;

//https://www.geeksforgeeks.org/build-lowest-number-by-removing-n-digits-from-a-given-number/
public class RemoveKDigits {

static String res="";

    public static String removeKdigitWithStackLogic(String num, int k)
    {
        StringBuilder result = new StringBuilder();
        // base cases
        if (k >= num.length()) {
            return "0";
        }if (k == 0) {
        return num;
        }

        Stack<Character> s = new Stack<Character>();

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            // Removing all digits in stack that are greater
            // than this digit(since they have higher
            // weightage)
            while (!s.isEmpty() && k > 0 && s.peek() > c) {
                s.pop();
                k--;
            }
            // ignore pushing 0
            if (!s.isEmpty() || c != '0')
                s.push(c);
        }

        // If our k isnt 0 yet then we keep popping out the
        // stack until k becomes 0
        while (!s.isEmpty() && k > 0) {
            k--;
            s.pop();
        }
        if (s.isEmpty())
            return "0";
        while (!s.isEmpty()) {
            result.append(s.pop());
        }
        String str = result.reverse().toString();

        return str;
    }
    
    public static void main(String[] args)
    {
//        String s = "1432219";
        String s = "2316";
        int k = 2;
//        System.out.println(removeKdigits(s, k));
        System.out.println(removeKdigitWithStackLogic(s,k));
    }
}
