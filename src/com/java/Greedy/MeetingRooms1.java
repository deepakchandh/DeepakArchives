package com.java.Greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MeetingRooms1 {

    public static boolean canAttendMeetings(List<Interval> intervals) {

        Collections.sort(intervals,  Comparator.comparingInt(i->i.start));

        for(int i=1;i<intervals.size();i++){
            Interval i1 = intervals.get(i - 1);
            Interval i2 = intervals.get(i);

            if(i1.end > i2.start){
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)
        );

        boolean canAttendAll = canAttendMeetings(intervals);
        System.out.println("Can attend all meetings: " + canAttendAll);

    }
}


/*
int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

         Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
 */