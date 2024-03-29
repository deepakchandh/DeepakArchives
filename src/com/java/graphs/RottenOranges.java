package com.java.graphs;

import java.util.*;
//https://leetcode.com/problems/rotting-oranges/description/
public class RottenOranges {

    //BFS
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int countFresh = 0;

        for(int i=0; i< grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j] == 2)
                    queue.offer(new int[]{i,j});
                else if(grid[i][j] == 1)
                    countFresh++;
            }
        }

        if(countFresh == 0) return 0;

        int count =0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while(!queue.isEmpty()){
            ++count;

            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] point = queue.poll();
                for(int dir[]: dirs){
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];

                    if(x < 0 || y < 0 || x>= rows || y>= cols ||grid[x][y] == 0 || grid[x][y] == 2)
                        continue;

                    grid[x][y] = 2;
                    queue.offer(new int[]{x,y});
                    countFresh--;
                }
            }

        }
        return countFresh == 0 ? count - 1 : -1;



    }

    public static void main(String[] args) {

        int[][] grid = new int[][]
                {
                        {2,1,1},{1,1,0},{0,1,1}
                };
        RottenOranges rottenOranges = new RottenOranges();
        System.out.println(rottenOranges.orangesRotting(grid));

    }
}
