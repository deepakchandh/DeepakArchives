package com.java.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {

    public static int[] smallestRange(List<List<Integer>> nums) {
        // Min heap stores: (value, whichList, indexInList)
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> Integer.compare(a[0], b[0]) );

        int currentMax = Integer.MIN_VALUE;

        // 1) initialize pq with first element from each list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            currentMax = Math.max(currentMax, val);
        }

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

        // 2) slide the window until one list runs out
        while (pq.size() == nums.size()) {
            int[] curr = pq.poll();
            int currentMin = curr[0];
            int listIdx = curr[1];
            int eleIdx = curr[2];

            // Update the best range
            if (currentMax - currentMin < rangeEnd - rangeStart) {
                rangeStart = currentMin;
                rangeEnd = currentMax;
            }

            // Move pointer in the current list
            if (eleIdx + 1 < nums.get(listIdx).size()) {
                int nextVal = nums.get(listIdx).get(eleIdx + 1);
                pq.offer(new int[]{nextVal, listIdx, eleIdx + 1});
                currentMax = Math.max(currentMax, nextVal);
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }

    public static void main(String[] args) {

        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));

        int[] result = smallestRange(nums);
        System.out.println("Smallest Range: [" + result[0] + ", " + result[1] + "]");
    }
}
