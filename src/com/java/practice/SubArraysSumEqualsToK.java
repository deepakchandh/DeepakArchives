package com.java.practice;

import java.util.HashMap;

// https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/

/*
* An efficient solution is while traversing the array, store sum so far in currsum.
* Also, maintain the count of different values of currsum in a map.
* If the value of currsum is equal to the desired sum at any instance increment count of subarrays by one.
* The value of currsum exceeds the desired sum by currsum – sum. If this value is removed from currsum then the desired sum can be obtained.
* From the map find the number of subarrays previously found having sum equal to currsum-sum.
* Excluding all those subarrays from the current subarray, gives new subarrays having the desired sum. So increase count by the number of such subarrays.
*
* Note that when currsum is equal to the desired sum then also check the number of subarrays previously having a sum equal to 0.
* Excluding those subarrays from the current subarray gives new subarrays having the desired sum. Increase count by the number of subarrays having sum 0 in that case.
 */
public class SubArraysSumEqualsToK {

    static int findSubarraySum(int arr[], int n, int sum)
    {
        HashMap<Integer, Integer> prevSum = new HashMap<>();

        int res = 0;

        // Sum of elements so far.
        int currsum = 0;

        for (int i = 0; i < n; i++) {

            currsum += arr[i];

            if (currsum == sum)
                res++;

            if (prevSum.containsKey(currsum - sum))
                res += prevSum.get(currsum - sum);

            // Add currsum value to count of
            // different values of sum.
            Integer count = prevSum.get(currsum);
            if (count == null)
                prevSum.put(currsum, 1);
            else
                prevSum.put(currsum, count + 1);
        }

        return res;
    }

    public static void main(String[] args)
    {

        int arr[] = { 10, 2, -2, -20, 10 };
        int sum = -10;
        int n = arr.length;
        System.out.println(findSubarraySum(arr, n, sum));
    }

}
