package com.java.practice;

import java.util.*;

public class KthSmallestElement {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEqual(matrix, mid);
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int countLessEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1;
        int col = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                // All elements in current row up to 'row' in this column are <= target
                count += (row + 1);
                col++;
            } else {
                // current element too big, move up
                row--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        KthSmallestElement solver = new KthSmallestElement();

        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;

        int ans = solver.kthSmallest(matrix, k);
        System.out.println("The " + k + "th smallest element is: " + ans);
    }
}
