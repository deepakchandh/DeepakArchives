package com.java.practice;

public class NumberOfUniqueGoodSubsequences {

    public static int numberOfUniqueGoodSubsequences(String binary) {
        int mod = (int)1e9 + 7, ends0 = 0, ends1 = 0, has0 = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                ends1 = (ends0 + ends1 + 1) % mod;
            } else {
                ends0 = (ends0 + ends1) % mod;
                has0 = 1;
            }
        }
        return (ends0 + ends1 + has0) % mod;
    }

    public static void main(String[] args) {
        System.out.println(numberOfUniqueGoodSubsequences("001"));
    }
}
