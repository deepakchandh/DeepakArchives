package com.java.practice;

public class GasStation {


    /*

The idea is simple.

Whenever the sum is negative, reset it and let the car start from next point.
In the mean time, add up all of the left gas to total. If it's negative finally, return -1 since it's impossible to finish.
If it's non-negative, return the last point saved in res;
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int size=gas.length;
        int sum=0;
        int res=0;
        int total=0;
        for(int i=0; i<size; ++i){
            sum+=gas[i]-cost[i];
            if(sum<0){
                total+=sum;
                sum=0;
                res=i+1;
            }
        }
        total+=sum;
        return total<0?-1:res;


    }

    public static void main(String[] args) {

        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
