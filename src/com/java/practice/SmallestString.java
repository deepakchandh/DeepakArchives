package com.java.practice;

public class SmallestString {

    public static String smallestString(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;

        while(i < n && arr[i] == 'a')
            i++;

        if(i==n){
            arr[n-1] = 'z';
            return new String(arr);
        }

        while(i<n && arr[i] != 'a'){
            arr[i] = (char)(arr[i] -1);
            i++;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(smallestString("cbabc"));
    }
}
