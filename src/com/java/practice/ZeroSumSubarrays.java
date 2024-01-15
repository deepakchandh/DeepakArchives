package com.java.practice;

public class ZeroSumSubarrays {


    public static long findSubarray(long[] arr ,int n)
    {
        //Your code here
        int count = 0;
        for(int i=0;i<n;i++){
            int prefixSum = 0;
            for(int j=i;j<n;j++){
                prefixSum += arr[j];
                if(prefixSum == 0)
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

//        System.out.println(findSubarray(new long[]{0,0,5,5,0,0}, 6));
        System.out.println(findSubarray(new long[]{6,-1,-3,4,-2,2,4,6,-12,-7}, 10));

    }
}
