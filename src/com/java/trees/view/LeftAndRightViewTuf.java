package com.java.trees.view;


import java.util.ArrayList;
import java.util.List;

public class LeftAndRightViewTuf {


    public static List<Integer> leftsideView(Node root) {
        List<Integer> res = new ArrayList<>();
        recursionLeft(root, 0, res);
        return res;
    }


    private static void recursionLeft(Node root, int level, List<Integer> res) {
        if (root == null)
            return;

        if (res.size() == level){
            res.add(root.data);
        }

        recursionLeft(root.left, level + 1, res);
        recursionLeft(root.right, level + 1, res);
    }


    private static void rightsideView(Node root, int level, List<Integer> res) {
        if(root == null)
            return;
        if(res.size() == level){
            res.add(root.data);
        }

        rightsideView(root.right, level+1, res);
        rightsideView(root.left, level+1, res);
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(10);
        root.left.left.right = new Node(5);
        root.left.left.right.right = new Node(6);
        root.right = new Node(3);
        root.right.right = new Node(10);
        root.right.left = new Node(9);

        List<Integer> leftView = leftsideView(root);

        // Print the result for Left View
        System.out.print("Left View Traversal: ");
        for (int node : leftView) {
            System.out.print(node + " ");
        }

    }
}
