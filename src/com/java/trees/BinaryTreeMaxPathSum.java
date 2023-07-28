package com.java.trees;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
public class BinaryTreeMaxPathSum {

    static int sum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        // int sum =0;
        helper(root);
        return sum;
    }

    static int helper(TreeNode root){
        if(root == null)
            return 0;

        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        sum = Math.max(sum, root.val + left + right);

        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(-10);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        System.out.println(maxPathSum(node));

    }
}
