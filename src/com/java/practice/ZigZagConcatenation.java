package com.java.practice;

public class ZigZagConcatenation {

    static void printZigZagConcat(String str, int n)
    {
        if (n == 1) {
            System.out.print(str + "\n");
        }
        String res = "";
        String[] arr = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = "";
        boolean down = false;
        int row = 0; // helps in building individual blocks
        // of Strings

        for (int i = 0; i < str.length(); i++) {
            if (row >= 0)
                arr[row] += (str.charAt(i));
            if (row == n - 1) {
                down = false;
            }
            if (row == 0) {
                down = true;
            }
            if (!down)
                row--;
            else
                row++;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
        }
    }

    public static void main(String[] args) {

//        String str = "PAYPALISHIRING";
        String str = "ABCDEFGH";
        int N = 2;
        printZigZagConcat(str, N);

    }
}
