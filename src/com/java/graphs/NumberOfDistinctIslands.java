package com.java.graphs;

import java.util.*;

// https://takeuforward.org/data-structure/number-of-distinct-islands/
public class NumberOfDistinctIslands {

    public static void main(String[] args) {
        int grid[][] = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        NumberOfDistinctIslands ob = new NumberOfDistinctIslands();
        int ans = ob.countDistinctIslands(grid);
        System.out.println(ans);
    }

    private int countDistinctIslands(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        Set<String> shapes = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, "S"); // "S" = start
                    shapes.add(path.toString());
                }
            }
        }
        return shapes.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder path, String dir) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) return;

        grid[i][j] = 0;  // mark visited
        path.append(dir);

        dfs(grid, i - 1, j, path, "U"); // up
        dfs(grid, i + 1, j, path, "D"); // down
        dfs(grid, i, j - 1, path, "L"); // left
        dfs(grid, i, j + 1, path, "R"); // right

        path.append("B"); // "B" = backtrack
    }
}
