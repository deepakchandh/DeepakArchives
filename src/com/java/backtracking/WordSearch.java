package com.java.backtracking;

//https://leetcode.com/problems/word-search/description/

// #Backtracking #DFS #Graph
public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length)
            return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length)
            return false;
        if (board[y][x] != word[i])
            return false;
        board[y][x] ^= 256; // binary value of 10000000 - so that it doesn't get used by other
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                        {'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}
                };
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }
}
