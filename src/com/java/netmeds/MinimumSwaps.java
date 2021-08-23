//$Id$
package com.java.netmeds;

import java.util.*;

///https://www.hackerearth.com/submission/59146040/
public class MinimumSwaps {
	
	public static void main(String[] args)  {
        int n = 4;
        long[] arr={-1,2,2,3};
        System.out.println(Solve(n, arr));
		
    }
    static long Solve(int N, long[] A){
       // Write your code here
        if (N == 1)
            return -1;
        int[] candidate0 = new int[N];
        int[] candidate1 = new int[N];
        Map<Long, Integer> freqMap = new HashMap<>();
        for (int i=0; i<N; i++) {
            candidate0[i] = distance(i, 0, N);// 0,1,2,1
            candidate1[i] = distance(i, 1, N);//1,0,1,2
            freqMap.put(A[i], freqMap.getOrDefault(A[i], 0) + 1);// freq calculator
        }
        long minSwap = Long.MAX_VALUE;
        for (int i=0; i<N; i++) {
            if (freqMap.get(A[i]) == 1)
                continue;
            for (int j=i+1; j<N; j++) {
                if (A[i] != A[j])
                    continue;
                if (candidate0[i] + candidate1[j] < minSwap)
                    minSwap = candidate0[i] + candidate1[j];
                if (candidate1[i] + candidate0[j] < minSwap)
                    minSwap = candidate1[i] + candidate0[j];
            }
        }
        return minSwap == Long.MAX_VALUE ? -1 : minSwap;
    }
 
    static int distance(int idx, int dest, int N) {
        int d1 = idx >= dest ? idx - dest : dest - idx;
        return Math.min(d1, N-idx+dest);
    }

}
