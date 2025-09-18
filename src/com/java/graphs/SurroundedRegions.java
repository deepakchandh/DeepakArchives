package com.java.graphs;

import java.io.IOException;

public class SurroundedRegions {


    private int ROWS, COLS;

    public void solve(char[][] board) {
        ROWS = board.length;
        COLS = board[0].length;

        for (int r = 0; r < ROWS; r++) {
            if (board[r][0] == 'O') {
                capture(board, r, 0);
            }
            if (board[r][COLS - 1] == 'O') {
                capture(board, r, COLS - 1);
            }
        }

        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == 'O') {
                capture(board, 0, c);
            }
            if (board[ROWS - 1][c] == 'O') {
                capture(board, ROWS - 1, c);
            }
        }

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void capture(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= ROWS ||
                c >= COLS || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T';
        capture(board, r + 1, c);
        capture(board, r - 1, c);
        capture(board, r, c + 1);
        capture(board, r, c - 1);
    }

    public static void main(String[] args) throws IOException {
        char mat[][] = {{'X','X','X','X'},
                {'X','O','O','X'},
                {'X','O','O','X'},
                {'X','O','X','X'}
        };
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(mat);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }



    }



}
