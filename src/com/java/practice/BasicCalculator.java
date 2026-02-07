package com.java.practice;

import java.util.*;

public class BasicCalculator {

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + (c -'0');
            }

            if((!Character.isDigit(c) && c != ' ') || i == s.length() -1){
                if(sign == '+'){
                    stack.push(num);
                }
                else if(sign == '-'){
                    stack.push(-num);
                }
                else if(sign == '*'){
                    int top = stack.pop();
                    stack.push(top * num);
                }
                else if(sign == '/'){
                    int top = stack.pop();
                    stack.push(top / num);
                }
                sign = c;
                num = 0;
            }
        }

        int result = 0;
        for (int val : stack) {
            result += val;
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(calculate("31+5/2"));
    }
}
