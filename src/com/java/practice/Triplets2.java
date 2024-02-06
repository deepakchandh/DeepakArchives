package com.java.practice;

import java.util.Stack;

//https://www.geeksforgeeks.org/check-whether-there-exists-a-triplet-i-j-k-such-that-arri-arrk-arrj-for-i-j-k/
public class Triplets2 {

    public static boolean findTripletNaiveApproach(int[] nums)
    {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // Triplet found, hence return false
                    if (nums[i] < nums[k] && nums[k] < nums[j])
                        return true;
                }
            }
        }
        // No triplet found, hence return false
        return false;
    }


    public static boolean findTriplet(int[] arr)
    {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        // Initialize the heights of h1 and h3
        // to INT_MAX and INT_MIN respectively
        int h3 = Integer.MIN_VALUE;
        int h1 = Integer.MAX_VALUE;

        for(int i = n - 1; i >= 0; i--)
        {

            // Store the current element as h1
            h1 = arr[i];

            // If the element at top of stack
            // is less than the current element
            // then pop the stack top
            // and keep updating the value of h3
            while (!st.empty() && st.peek() < arr[i])
            {
                h3 = st.peek();
                st.pop();
            }

            // Push the current element
            // on the stack
            st.push(arr[i]);

            // If current element is less
            // than h3, then we found such
            // triplet and return true
            if (h1 < h3)
            {
                return true;
            }
        }

        // No triplet found, hence return false
        return false;
    }

    public static void main(String[] args) {
//        int arr[] = { 4, 7, 5, 6 };
        int arr[] = { 7, 6 };

        // Function call
        if (findTriplet(arr))
        {
            System.out.println("Yes");
        }
        else
        {
            System.out.println("No");
        }
    }
}
