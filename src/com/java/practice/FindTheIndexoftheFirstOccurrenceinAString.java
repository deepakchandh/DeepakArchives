package com.java.practice;


//https://takeuforward.org/data-structure/kmp-algorithm/
public class FindTheIndexoftheFirstOccurrenceinAString {


    static int kmp(String string, String pattern) {
        int i = 0, j = 0, m = pattern.length(), n = string.length();
        pattern = ' ' + pattern; //just shifting the pattern indices by 1
        int piTable[]=new int[m+1];
        for (i = 2; i <= m; i++) {
            while (j <= m && pattern.charAt(j + 1) == pattern.charAt(i))
                piTable[i++] = ++j;
            j = 0;
        }
        j = 0;
        for (i = 0; i < n; i++) {
            if (pattern.charAt(j + 1) != string.charAt(i)) {
                while (j != 0 && pattern.charAt(j + 1) != string.charAt(i))
                    j = piTable[j];
            }
            j++;
            if (j == m) return i - m + 1;
        }
        return -1;

    }



    public static void main(String[] args) {

        FindTheIndexoftheFirstOccurrenceinAString index = new FindTheIndexoftheFirstOccurrenceinAString();
        System.out.println(kmp("sadbutsad", "sad"));
//        System.out.println(kmp("aaaaaaaamaaaaaab", "aaacaaaaac"));

    }
}
