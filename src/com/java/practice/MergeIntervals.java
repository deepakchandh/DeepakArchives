//$Id$
package com.java.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

	 public static int[][] merge(int[][] intervals) {
	        List<int[]> res = new ArrayList<>();
	        
	        if(intervals.length == 0 || intervals == null) 
	            return res.toArray(new int[0][]);
	        //based on start timw
	        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
//	        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); -- same as above

	        int start = intervals[0][0];
	        int end = intervals[0][1];
	        
	        for(int[] i : intervals) {
	            if(i[0] <= end) {
	                end = Math.max(end, i[1]);
	            }
	            else {
	                res.add(new int[]{start, end});
	                start = i[0];
	                end = i[1];
	            }
	        }
	        res.add(new int[]{start, end});
	       return res.toArray(new int[0][]);
	    }
	 
	 public static void main(String[] args)
	    {
	        int [][]mat = {{2,6}, {1,3}, {8,10}, {15,18}
	        };
//			int [][]res = merge(mat);

//			for (int i=0; i< res.length;i++){
//				for(int j=0;j< res[0].length;i++)
//					System.out.print(res[i][j]+" ");
//				System.out.println();
//			}

			System.out.println(merge(mat).toString());
	    }
}
