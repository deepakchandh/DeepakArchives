package com.java.arrays;

import java.util.ArrayList;
import java.util.List;
// #Arrays

// https://leetcode.com/problems/pascals-triangle/
public class PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int row =1;row<=numRows;row++){
            res.add(generateRow(row));
        }
        return res;
    }

    private static List<Integer> generateRow(int row) {
        List<Integer> rowAns = new ArrayList<>();
        long ans = 1;
        rowAns.add(1);

        for(int col =1; col < row; col++){
            ans = ans * (row - col);
            ans = ans/col;
            rowAns.add((int) ans);
        }
        return rowAns;
    }


    /*
Here’s how we build each row directly without previous rows:

✅ Step 1. Start each row with 1.

✅ Step 2. For each next element in the row:

Multiply the previous number by (row - col)
Divide by col
✅ Step 3. Repeat until you’ve filled the row.

✅ Step 4. Add that row to the result.
*/
    public static void main(String[] args) {
        generate(5);
    }
}
