package com.java.practice;

import java.util.*;

public class OpenLock {

    public static int openLock(String[] deadends, String target) {
        String start = "0000";

        Set<String> deadset = new HashSet<>(Arrays.asList(deadends));
        if (deadset.contains(start))
            return -1;

        // If the target is the start
        if (target.equals(start))
            return 0;

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);

        // Keep track of visited states (so we don't reprocess or go into deadends)
        Set<String> visited = new HashSet<>();
        visited.add(start);

        int moves = 0;
        while (!queue.isEmpty()){
            moves++;
            int size = queue.size();

            for(int i=0;i<size;i++){
                String current = queue.poll();
                List<String> neighbours = getNeighbors(current);
                for(String next: neighbours){
                    if (next.equals(target)) {
                        return moves;
                    }

                    if (!deadset.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return -1; // Unreachable
    }

    private static List<String> getNeighbors(String comb) {
        List<String> neighbors = new ArrayList<>();
        char[] arr = comb.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = arr[i];

            // Move wheel forward
            arr[i] = (char) ((original == '9') ? '0' : (original + 1));
            neighbors.add(new String(arr));

            // Move wheel backward
            arr[i] = (char) ((original == '0') ? '9' : (original - 1));
            neighbors.add(new String(arr));

            // Restore original
            arr[i] = original;
        }

        return neighbors;
    }

    public static void main(String[] args) {
        System.out.println(openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }
}
