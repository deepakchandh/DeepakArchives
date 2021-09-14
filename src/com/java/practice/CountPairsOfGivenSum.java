package com.java.practice;


import java.util.HashMap;

//https://www.geeksforgeeks.org/count-pairs-with-given-sum/
public class CountPairsOfGivenSum {


    static int arr[] = new int[] { 1, 5, 7, -1, 5 };

    static int getPairsCount(int n, int sum)
    {
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Store counts of all elements in map hm

        for (int i = 0; i < n; i++) {

            // initializing value to 0, if key not found
            if (!hm.containsKey(arr[i]))
                hm.put(arr[i], 0);

            hm.put(arr[i], hm.get(arr[i]) + 1);
        }

        int twice_count = 0;

        for(int i=0;i<n;i++){
            if(hm.get(sum - arr[i]) != null)
                twice_count += hm.get(sum - arr[i]);

            if(sum - arr[i] == arr[i])
                twice_count --;
        }

        return twice_count / 2;
    }

    // Driver method to test the above function
    public static void main(String[] args)
    {

        int sum = 6;
        System.out.println(
                "Count of pairs is "
                        + getPairsCount(arr.length, sum));
    }
}
