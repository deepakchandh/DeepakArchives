package com.java.backtracking;

//https://leetcode.com/problems/word-search/description/

// #Backtracking #DFS #Graph
public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int index) {

        // Check boundaries + current char match
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                || board[r][c] != word.charAt(index)) {
            return false;
        }

        // If we matched the last character
        if (index == word.length() - 1) {
            return true;
        }

        // Mark visited
        char temp = board[r][c];
        board[r][c] = '#';

        int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
        for (int[] d : directions) {
            int nr = r + d[0], nc = c + d[1];
            if (dfs(board, word, nr, nc, index + 1)) {
                board[r][c] = temp; // restore before returning
                return true;
            }
        }

        // Backtrack (restore cell)
        board[r][c] = temp;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                        {'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}
                };
        String word = "CCS";
        System.out.println(exist(board, word));
    }
}
