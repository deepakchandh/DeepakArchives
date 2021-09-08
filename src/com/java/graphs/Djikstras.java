package com.java.graphs;
// https://www.youtube.com/watch?v=jbhuqIASjoM
//https://github.com/striver79/StriversGraphSeries/blob/main/djisktraJava

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
class Node implements Comparator<Node>{

    private int v;
    private int weight;

    // if the local variable and instance of variable is diff there is no need use This keyword
    public Node(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    Node() {}

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.weight < node2.weight)
            return -1;
        if (node1.weight > node2.weight)
            return 1;
        return 0;
    }


}

public class Djikstras {

    static void shortestPath(int s, ArrayList<ArrayList<Node>> adj, int N){
        int dist[] = new int[N];

        for(int i=0;i<N;i++)
            dist[i] = Integer.MAX_VALUE;

        dist[s] = 0;

        // you can also use Set<pair<int,int>>
        PriorityQueue<Node> pq = new PriorityQueue<>(N, new Node());
        pq.add(new Node(s, 0));

        while(pq.size() > 0){
            Node node = pq.poll();
            for(Node it: adj.get(node.getV())){
                if (node.getWeight() + it.getWeight() < dist[it.getV()]) {
                    dist[it.getV()] = node.getWeight() + it.getWeight();
                    pq.add(new Node(it.getV(), dist[it.getV()]));
                }
            }
        }

        for (int i = 0; i < N; i++)
        {
            System.out.print( dist[i] + " ");
        }

    }

    public static void main(String args[])
    {
        int n = 5;
        ArrayList<ArrayList<Node> > adj = new ArrayList<ArrayList<Node> >();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Node>());

        adj.get(0).add(new Node(1, 2));
        adj.get(1).add(new Node(0, 2));

        adj.get(1).add(new Node(2, 4));
        adj.get(2).add(new Node(1, 4));

        adj.get(0).add(new Node(3, 1));
        adj.get(3).add(new Node(0, 1));

        adj.get(3).add(new Node(2, 3));
        adj.get(2).add(new Node(3, 3));

        adj.get(1).add(new Node(4, 5));
        adj.get(4).add(new Node(1, 5));

        adj.get(2).add(new Node(4, 1));
        adj.get(4).add(new Node(2, 1));

        shortestPath(0, adj, n);

    }

}
