package com.java.LinkedList;

public class Merge2Lists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode prev = result;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                prev.next = l1;
                l1=l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        if (l1 != null) {
            prev.next = l1;
        }
        if (l2 != null) {
            prev.next = l2;
        }
        return result.next;

    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(7);
        l1.next.next = new ListNode(10);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(5);
        l2.next.next.next = new ListNode(8);
        l2.next.next.next.next = new ListNode(10);

        Merge2Lists merge2Lists = new Merge2Lists();
        merge2Lists.mergeTwoLists(l1, l2);
    }
}
