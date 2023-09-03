package com.java.practice;
//https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/submissions/
import java.util.*;

class Pair{
    int row;
    int col;
    Integer val;

    Pair(int row, int col , Integer val){
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class SmallestRangeCoveringKLists {


    public static int[] smallestRange(List<List<Integer>> nums) {
        //min pq
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        //max of each k element
        int max = 0;
        for(int i = 0 ; i < nums.size(); i++){
            max = Math.max(max, nums.get(i).get(0));
            pq.add(new Pair(i, 0, nums.get(i).get(0)));
        }
        int[] ans = new int[2];
        //range
        int range = Integer.MAX_VALUE;
        while(pq.size() > 0){
            Pair p = pq.remove();

            //updating range if smaller
            if(max - p.val < range){
                ans[0] = p.val;
                ans[1] = max;
                range = max - p.val;
            }

            //checking the validity
            if(p.col + 1 < nums.get(p.row).size()){
                max = Math.max(nums.get(p.row).get(p.col + 1), max);
                pq.add(new Pair(p.row, p.col + 1, nums.get(p.row).get(p.col + 1)));
            }else break;
        }
        return ans;
    }

    public static void main(String[] args) {

        ArrayList list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));
        int[] arr = smallestRange(list);
        System.out.println(arr[0]+"__"+arr[1]);

    }
}
