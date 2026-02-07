package com.java.practice;

public class OriginalDigits {
    public static String originalDigits(String s) {

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[] digit = new int[10];

        // Unique letters
        digit[0] = count['z' - 'a']; // zero
        digit[2] = count['w' - 'a']; // two
        digit[4] = count['u' - 'a']; // four
        digit[6] = count['x' - 'a']; // six
        digit[8] = count['g' - 'a']; // eight

        // Derived letters
        digit[1] = count['o' - 'a'] - digit[0] - digit[2] - digit[4];
        digit[3] = count['h' - 'a'] - digit[8];
        digit[5] = count['f' - 'a'] - digit[4];
        digit[7] = count['s' - 'a'] - digit[6];
        digit[9] = count['i' - 'a'] - digit[5] - digit[6] - digit[8];

        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            while (digit[i]-- > 0) {
                result.append(i);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(originalDigits("owoztneoer"));
    }
}
