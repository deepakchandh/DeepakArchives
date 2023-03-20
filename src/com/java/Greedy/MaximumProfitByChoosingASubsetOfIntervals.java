package com.java.Greedy;

import java.util.*;

// https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
public class MaximumProfitByChoosingASubsetOfIntervals {

    // Leetcode Logic
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        //https://leetcode.com/problems/maximum-profit-in-job-scheduling/solutions/409229/java-dp-with-treemap-20-lines-o-nlogn/?orderBy=most_votes&page=2
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0;

        for (int[] curr: jobs) {
            Integer prev = map.floorKey(curr[0]);
            int prevSum = prev==null?0:map.get(prev);
            ans = Math.max(ans, prevSum+curr[2]);
            map.put(curr[1], ans);
        }
        return ans;
    }



    public static int maximum_profit(int n, int[][] intervals) {
        Arrays.sort(intervals,(a, b) -> (a[0]-b[0]));
        int[] dp = new int[n];
        for(int i=n-1;i>=0;i--) {
            int idx = searchGreaterOrEqualTo(intervals,intervals[i][1]);
            if(idx>=dp.length) {
                dp[i]=intervals[i][2];
            } else {
                dp[i] = intervals[i][2]+dp[idx];
            }
            if(i!=n-1) dp[i] = Math.max(dp[i],dp[i+1]);
        }
        return dp[0];
    }

    public static int searchGreaterOrEqualTo(int[][] a,int val) {
        int lo=0,hi=a.length-1,ans=a.length;
        while(lo<=hi) {
            int mid = lo+(hi-lo)/2;
            if(a[mid][0]<val) {
                lo=mid+1;
            } else {
                hi=mid-1;
                ans = Math.min(ans,mid);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] startTime = new int[]{1,3,3, 2};
        int[] endTime = new int[]{3,5,6, 4};
        int[] profit = new int[]{50,40,70, 10};

        System.out.println(jobScheduling(startTime, endTime, profit));
    }
}
