package com.java.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://leetcode.com/problems/insert-interval/

public class InsertIntervals {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            // Case 1: No overlap, and current interval is completely before newInterval
            if (newInterval == null || interval[1] < newInterval[0]) {
                res.add(interval);
            }
            // Case 2: No overlap, and current interval is completely after newInterval
            else if (interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            }
            // Overlap — merge the intervals
            else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }

        if (newInterval != null)
            res.add(newInterval);

        return res.toArray(new int[res.size()][]);


    }

    public static void main(String[] args) {

        int[][] intervals = { {1, 2}, {3,5}, {9, 10} };
        int[] newInterval = {6, 7};

        int[][] result = insert(intervals, newInterval);

        System.out.print("Merged Intervals: ");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }

    }
}
