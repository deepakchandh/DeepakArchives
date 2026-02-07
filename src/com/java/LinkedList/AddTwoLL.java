//$Id$
package com.java.LinkedList;

public class AddTwoLL {

	/*

	// We use a dummy node to avoid special-case logic when the head itself needs to be removed.
        // with dummy it is safe to access previous node..
	 */
	// We use a dummy node to avoid special-case logic when the head itself needs to be removed.
        // with dummy it is safe to access previous node..
        /*
When Should You Use a Dummy Node?

Use it when:

Nodes may be removed

Head may change

You want cleaner pointer logic

Common problems:

Remove elements

Remove Nth from end

Partition list

Reverse sublist
*/


	static ListNode reverse(ListNode node, int k)
	{
		ListNode prev = null;
		ListNode current = node;
		ListNode next = null;
		while (current != null && k>0) {
			--k;
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		ListNode jj =new ListNode(0);
		while(node!=null){
			ListNode jListNode =  new ListNode(node.val);
			if (jj == null) {
				jj = jListNode;
			}else{
				ListNode cur =  jj;
				while(cur.next != null){
					cur = cur.next;
				}
				cur = cur.next;
			}
			node = node.next;
		}
		while(current!=null){
			ListNode jListNode =  new ListNode(current.val);
			if (jj == null) {
				jj = jListNode;
			}else{
				ListNode cur =  jj;
				while(cur.next != null){
					cur = cur.next;
				}
				cur = cur.next;
			}
			current = current.next;
		}

		/* while(node!=null){
        	ListNode jListNode = jj;
            jj = new ListNode(node.val);
            jj.next = jListNode;
            node = node.next;
        }

        while(current != null){
        	ListNode jListNode = jj;
            jj = new ListNode(current.val);
            jj.next = jListNode;
            current = current.next;
        }  */
		return jj;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while(p!=null || q!=null){
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null) p = p.next;
			if (q != null) q = q.next;
		}


		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}


	public static void main(String[] args) {
		System.out.println("started");
		ListNode list1 = new ListNode(5);
		list1.next = new ListNode(6);
		list1.next.next = new ListNode(3);
//		list1.next.next.next = new ListNode(4);
//		list1.next.next.next.next = new ListNode(5);

//		System.out.println(reverse(list1, 2));
		ListNode list2 = new ListNode(8);
		list2.next = new ListNode(4);
		list2.next.next = new ListNode(2);

		System.out.println(addTwoNumbers(list1, list2));;


	}
	/*
	
 
class Solution {
    
   static ListNode reverse(ListNode node, int k)
    {
        ListNode prev = null;
        ListNode current = node;
        ListNode next = null;
        while (current != null && k>0) {
            --k;
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
         node = prev;
       ListNode kk = new ListNode(0);
        ListNode jj = kk;
        while(node!=null){
        	ListNode jListNode = jj;
            jj = new ListNode(node.val);
            jj.next = jListNode;
            node = node.next;
        }
      
        while(current != null){
        	ListNode jListNode = jj;
            jj = new ListNode(current.val);
            jj.next = jListNode;
            current = current.next;
        }  
        return jj.next;
    }
  static ListNode reverse(ListNode node, int k)
    {
        ListNode prev = null;
        ListNode current = node;
        ListNode next = null;
        while (current != null && k>0) {
            --k;
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
         node = prev;
       ListNode kk = new ListNode(0);
        ListNode jj = kk;
        while(node!=null){
            jj = new ListNode(node.val);
            jj = jj.next;
            node = node.next;
        }
      
        while(current != null){
            jj = new ListNode(current.val);
            jj = jj.next;
            current = current.next;
        }  
        return kk.next;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
       
        ListNode dummyHead = new ListNode(0);
        dummyHead = reverse(head, k);
        return dummyHead;
    }
}
	 */
	
	
}
