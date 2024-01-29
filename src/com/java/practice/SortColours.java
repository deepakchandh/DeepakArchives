package com.java.practice;
//https://leetcode.com/problems/sort-colors/
public class SortColours {

    public static void sortColors(int[] a) {


        int lo = 0;
        int hi = a.length - 1;
        int mid = 0, temp = 0;
        // Iterate till all the elements are sorted
        while (mid <= hi) {
            switch (a[mid]) {

                case 0: { // If the element is 0
                    temp = a[lo];
                    a[lo] = a[mid];
                    a[mid] = temp;
                    lo++;
                    mid++;
                    break;
                }

                case 1: // If the element is 1
                    mid++;
                    break;

                case 2: { // If the element is 2
                    temp = a[mid];
                    a[mid] = a[hi];
                    a[hi] = temp;
                    hi--;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        sortColors(new int[]{2,0,1});
        System.out.println();
    }
}
