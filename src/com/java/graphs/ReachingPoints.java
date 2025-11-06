package com.java.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ReachingPoints {


    /*
    Input:  sx = 1, sy = 1, tx = 3, ty = 5
Steps:
(tx, ty) = (3, 5)
→ ty > tx → ty = ty % tx = 5 % 3 = 2 → (3, 2)
→ tx > ty → tx = tx % ty = 3 % 2 = 1 → (1, 2)
→ ty > tx → ty = ty % tx = 2 % 1 = 0 → loop stops

Now tx == sx == 1, and (ty - sy) % tx == (2 - 1) % 1 == 0 → ✅ true
     */

    public static boolean reachingPointsMath(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == sx && ty == sy) {
                return true;
            }

            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                } else {
                    // ty == sy → can only move in x direction by adding ty
                    return (tx - sx) % ty == 0;
                }
            } else { // ty >= tx
                if (tx > sx) {
                    ty %= tx;
                } else {
                    // tx == sx → can only move in y direction by adding tx
                    return (ty - sy) % tx == 0;
                }
            }
        }

        return false;
    }

    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];

            if (x == tx && y == ty)
                return true; // Found target
            if (x > tx || y > ty)
                continue;      // Overshoot, skip

            // Add next possible moves
            queue.offer(new int[]{x + y, y});
            queue.offer(new int[]{x, x + y});
        }

        return false;
    }

    public static void main(String[] args) {
        int sx = 1, sy = 1;
        int tx = 2, ty = 2;

        boolean result = reachingPointsMath(sx, sy, tx, ty);
        System.out.println("Can reach (" + tx + "," + ty + ") from (" + sx + "," + sy + ")? " + result);
    }
}
