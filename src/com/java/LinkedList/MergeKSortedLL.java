package com.java.LinkedList;

import java.util.PriorityQueue;


//https://practice.geeksforgeeks.org/problems/merge-k-sorted-linked-lists/ 1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
class Node
{
    int data;
    Node next;

    Node(int key)
    {
        data = key;
        next = null;
    }
}
public class MergeKSortedLL {

    public static Node mergeKList(Node arr[], int last)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i=0; i<arr.length; i++) {
            Node node = arr[i];

            while (node != null) {
                pq.add(node.data);
                node = node.next;
            }
        }

        Node dummy = new Node(0);
        Node head = dummy;

        while (!pq.isEmpty()) {
            dummy.next = new Node(pq.poll());
            dummy = dummy.next;
        }

        return head.next;
    }


}
