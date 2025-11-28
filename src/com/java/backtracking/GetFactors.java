package com.java.backtracking;

import java.util.*;

public class GetFactors {

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3) return res; // no valid factors for 1,2,3
        dfs(n, 2, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int n, int start, List<Integer> cur, List<List<Integer>> res) {
        // try factors from 'start' to sqrt(n)
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                // add the factor pair [ ... , i, n/i ] as one valid combination
                cur.add(i);
                cur.add(n / i);
                res.add(new ArrayList<>(cur));
                // remove the last added (n/i) so we can recurse with only i in cur
                cur.remove(cur.size() - 1);

                // explore deeper factorizations of n/i starting from i
                dfs(n / i, i, cur, res);

                // backtrack remove the i
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getFactors(12)); // [[2,6], [2,2,3], [3,4]]
//        System.out.println(getFactors(16));
    }
}
