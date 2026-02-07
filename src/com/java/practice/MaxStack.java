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

    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        } else {
            maxStack.push(Math.max(x, maxStack.peek()));
        }
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack<>();

        // Remove elements until max is found
        while (top() != max) {
            buffer.push(pop());
        }

        // Remove the max itself
        pop();

        // Restore the elements
        while (!buffer.isEmpty()) {
            push(buffer.pop());
        }

        return max;
    }

    public static void main(String[] args) {
        MaxStack stk = new MaxStack();

        stk.push(5);
        stk.push(1);
        stk.push(5);

        System.out.println(stk.top());     // 5
        System.out.println(stk.popMax());  // 5
        System.out.println(stk.top());     // 1
        System.out.println(stk.peekMax()); // 5
        System.out.println(stk.pop());     // 1
        System.out.println(stk.top());     // 5
    }

}
