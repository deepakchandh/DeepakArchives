package com.java.practice;

public class TimeToReArrange {
    public static int secondsToRemoveOccurrences(String s) {
        int seconds = 0;
        while (s.indexOf("01") >= 0) {
            s = s.replace("01", "10");
            ++seconds;
        }
        return seconds;
    }

    public static void main(String[] args) {
        System.out.println(secondsToRemoveOccurrences("0110101"));
    }
}
