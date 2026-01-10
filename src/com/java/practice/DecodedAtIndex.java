package com.java.practice;

public class DecodedAtIndex {


    public static String decodeAtIndex(String s, int k) {
        long size = 0;

        // 1. Compute total decoded length
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                size *= (c - '0');
            } else {
                size++;
            }
        }

        // 2. Traverse backwards
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            k %= size;

            if (k == 0 && Character.isLetter(c)) {
                return String.valueOf(c);
            }

            if (Character.isDigit(c)) {
                size /= (c - '0');
            } else {
                size--;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(decodeAtIndex("leet2code3", 10));
    }
}
