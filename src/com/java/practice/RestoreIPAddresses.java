package com.java.practice;

import java.util.*;

public class RestoreIPAddresses {

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
        System.out.println(restoreIpAddresses(input));
    }
}
