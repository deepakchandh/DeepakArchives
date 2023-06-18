package com.java.practice;

import java.util.*;

//https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/926059150/
public class TrappingRainWater2 {
    public int largestRectangleArea(int[] heights) {

        int n = heights.length;
        Stack<Integer> stack = new Stack();
        int maxArea = 0;
        for(int right = 0; right <= n; right++){
            int h = right == n ? 0 : heights[right];
            while(!stack.isEmpty() && h < heights[stack.peek()]){
                int curHeight = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = curHeight * (right - left - 1);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(right);
        }
        return maxArea;
    }

    public static void main(String[] args) {


    }
}
