package com.java.practice;

import java.util.Stack;

public class MaxStack {

    /*
    Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

     */

    Stack<int[]> stack;
    //  first index it will store original value and in second index it will store current max value
    public MaxStack() {
        stack = new Stack<>();

    }

    public void push(int x) {
        if(stack.size()==0) {
            stack.add(new int[]{x, x});
        }
        else {
            int current_max = stack.peek()[1];
            int new_max = Math.max(x, current_max);
            stack.add(new int[]{x, new_max});
        }
    }

    public int pop() {
        return stack.pop()[0];
    }

    public int top() {
        return stack.peek()[0];
    }

    public int peekMax() {
        return stack.peek()[1];
    }

    public int popMax() {
        Stack<int[]> temp_stack = new Stack<>();
        int max_val = stack.peek()[1];
        while(stack.peek()[0] != max_val) {
            temp_stack.add(stack.pop());
        }
        int ans = stack.pop()[1];
        while(temp_stack.size()!=0) {
            push(temp_stack.pop()[0]);
        }
        return ans;
    }

}
