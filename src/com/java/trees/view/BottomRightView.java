package com.java.trees.view;

import java.util.*;

public class BottomRightView {

    static class Node {
        int data;
        Node left, right;

        Node(int data)
        {
            this.data = data;
            this.left = this.right = null;
        }
    }

    // create and returns a new node
    static Node newNode(int val)
    {
        Node temp = new Node(val);
        return temp;
    }

    static void bottomRightView(Node root)
    {

        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                root = q.poll();
                while (root.right != null) {
                    if (root.left != null) {
                        q.add(root.left);
                    }
                    root = root.right;
                }
                if (root.left != null) {
                    q.add(root.left);
                }
            }
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args)
    {
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.right.left = newNode(5);
        root.right.left.right = newNode(6);

        bottomRightView(root);
    }

}
