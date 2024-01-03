package com.java.practice;
//https://leetcode.com/problems/number-of-ways-to-select-buildings/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency
public class NumberOfWaysToSelectBuilding {

     static long numberOfWays(String s) {
         int zeroCount = 0; //0
         int oneCount = 0; //1
         long zeroOneCount = 0; //01
         long oneZeroCount = 0; //10
         long answer = 0;
         for (char c : s.toCharArray()) {
             if (c == '0') {
                 answer += zeroOneCount; // Each of previously 01 will pair with current 0 to from 010, so it is answer [xx01xxx01xxx{0}]
                 oneZeroCount += oneCount; // Each of previously 1 will pair with courrnet 0 to form 10 [xx1xx1xx1xx1xx1xx{0}]
                 zeroCount++;
             } else {
                 answer += oneZeroCount; // Each of previously 10 found will pair with current 1 to shape answeer 101 [xx10xxx10xxx{1}]
                 zeroOneCount += zeroCount; // each of previously found 0 will pair with current 1 to shape 01 [xx0xxx0xxx{1}]
                 oneCount++;
             }
         }
         return answer;
    }

    public static void main(String[] args) {

         System.out.println(numberOfWays("001101"));

    }
}
