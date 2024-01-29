package com.java.practice;

public class GasStation {


    /*

The idea is simple.

Whenever the sum is negative, reset it and let the car start from next point.
In the mean time, add up all of the left gas to total. If it's negative finally, return -1 since it's impossible to finish.
If it's non-negative, return the last point saved in res;
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int totalTank = 0;
        int currTank = 0;
        int startPoint = 0;

        // Iterate through the gas stations
        for (int i = 0; i < gas.length; i++) {
            // Update totalTank by calculating the difference between gas and cost for the current station
            totalTank += gas[i] - cost[i];

            // Update currTank similarly
            currTank += gas[i] - cost[i];

            // If currTank becomes negative, reset the starting point to the next station and reset currTank to 0
            if (currTank < 0) {
                startPoint = i + 1;
                currTank = 0;
            }
        }

        // If the totalTank is non-negative, a circuit can be completed, return the starting point; otherwise, return -1
        return totalTank >= 0 ? startPoint : -1;
    }

    public static void main(String[] args) {

        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
