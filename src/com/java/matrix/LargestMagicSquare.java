package com.java.matrix;

public class LargestMagicSquare {

    public static int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // prefix sums
        int[][] rowSum = new int[m][n+1];  // rowSum[i][j] = sum of grid[i][0..j-1]
        int[][] colSum = new int[m+1][n];  // colSum[i][j] = sum of grid[0..i-1][j]

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j+1] = rowSum[i][j] + grid[i][j];
                colSum[i+1][j] = colSum[i][j] + grid[i][j];
            }
        }

        int maxK = Math.min(m, n);
        for (int k = maxK; k >= 1; k--) {  // note: 1 always works
            for (int r = 0; r + k - 1 < m; r++) {
                for (int c = 0; c + k - 1 < n; c++) {
                    if (isMagic(grid, rowSum, colSum, r, c, k)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private static boolean isMagic(int[][] grid, int[][] rowSum, int[][] colSum,
                            int r, int c, int k) {
        // target sum: first row of block
        int sum = rowSum[r][c + k] - rowSum[r][c];

        // check all rows
        for (int i = r; i < r + k; i++) {
            int s = rowSum[i][c + k] - rowSum[i][c];
            if (s != sum) {
                return false;
            }
        }

        // check all columns
        for (int j = c; j < c + k; j++) {
            int s = colSum[r + k][j] - colSum[r][j];
            if (s != sum) {
                return false;
            }
        }

        // main diagonal
        int sDiag = 0;
        for (int d = 0; d < k; d++) {
            sDiag += grid[r + d][c + d];
        }
        if (sDiag != sum) {
            return false;
        }

        // anti-diagonal
        int sAnti = 0;
        for (int d = 0; d < k; d++) {
            sAnti += grid[r + d][c + (k - 1) - d];
        }
        if (sAnti != sum) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {7, 1, 4, 5, 6},
                {2, 5, 1, 6, 4},
                {1, 5, 4, 3, 2},
                {1, 2, 7, 3, 4}
        };

        int result = largestMagicSquare(grid);
        System.out.println("Largest Magic Square Size: " + result);
    }
}
