package com.java.practice;

import java.util.Arrays;

public class LongestHappyString {

    public static String longestDiverseString(int a, int b, int c) {
        int[][] chars = { {a, 'a'},
                {b, 'b'},
                {c, 'c'} };

        StringBuilder sb = new StringBuilder();

        while(true){
            Arrays.sort(chars, (x, y) -> y[0] - x[0]);

            boolean added = false;

            for (int i = 0; i < 3; i++) {
                int len = sb.length();

                if(chars[i][0] == 0)
                    continue;

                if(len >= 2 && sb.charAt(len-1) == chars[i][1] && sb.charAt(len - 2) == chars[i][1])
                    continue;


                // use this char
                sb.append((char) chars[i][1]);
                chars[i][0]--;
                added = true;
                break;

            }
            if (!added) break;
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(longestDiverseString(1,1,7));
    }
}
