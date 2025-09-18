package com.java.dynamic;

public class InterLeaving {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) return false;

        // dp[i][j] = true if s3[0..i+j-1] is interleaving of s1[0..i-1] and s2[0..j-1]
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        // initialize first column (using only s1)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        // initialize first row (using only s2)
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        // fill dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int k = i + j - 1; // index in s3
                dp[i][j] =
                        (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k)) ||
                                (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k));
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        // Sample test cases
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";  // true
        String s4 = "aadbbbaccc";  // false

        System.out.println("Test 1: " + isInterleave(s1, s2, s3)); // true
        System.out.println("Test 2: " + isInterleave(s1, s2, s4)); // false
        System.out.println("Test 3: " + isInterleave("abc", "def", "adbcef")); // true
        System.out.println("Test 4: " + isInterleave("", "", "")); // true
    }
}
