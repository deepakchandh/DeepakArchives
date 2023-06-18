package com.java.practice;

import java.util.Arrays;
import java.util.List;

public class MedianValue {

    public static float FindMedianValue(List<Integer> list1, List<Integer> list2) {

        int[] nums1 = new int[list1.size()];
        int[] nums2 = new int[list2.size()];
        int k=0;
        for(Integer l1: list1){
            nums1[k++] = l1;
        }
        k=0;
        for(Integer l2: list2){
            nums2[k++] = l2;
        }
        int index1 = 0;
        int index2 = 0;
        int med1 = 0;
        int med2 = 0;
        for (int i=0; i<=(nums1.length+nums2.length)/2; i++) {
            med1 = med2;
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2] ) {
                med2 = nums1[index1];
                index1++;
            }  else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // the median is the average of two numbers
        if ((nums1.length+nums2.length)%2 == 0) {
            return (float)(med1+med2)/2;
        }

        return med2;


    }



    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int med1 = 0;
        int med2 = 0;
        for (int i=0; i<=(nums1.length+nums2.length)/2; i++) {
            med1 = med2;
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2] ) {
                med2 = nums1[index1];
                index1++;
            }  else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // the median is the average of two numbers
        if ((nums1.length+nums2.length)%2 == 0) {
            return (float)(med1+med2)/2;
        }

        return med2;
    }

    public static void main(String[] args) {
        List<Integer> a1 = Arrays.asList(3,4,22,31,41);
        List<Integer> a2 = Arrays.asList(1);

        int[] arr1 = new int[] {3,4,22,31,41};
        int[] arr2 = new int[] {1};
//        System.out.println(findMedianSortedArrays(arr1, arr2));
        System.out.println(FindMedianValue(a1, a2));
//        findMedianSortedArrays()
    }
}
