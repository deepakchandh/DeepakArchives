package com.java.practice;

public class RemoveOcc {

    public static String removeOccurrences(String s, String part) {
        int partLen = part.length();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append(c);
            int strLen = sb.length();
            // If end matches `part`, remove it
            if (strLen >= partLen && sb.substring(strLen - partLen, strLen).equals(part)) {
                sb.delete(strLen - partLen, strLen);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeOccurrences("daabcbaabcbc", "abc"));
    }
}
