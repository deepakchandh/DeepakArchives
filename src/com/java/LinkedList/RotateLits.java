//$Id$
package com.java.LinkedList;

public class RotateLits {


	public static ListNode rotateRight(ListNode head, int n) {
		if(head == null || head.next ==null)
            return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast=dummy,slow=dummy;
        
        int len =0;
        while(fast.next!=null){
        	 fast = fast.next;
        	 len++;
        }
        
        for(int j=len-(n%len); j>0;j--){
            slow = slow.next;
        }
        
        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;
        
        return dummy.next;
	}
	
	
	public static void main(String... args){
		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(2);
		list1.next.next = new ListNode(3);
		list1.next.next.next = new ListNode(4);
		list1.next.next.next.next = new ListNode(5);
		ListNode list2 = rotateRight(list1, 2);
		
		while(list2.next!=null){
			System.out.print(list2.val);
			list2 = list2.next;
		}
		
	}
}
