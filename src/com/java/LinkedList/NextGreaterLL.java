//$Id$
package com.java.LinkedList;

import java.util.*;

public class NextGreaterLL {

	public static int[] nextLargerNodes(ListNode head) {

		ArrayList<Integer> arrayList = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next)
            arrayList.add(node.val);
        int[] res = new int[arrayList.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrayList.size(); ++i) {
            while (!stack.isEmpty() && arrayList.get(stack.peek()) < arrayList.get(i))
                res[stack.pop()] = arrayList.get(i);
            stack.push(i);
        }
        return res;
	}
	
	public static void main(String... args){
		
		ListNode list1 = new ListNode(2);
		list1.next = new ListNode(1);
		list1.next.next = new ListNode(5);
//		list1.next.next.next = new ListNode(0);
//		list1.next.next.next.next = new ListNode(5);
		
		int[] arr = nextLargerNodes(list1);
		System.out.println(Arrays.toString(arr));
	}


}
