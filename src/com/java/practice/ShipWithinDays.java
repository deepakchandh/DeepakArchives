package com.java.practice;

public class ShipWithinDays {

    public static int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);    // heaviest single package
            right += w;                   // sum of all weights
        }

        while(left < right){
            int mid = left + (right - left) / 2;
            if(canShip(weights, days, mid)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canShip(int[] weights, int days, int capacity) {
        int dayCount = 1;
        int currentLoad = 0;

        for(int w: weights){
            if (currentLoad + w <= capacity) {
                currentLoad += w;
            }
            else {
                // start next day
                dayCount++;
                currentLoad = w;
                if (dayCount > days) {
                    return false;
                }
            }

        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}
