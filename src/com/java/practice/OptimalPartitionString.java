package com.java.practice;
//https://leetcode.com/problems/optimal-partition-of-string/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency
public class OptimalPartitionString {

    public static int partitionString(String s) {
        int hash[] = new int[26];
        int count = 0;
        for(int i = 0 ; i < s.length(); i ++){
            if(hash[s.charAt(i) - 'a'] == 1){
                count++;
                hash = new int[26];
            }
            hash[s.charAt(i)-'a']++;
        }
        return count+1;
    }

    public static void main(String[] args) {

        System.out.println(partitionString("abacaba"));

    }
}
