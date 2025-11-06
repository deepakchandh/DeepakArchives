package com.java.Greedy;

import java.util.*;

/*
private static void backtrack(String s, int index, List<String> path, List<String> result) {
        // If we have 4 parts and used all characters -> valid IP
        if (path.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }

        // Try parts of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) break;

            String segment = s.substring(index, index + len);

            // Leading zero check
            if (segment.length() > 1 && segment.startsWith("0")) continue;

            // Range check
            int val = Integer.parseInt(segment);
            if (val > 255) continue;

            path.add(segment);
            backtrack(s, index + len, path, result);
            path.remove(path.size() - 1);
        }
    }
 */


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
