package com.java.practice;

public class MaxTurbulence {

    public static int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int ans = 1;
        int inc = 1, dec = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                inc = dec + 1;
                dec = 1;
            }
            else if (arr[i] < arr[i - 1]) {
                dec = inc + 1;
                inc = 1;
            }
            else {
                // arr[i] == arr[i-1]
                inc = 1;
                dec = 1;
            }
            ans = Math.max(ans, Math.max(inc, dec));
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
    }
}
