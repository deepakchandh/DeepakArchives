package com.java.practice;


import java.util.*;

class Job {
    int start;
    int end;
    int cpuLoad;

    public Job(int start, int end, int cpuLoad) {
        this.start = start;
        this.end = end;
        this.cpuLoad = cpuLoad;
    }
};

public class MaxCpuLoadForGivenJobs {
    public static int findMaxCPULoad(List<Job> jobs)
    {
        // sort the jobs by start time
        Collections.sort(jobs, (a, b) -> Integer.compare(a.start, b.start));

        int maxCPULoad = 0;
        int currentCPULoad = 0;
        // based on the endTimes we have minHeap
        PriorityQueue<Job> minHeap = new PriorityQueue<>(jobs.size(), (a, b) -> Integer.compare(a.end, b.end));

        for (Job job : jobs) {
            // remove all jobs that have ended
            while (!minHeap.isEmpty() && job.start > minHeap.peek().end)
                currentCPULoad -= minHeap.poll().cpuLoad;

            // add the current job into the minHeap
            minHeap.offer(job);
            currentCPULoad += job.cpuLoad;
            maxCPULoad = Math.max(maxCPULoad, currentCPULoad);
        }
        return maxCPULoad;
    }


    public static void main(String[] args) {

        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3),
                new Job(2, 5, 4),
                new Job(7, 9, 6)));
//        System.out.println("Maximum CPU load at any time: " +
//                MaxCpuLoadForGivenJobs.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 5, 10),
                                                 new Job(2, 4, 11),
                                                 new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " + MaxCpuLoadForGivenJobs.findMaxCPULoad(input));

    }
}
