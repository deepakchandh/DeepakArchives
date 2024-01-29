package com.java.LinkedList;

import java.util.List;

public class OddEvenList {


    public static ListNode oddEvenList(ListNode head) {

        if(head != null){
            ListNode odd = head, even = head.next, evenHead = even;

            while(even != null && even.next != null){
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);
        list1.next.next.next.next = new ListNode(5);
        ListNode res = oddEvenList(list1);
        while (res != null){
            System.out.print(res.val+" ");
            res = res.next;
        }


        System.out.println();
    }
}
