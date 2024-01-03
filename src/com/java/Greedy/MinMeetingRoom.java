package com.java.Greedy;

import java.util.*;

//https://leetcode.com/problems/meeting-rooms-ii/description/
public class MinMeetingRoom {

    public static int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;

        int startTime[] = new int[len];
        int endTime[] = new int[len];
        int i;
        for(i =0;i<len;i++){
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        i = 0;
        int j =0 ;
        int roomCount=0;

        while(i<len){
            if(startTime[i]<endTime[j]){
                roomCount++;
                i++;
            }
            else {
                i++;
                j++;
            }
        }
        return roomCount;
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][] {{0,30}, {5,10}, {15,20}}));

    }
}
