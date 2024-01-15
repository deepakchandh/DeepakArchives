package com.java.trees;

public class Diameter {

    static int maxx =0;
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxx;
        // return (1 + Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));

    }
    private static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxx = Math.max(maxx, left + right);
        return Math.max(left, right) + 1;

    }


    public static void main(String[] args) {
        TreeNode tree;
        tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(tree));
    }
}
