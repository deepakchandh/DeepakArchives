package com.java.graphs;

import java.util.*;

//https://leetcode.com/problems/cut-off-trees-for-golf-event/solutions/148729/logical-thinking-with-java-code-beats-98-04/


// also see rotten Oranges for better understanding
public class CutOffTreesForGolfEvent {

    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {

        List<int[]> treeHeights = getAllTreeHights(forest);
        /*

     lists.add(Arrays.asList(1,2,3));
     lists.add(Arrays.asList(0,0,4));
     lists.add(Arrays.asList(7,6,5));
    In above ex, totally we have 7 trees and ****apart from 1 we have 6 trees***
    treeHeights = {LinkedList@494}  size = 6
    0 = {int[3]@495} [0, 1, 2]
    1 = {int[3]@496} [0, 2, 3]
    2 = {int[3]@497} [1, 2, 4]
    3 = {int[3]@498} [2, 0, 7]
    4 = {int[3]@499} [2, 1, 6]
    5 = {int[3]@501} [2, 2, 5]
         */

        Collections.sort(treeHeights, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int minSteps = 0;
        int curX = 0, curY = 0;
        Iterator<int[]> iter = treeHeights.listIterator();

        while (iter.hasNext()) {
            int[] curTree = iter.next();
            int steps = getMinimumSteps(forest, curX, curY, curTree[0], curTree[1]);
            if (steps == -1) {
                return -1;
            }
            minSteps += steps;
            curX = curTree[0];
            curY = curTree[1];
            forest.get(curX).set(curY, 1); // if the tree is present & reachable. It'll be marked 1
        }
        return minSteps;
    }

    private int getMinimumSteps(List<List<Integer>> forest, int curX, int curY, int aimX, int aimY) {

        int minSteps = 0;
        int rows = forest.size(), cols = forest.get(0).size();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curX, curY});
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curCell = queue.poll();
                if (curCell[0] == aimX && curCell[1] == aimY) {
                    return minSteps;
                }
                for (int[] direction : directions) {
                    int nx = curCell[0] + direction[0];
                    int ny = curCell[1] + direction[1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

// List<List<>> to List<int[]>
    private List<int[]> getAllTreeHights(List<List<Integer>> forest) {
        List<int[]> treeHeights = new LinkedList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int tmpVal = forest.get(i).get(j);
                if (tmpVal > 1) {
                    int[] element = new int[3];
                    element[0] = i;
                    element[1] = j;
                    element[2] = tmpVal;
                    treeHeights.add(element);
                }
            }
        }
        return treeHeights;
    }

    public static void main(String[] args) {

        CutOffTreesForGolfEvent cutOffTreesForGolfEvent = new CutOffTreesForGolfEvent();
        List<List<Integer>> lists = new ArrayList<>();

        lists.add(Arrays.asList(1,2,3));
        lists.add(Arrays.asList(0,0,4));
        lists.add(Arrays.asList(7,6,5));
        System.out.println(cutOffTreesForGolfEvent.cutOffTree(lists));

    }
}
