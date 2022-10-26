package com.java.practice;
//https://leetcode.com/problems/3sum/submissions/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class triplets {


    List<List<Integer>> news = new ArrayList<>();
    private static void findTriplets(int[] arr, int n) {
        boolean found = false;

        Arrays.sort(arr);

        for(int i=0;i<n-1;i++){
            int l = i+1;
            int r = n-1;
            int x = arr[i];
            while(l < r){
                if (x + arr[l] + arr[r] == 0) {
                    System.out.printf("%d %d %d\n", x, arr[l], arr[r]);
                    l++;
                    r--;
                    found = true;
                }
                else if(x + arr[l] + arr[r] < 0)
                    l++;
                else
                    r--;
            }
        }
        if (found == false)
            System.out.println(" No Triplet Found");
    }

    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { -1,0,1,2,-1,-4};
        int n = arr.length;
        findTriplets(arr, n);
    }

    // iterating with i, and then binary search inside every iteration (l,r)
}
