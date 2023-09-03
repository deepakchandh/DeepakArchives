package com.java.trees;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class InvertBinaryTree {

    public static TreeNode invertTree(TreeNode root) {

        if(root == null)
            return null;

        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;


    }

    public static void main(String[] args) {
        TreeNode tree;
        tree = new TreeNode(1, null, null);
        tree.left = new TreeNode(1, null, null);
        tree.right = new TreeNode(1, null, null);
        tree.left.left = new TreeNode(1, null, null);
        tree.left.right = new TreeNode(1, null, null);
        tree.right.left = new TreeNode(1, null, null);
        tree.right.right = new TreeNode(1, null, null);

        invertTree(tree);

    }
}
