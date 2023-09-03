package com.java.graphs;

import java.util.*;
//https://www.geeksforgeeks.org/snake-ladder-problem-2/ - BFS
public class SnakesAndLadder {

    static class Qentry {
        int v; // Vertex number
        int dist; // Distance of this vertex from source
    }

    static int getMinDiceThrows(int move[], int n)
    {
        int visited[] = new int[n];
        Queue<Qentry> qentries = new LinkedList<>();
        Qentry qMeta = new Qentry();
        qMeta.v = 0;
        qMeta.dist = 0;

        visited[0] = 1;
        qentries.add(qMeta);

        while (!qentries.isEmpty()) {
            qMeta = qentries.remove();
            int v = qMeta.v;

            if (v == n - 1)
                break;

            for (int j = v + 1; j <= (v + 6) && j < n; ++j) {
                if (visited[j] == 0) {
                    Qentry subMetaQue = new Qentry();
                    subMetaQue.dist = (qMeta.dist + 1);
                    visited[j] = 1;

                    if (move[j] != -1)
                        subMetaQue.v = move[j];
                    else
                        subMetaQue.v = j;
                    qentries.add(subMetaQue);
                }
            }
        }
        return qMeta.dist;
    }

    public static void main(String[] args) {

        int N = 30;
        int moves[] = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println("Min Dice throws required is "
                + getMinDiceThrows(moves, N));

    }
}
