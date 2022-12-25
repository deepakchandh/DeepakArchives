package com.java.trees;

//https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/

public class PathSum {

    Node2 root;
    boolean hasPathSum(Node2 node, int sum)
    {
        if (root == null)
            return false;
        boolean ans = false;
        int subSum = sum - node.data;
        if (subSum == 0 && node.left == null && node.right == null)
            return (ans = true);

        if (node.left != null)
            ans = ans || hasPathSum(node.left, subSum);

        if (node.right != null)
            ans = ans || hasPathSum(node.right, subSum);

        return (ans);
    }

    // Driver's Code
    public static void main(String args[])
    {
        int sum = 21;

        /* Constructed binary tree is
              10
             /  \
           8     2
          / \   /
         3   5 2
        */
        PathSum tree = new PathSum();
        tree.root = new Node2(10);
        tree.root.left = new Node2(8);
        tree.root.right = new Node2(2);
        tree.root.left.left = new Node2(3);
        tree.root.left.right = new Node2(5);
        tree.root.right.left = new Node2(2);

        // Function call
        if (tree.hasPathSum(tree.root, sum))
            System.out.println("There is a root to leaf path with sum "+ sum);
        else
            System.out.println("There is no root to leaf path with sum " + sum);
    }

}

class Node2 {
    int data;
    Node2 left, right;

    Node2(int item)
    {
        data = item;
        left = right = null;
    }
}
