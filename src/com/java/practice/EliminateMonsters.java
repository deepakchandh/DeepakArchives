package com.java.practice;

import java.util.Arrays;

public class EliminateMonsters {

    public static int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        double[] arrival = new double[n];

        // compute time each monster reaches the city
        for (int i = 0; i < n; i++) {
            arrival[i] = (double) dist[i] / speed[i];
        }

        Arrays.sort(arrival); // earliest arrivals first

        for (int i = 0; i < n; i++) {
            // i = minute when we fire the i-th shot
            if (arrival[i] <= i) {
                return i; // monster i reached city before we could shoot
            }
        }

        return n; // eliminated all
    }

    public static void main(String[] args) {
        System.out.println(eliminateMaximum(new int[]{3,2,4}, new int[]{5,3,2}));
    }
}
