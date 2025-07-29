package com.java.practice;
// https://leetcode.com/problems/daily-temperatures/
import java.util.Stack;
// dailyTemperatures
public class minimumTemperature {

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] results = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int index = stack.pop();
                results[index] = i - index;
            }

            stack.push(i);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(dailyTemperatures(new int[] {73,74,75,71,69,72,76,73}));
    }
}
