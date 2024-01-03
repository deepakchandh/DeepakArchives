package com.java.practice;
//https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency

public class MinSwapsToGroupAllOnesTogether {

    public static int minSwaps(int[] data) {
        int allones = 0;
        for (int i : data) {
            if (i == 1) allones++;
        }

        int slow = 0;
        int fast = 0;
        int maxOnes = 0;
        int curOnes = 0;

        while(fast < data.length){
            if(fast - slow < allones){
                if(data[fast] == 1){
                    curOnes++;
                    maxOnes = Math.max(maxOnes, curOnes);
                }
                fast++;
            }else{
                if(data[slow] == 1){
                    curOnes--;
                }
                slow++;
            }
        }
        return allones - maxOnes;
    }

    public static void main(String[] args) {

        System.out.println(minSwaps(new int[]{1,0,1,0,1}));

    }
}
