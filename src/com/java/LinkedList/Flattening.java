//$Id$
package com.java.LinkedList;

public class Flattening {
	Node head; // head of list

	/* Linked list Node*/
	class Node
	{
		int data;
		Node right, down;
		Node(int data)
		{
			this.data = data;
			right = null;
			down = null;
		}
	}

	// An utility function to merge two sorted linked lists
	Node merge(Node a, Node b)
	{
		if (a == null)	 return b;

		if (b == null)	 return a;

		Node result;

		if (a.data < b.data)
		{
			result = a;
			result.down = merge(a.down, b);
		}

		else
		{
			result = b;
			result.down = merge(a, b.down);
		}

		result.right = null;
		return result;
	}
	
	/* Let us create the following linked list
	5 -> 10 -> 19 -> 28
	| 	 |	 	|	 |
	V 	 V	 	V	 V
	7 	20 	 	22   35
	|		 	|	 |
	V		 	V	 V
	8		 	50  40
	|			 	|
	V			 	V
	30			 	45
 */

	Node flatten(Node root)
	{
		if (root == null || root.right == null)
			return root;
		root.right = flatten(root.right);

		root = merge(root, root.right); // here the main logic is to send the node, node.right and sort them. 
		//Important: sorted nodes will be appended downward. 
		// like 19-> (down) 22 -> down 28 -> down 35 -> down 40 -> down 45

		return root;
	}

	/* Utility function to insert a node at beginning of the
	linked list */
	Node push(Node head_ref, int data)
	{
		Node new_node = new Node(data);
		new_node.down = head_ref;
		head_ref = new_node;
		return head_ref;
	}

	void printList()
	{
		Node temp = head;
		while (temp != null)
		{
			System.out.print(temp.data + " ");
			temp = temp.down;
		}
		System.out.println();
	}

	/* Driver program to test above functions */
	public static void main(String args[])
	{
		Flattening L = new Flattening();

		/* Let us create the following linked list
			5 -> 10 -> 19 -> 28
			| 	 |	 	|	 |
			V 	 V	 	V	 V
			7 	20 	 	22   35
			|		 	|	 |
			V		 	V	 V
			8		 	50  40
			|			 	|
			V			 	V
			30			 	45
		 */

		L.head = L.push(L.head, 30);
		L.head = L.push(L.head, 8);
		L.head = L.push(L.head, 7);
		L.head = L.push(L.head, 5);

		L.head.right = L.push(L.head.right, 20);
		L.head.right = L.push(L.head.right, 10);

		L.head.right.right = L.push(L.head.right.right, 50);
		L.head.right.right = L.push(L.head.right.right, 22);
		L.head.right.right = L.push(L.head.right.right, 19);

		L.head.right.right.right = L.push(L.head.right.right.right, 45);
		L.head.right.right.right = L.push(L.head.right.right.right, 40);
		L.head.right.right.right = L.push(L.head.right.right.right, 35);
		L.head.right.right.right = L.push(L.head.right.right.right, 28);

		// flatten the list
		L.head = L.flatten(L.head);

		L.printList();
	}
}