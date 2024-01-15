package com.java.practice;

import java.util.Arrays;

// https://www.geeksforgeeks.org/problems/convert-array-into-zig-zag-fashion1638/1?page=1&status=unsolved&sprint=a663236c31453b969852f9ea22507634&sortBy=submissions
public class ZigZagFashion {


    public static void zigZag(int arr[], int n){
        // Code your solution here.

        Arrays.sort(arr);
        // traverse the array from 1 to N -1
        for (int i = 1; i <= arr.length - 2; i += 2) {
            // swap the current element with the next
            // element
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        for(int i=0;i< arr.length;i++){
            System.out.print(arr[i] +"_");
        }
    }

    public static void main(String[] args) {
        zigZag(new int[]{4, 3, 7, 8, 6, 2, 1}, 7);
        System.out.println();

    }
}
