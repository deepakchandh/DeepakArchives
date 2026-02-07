package com.java.practice;

import java.util.*;

public class RestoreIPAddresses {

    // iteration

    public static List<String> restoreIpAddressesIter(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        if (n < 4 || n > 12) return result; // Quick bounds check

        for (int i = 1; i <= 3 && i < n; i++) {
            String s1 = s.substring(0, i);
            if (!isValid(s1)) continue;

            for (int j = i + 1; j <= i + 3 && j < n; j++) {
                String s2 = s.substring(i, j);
                if (!isValid(s2)) continue;

                for (int k = j + 1; k <= j + 3 && k < n; k++) {
                    int remaining = n - k;
                    if (remaining < 1 || remaining > 3) continue;

                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k);

                    if (isValid(s3) && isValid(s4)) {
                        result.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }

        return result;
    }


    private static boolean isValid(String s) {
        if (s.length() == 0 || s.length() > 3)
            return false;
        if (s.charAt(0) == '0' && s.length() > 1)
            return false;
        int val = Integer.parseInt(s);
        return val >= 0 && val <= 255;
    }


    /// --- back track

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int index, List<String> path, List<String> result){
        if (path.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }

        // Try parts of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) break;

            String segment = s.substring(index, index + len);

            // Leading zero check
            if (segment.length() > 1 && segment.startsWith("0")) continue;

            // Range check
            int val = Integer.parseInt(segment);
            if (val > 255) continue;

            path.add(segment);
            backtrack(s, index + len, path, result);
            path.remove(path.size() - 1);
        }
    }



    public static void main(String[] args) {
        String input = "24925411135";
        System.out.println(restoreIpAddressesIter(input));
    }
}
