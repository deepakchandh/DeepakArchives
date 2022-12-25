package com.java.graphs;

import java.util.*;
// https://leetcode.com/problems/word-ladder/solutions/1764371/a-very-highly-detailed-explanation/
public class WordLadder2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();

        int changes = 1;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String word = queue.poll();
                if(word.equals(endWord))
                    return changes;

                for(int j = 0; j < word.length(); j++){
                    for(char k = 'a'; k <= 'z'; k++){
                        char arr[] = word.toCharArray();
                        arr[j] = k;

                        String str = new String(arr);
                        if(set.contains(str) && !visited.contains(str)){
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
//        System.out.println(wordLadder2.ladderLength("hit" , "cog", Arrays.asList("hot","dot","dog","lot","log","cog") ));
        System.out.println(wordLadder2.ladderLength("be" , "ko", Arrays.asList("ce", "mo", "ko", "me", "co") ));
    }
}
