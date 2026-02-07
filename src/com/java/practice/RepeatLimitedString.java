package com.java.practice;

import java.util.*;

public class RepeatLimitedString {

    public static String repeatLimitedString(String s, int repeatLimit) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max heap sorted by character in descending order
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[0] - a[0]
        );

        // Fill heap with (charIndex, count)
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.add(new int[] {i, freq[i]});
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int charIndex = top[0];
            int count = top[1];

            // How many times we can use this char now
            int use = Math.min(count, repeatLimit);

            // Append allowed repeats
            for (int i = 0; i < use; i++) {
                sb.append((char) (charIndex + 'a'));
            }

            count -= use;

            if (count > 0) {
                // We need a breaker (next largest character)
                if (pq.isEmpty()) {
                    // No breaker available -> end
                    break;
                }
                int[] next = pq.poll();
                sb.append((char) (next[0] + 'a'));
                next[1]--;

                // Re-add next char if still has count
                if (next[1] > 0) {
                    pq.add(next);
                }
                // Re-add the original char with reduced count
                pq.add(new int[] {charIndex, count});
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(repeatLimitedString("cczazcc", 3));
    }
}
