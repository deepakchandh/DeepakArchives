package com.java.lc;

// #String
import java.util.*;
//https://leetcode.com/problems/valid-sudoku/solutions/15472/short-simple-java-using-strings/
public class ValidSudoku {

    public static boolean isValidSudoku3(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if     (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        char[][] board = new char[][]{
                // 0,  1,  2,  3,  4,  5,  6,  7,  8
                 {'5','3','.','.','5','.','.','.','.'}, //0
                 {'6','.','.','1','9','5','.','.','.'}, //1
                 {'.','9','8','.','.','.','.','6','.'} //2
                ,{'8','.','.','.','6','.','.','.','3'} //3
                ,{'4','.','.','8','.','3','.','.','1'} //4
                ,{'7','.','.','.','2','.','.','.','6'} //5
                ,{'.','6','.','.','.','.','2','8','.'} //6
                ,{'.','.','.','4','1','9','.','.','5'} //7
                ,{'.','.','.','.','8','.','.','7','9'} //8
        };
        System.out.println(isValidSudoku3(board));

    }
}
