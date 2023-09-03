package com.java.trees.dfs;

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
public class SumRootToLeaf {

    int total;

    public int sumNumbers(TreeNode root) {
        total = 0;
        helper(root, 0);
        return total;
    }

    void helper(TreeNode root, int sum) {
        if (root == null) return;

        sum = sum * 10 + root.val;

        if (root.left == null && root.right == null) {
            total += sum;
            return;
        }

        helper(root.left, sum);
        helper(root.right, sum);
    }

    public static void main(String[] args) {
        TreeNode tree;
        tree = new TreeNode(1, null, null);
        tree.left = new TreeNode(2, null, null);
        tree.right = new TreeNode(3, null, null);
        tree.right.right = new TreeNode(5, null, null);

    }
}
