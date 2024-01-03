package com.java.practice;
// https://leetcode.com/problems/next-greater-element-ii/discuss/98273/Java-10-lines-and-C%2B%2B-12-lines-linear-time-complexity-O(n)-with-explanation
import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n)
                stack.push(i);
        }
        return next;
    }



//int arr[]={97, 87, 35, 45, 12}; // {-1, 97, 45, 97, 97 }
    public static int[] nextGreaterElementss(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();
        // push elements of stack in rev
        for (int i = n - 1; i >= 0; i--) {
            stack.push(i);
        }

        for(int i=n-1 ; i>=0 ; i--){
            result[i] = -1;
            while (!stack.empty() && nums[stack.peek()] <= nums[i]){
                stack.pop();
            }
            if (!stack.empty()){
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }
        return result;
    }

/// https://leetcode.com/problems/next-greater-element-ii/discuss/98262/Typical-ways-to-solve-circular-array-problems.-Java-solution.
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(i);
        }
// updating the result value from reverse
        for (int i = n - 1; i >= 0; i--) {
            result[i] = -1;
            int tt = stack.peek();
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()){
                result[i] = nums[stack.peek()];
            }
            stack.add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int arr[]={97, 87, 35, 45, 12}; // {-1, 97, 45, 97, 97 }
        int[] res = nextGreaterElements(arr);
        for(int i: res)
            System.out.print(res[i]+" ");
    }
}
