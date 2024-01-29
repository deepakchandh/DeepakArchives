package com.java.practice;


import java.util.*;

/*
Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc" -> acdb
Output: "acdb"

Answer: First Of all, we have to pick the character's if it is not already visited.
If, that's the case we'll try to pick these character's. We'll also make sure, the previously picked character is smaller then
the current character in order to maintain lexicographically order. But, how we can check the previously picked character is best for!!
And the answer is **Stack**!!
 */
public class RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        int visit[] = new int[26];
        int lastOccurence[] = new int[26];

        // Stack to maintain the result string in reverse order
        Stack<Character> st = new Stack<>();

        // Record last occurrences of each character
        for (int i = 0; i < s.length(); i++) {
            lastOccurence[s.charAt(i) - 'a'] = i;
        }

        /*
        For each character letter:
                While the stack is not empty, the top character is lexicographically greater than letter, and letter has a later occurrence:
                    Pop the top character from the stack (it's safe to remove).
                    Mark it as unvisited in visit[].
                If letter hasn't been visited yet:
                    Push it onto the stack.
                    Mark it as visited in visit[].
         */

        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);

            while (!st.isEmpty() && st.peek() > letter && lastOccurence[st.peek() - 'a'] > i && visit[letter - 'a'] == 0) {
                visit[st.peek() - 'a'] = 0; // Mark as unvisited
                st.pop();
            }

            if (visit[letter - 'a'] == 0) {
                st.push(letter);
                visit[letter - 'a'] = 1; // Mark as visited
            }
        }
        StringBuilder ans = new StringBuilder("");
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));

    }

}
