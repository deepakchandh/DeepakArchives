package com.java.LinkedList;
//https://www.geeksforgeeks.org/merge-two-sorted-linked-lists-such-that-merged-list-is-in-reverse-order/
public class SortedLLInReverse {

    // https://www.geeksforgeeks.org/dsa/reverse-a-linked-list/
    public ListNode reverseList(ListNode head) {

        ListNode prev = null, curr = head, next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return prev;
    }


    Node head;  // head of list
    static Node a, b;

    /* Node Class */
    static class Node {

        int data;
        Node next;

        // Constructor to create a new node
        Node(int d) {
            data = d;
            next = null;
        }
    }

    void printlist(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    Node sortedmerge(Node node1, Node node2) {

        // if both the nodes are null
        if (node1 == null && node2 == null) {
            return null;
        }

        // resultant node
        Node res = null;

        // if both of them have nodes present traverse them
        while (node1 != null && node2 != null) {

            // Now compare both nodes current data
            if (node1.data <= node2.data) {
                Node temp = node1.next;
                node1.next = res;
                res = node1;
                node1 = temp;
            } else {
                Node temp = node2.next;
                node2.next = res;
                res = node2;
                node2 = temp;
            }
        }

        // If second list reached end, but first list has
        // nodes. Add remaining nodes of first list at the
        // front of result list
        while (node1 != null) {
            Node temp = node1.next;
            node1.next = res;
            res = node1;
            node1 = temp;
        }

        // If first list reached end, but second list has
        // node. Add remaining nodes of first list at the
        // front of result list
        while (node2 != null) {
            Node temp = node2.next;
            node2.next = res;
            res = node2;
            node2 = temp;
        }

        return res;

    }

    public static void main(String[] args) {

        SortedLLInReverse list = new SortedLLInReverse();
        Node result = null;

        /*Let us create two sorted linked lists to test
         the above functions. Created lists shall be
         a: 5->10->15
         b: 2->3->20*/
        list.a = new Node(5);
        list.a.next = new Node(10);
        list.a.next.next = new Node(15);

        list.b = new Node(2);
        list.b.next = new Node(3);
        list.b.next.next = new Node(20);

        System.out.println("List a before merge :");
        list.printlist(a);
        System.out.println("");
        System.out.println("List b before merge :");
        list.printlist(b);

        // merge two sorted linkedlist in decreasing order
        result = list.sortedmerge(a, b);
        System.out.println("");
        System.out.println("Merged linked list : ");
        list.printlist(result);

    }
}
