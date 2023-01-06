package com.java.graphs;

import java.io.IOException;
//https://leetcode.com/problems/number-of-islands/description/
public class Islands {

    public static int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }
    private static void dfsFill(char[][] grid,int i, int j){
        if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&grid[i][j]=='1'){
            grid[i][j]='0';
            dfsFill(grid, i + 1, j); // down
            dfsFill(grid, i - 1, j); // up
            dfsFill(grid, i, j + 1); // right
            dfsFill(grid, i, j - 1); //left
        }
    }

    public static void main(String[] args) throws IOException {
        char mat[][] = {{'1','1','1','1', '0'},
                {'1','1','0','1', '0'},
                {'1','1','0','0', '0'},
                {'0','0','0','0', '0'}
        };

        System.out.println( numIslands(mat));
//        for(int i=0;i<m;i++){
//            for(int j=0;j<n;j++)
//                System.out.print(mat[i][j] + " ");
//            System.out.println();
//        }

    }

}
