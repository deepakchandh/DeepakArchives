package com.java.practice;

import java.util.HashSet;

public class longestConsecutiveSequence {

    public static void main(String arg[])
    {
        int arr[] = { 100,4,200,1,3,2 };
        System.out.println("Result: "
                + longestConsecutive(arr));
    }

    public static  int longestConsecutive(int[] nums) {

        HashSet<Integer> hset = new HashSet<>();
        for(int i=0;i<nums.length;i++)
            hset.add(nums[i]);

        int longStreak = 0;
        for(int num: nums){
            if(! hset.contains(num - 1)){
                int currNum = num;
                int currentStreak = 1;

                while(hset.contains(currNum + 1)){
                    currNum+=1;
                    currentStreak++;
                }
                longStreak = Math.max(longStreak, currentStreak );
            }

        }
        return longStreak;

    }

}
