package com.java.practice;

import java.util.Arrays;

public class MaximumUnits {

    public  static int maximumUnits(int[][] boxTypes, int truckSize) {
        // Sort box types by units per box in descending order
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int totalUnits = 0;

        for (int[] box : boxTypes) {
            int numberOfBoxes = box[0];
            int unitsPerBox = box[1];

            // Take as many boxes as possible (up to remaining capacity)
            int take = Math.min(truckSize, numberOfBoxes);
            totalUnits += take * unitsPerBox;
            truckSize -= take;

            // If truck is full, we’re done
            if (truckSize == 0) {
                break;
            }
        }

        return totalUnits;
    }
    public static void main(String[] args) {
        int[][] boxTypes = {
                {2, 2},
                {1, 3},
                {3, 1}
        };
        int truckSize = 4;

        int result = maximumUnits(boxTypes, truckSize);
        System.out.println("Maximum units on truck: " + result);
    }
}
