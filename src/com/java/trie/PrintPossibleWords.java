package com.java.trie;

import java.util.*;

public class PrintPossibleWords {

    static final int SIZE = 26;

    static class TrieNode
    {
        TrieNode[] Child = new TrieNode[SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean leaf;

        // Constructor
        public TrieNode() {
            leaf = false;
            for (int i =0 ; i< SIZE ; i++)
                Child[i] = null;
        }
    }

    static void insert(TrieNode root, String Key)
    {
        int n = Key.length();
        TrieNode pChild = root;

        for (int i=0; i<n; i++)
        {
            int index = Key.charAt(i) - 'a';

            if (pChild.Child[index] == null)
                pChild.Child[index] = new TrieNode();

            pChild = pChild.Child[index];
        }

        // make last node as leaf node
        pChild.leaf = true;
    }

    static void searchWord(TrieNode root, boolean Hash[],
                           String str)
    {
        // if we found word in trie / dictionary
        if (root.leaf == true)
            System.out.println(str);

        // traverse all child's of current root
        for (int K =0; K < SIZE; K++)
        {
            if (Hash[K] == true && root.Child[K] != null )
            {
                // add current character
                char c = (char) (K + 'a');

                // Recursively search reaming character
                // of word in trie
                searchWord(root.Child[K], Hash, str + c);
            }
        }
    }

    // Prints all words present in dictionary.
    static void PrintAllWords(char Arr[], TrieNode root,
                              int n)
    {
        // create a 'has' array that will store all
        // present character in Arr[]
        boolean[] Hash = new boolean[SIZE];

        for (int i = 0 ; i < n; i++)
            Hash[Arr[i] - 'a'] = true;

        // temporary node
        TrieNode pChild = root ;

        // string to hold output words
        String str = "";

        // Traverse all matrix elements. There are only
        // 26 character possible in char array
        for (int i = 0 ; i < SIZE ; i++)
        {
            // we start searching for word in dictionary
            // if we found a character which is child
            // of Trie root
            if (Hash[i] == true && pChild.Child[i] != null )
            {
                str = str+(char)(i + 'a');
                searchWord(pChild.Child[i], Hash, str);
                str = "";
            }
        }
    }

    private static boolean canFormWord(String word, Map<Character, Integer> freqArr) {
        Map<Character, Integer> freqWord = new HashMap<>();
        for (char c : word.toCharArray()) {
            freqWord.put(c, freqWord.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freqWord.entrySet()) {
            char c = entry.getKey();
            int needed = entry.getValue();
            if (freqArr.getOrDefault(c, 0) < needed) {
                return false;
            }
        }
        return true;
    }

    public static List<String> findValidWords(String[] dict, char[] arr) {
        // Build freq map from arr[]
        Map<Character, Integer> freqArr = new HashMap<>();
        for (char c : arr) {
            freqArr.put(c, freqArr.getOrDefault(c, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (String word : dict) {
            if (canFormWord(word, freqArr)) {
                result.add(word);
            }
        }
        return result;
    }

    // Test with a sample input
    public static void main(String[] args) {
        String[] dict = {"go", "bat", "me", "eat", "goal", "boy", "run"};
        char[] arr = {'e', 'o', 'b', 'a', 'm', 'g', 'l'};

        List<String> valid = findValidWords(dict, arr);
        System.out.println("Valid words are: " + valid);
    }
}
