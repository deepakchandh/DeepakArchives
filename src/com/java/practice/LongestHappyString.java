package com.java.practice;

import java.util.Arrays;

public class LongestHappyString {

    public static String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();

        // Characters and their counts
        int[][] letters = {
                {a, 'a'},
                {b, 'b'},
                {c, 'c'}
        };

        while(true){
            Arrays.sort(letters, (x, y) -> x[0] - y[0]);

            boolean appended = false;

            for(int i=2;i>=0;i--){
                int count = letters[i][0];
                char ch = (char) letters[i][1];

                if(count == 0) continue;

                int len = sb.length();
                if (len >= 2 && sb.charAt(len - 1) == ch && sb.charAt(len - 2) == ch) {
                    continue;
                }

                sb.append(ch);
                letters[i][0]--;
                appended = true;
                break;

            }
            if (!appended) break;
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(longestDiverseString(1,1,7));
    }
}
