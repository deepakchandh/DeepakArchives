package com.java.LinkedList;

import java.util.*;

class node {
    int data;
    node next;
    node(int d) {
        data = d;
        next = null;
    }
}
public class RemoveLoopInLL {

    static void loopDetDelBrute(node head) {
        Set< node > set = new HashSet < > ();
        node prev = null;
        node current = head;
        while (current != null) {
            if (set.contains(current)) // loop detected
            {
                prev.next = null; // loop removal
                return;
            }
            set.add(current);

            prev = current;
            current = current.next;
        }

    }
    // for checking if our approach is correct
    public static boolean FLD(node head) {
        node slow = head;
        node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static void main(String args[]) {
        node head = new node(15);
        head.next = new node(10);
        head.next.next = new node(12);
        head.next.next.next = new node(20);
        head.next.next.next.next = head.next;
        loopDetDelBrute(head);
        if (FLD(head)) {
            System.out.println("Loop Detected");
        } else System.out.println("Loop not detected");
    }
}
