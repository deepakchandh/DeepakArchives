package com.java.practice;

public class IntToRoman {

    public static String intToRoman(int num) {
        // Values and corresponding Roman numerals (largest → smallest)
        int[] values = {1000, 900, 500, 400,
                100,  90,  50,  40,
                10,   9,   5,   4,    1};
        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"
        };

        StringBuilder roman = new StringBuilder();

        // Greedily subtract largest values while appending symbols
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3774));
    }
}
