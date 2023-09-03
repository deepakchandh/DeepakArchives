package com.java.trees;

public class FlipEquivalBinaryTree {

    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) return false;


        Boolean flipLeft = flipEquiv(root1.left, root2.left);
        Boolean flipRight = flipEquiv(root1.right, root2.right);

        if (flipLeft && flipRight) {
            return true;
        }

        else if (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)) {
            return true;
        }

        else {
            return false;
        }
    }
    public static void main(String[] args) {

        TreeNode tree;
        tree = new TreeNode(1, null, null);
        tree.left = new TreeNode(2, null, null);
        tree.right = new TreeNode(3, null, null);
        tree.left.left = new TreeNode(4, null, null);
        tree.left.right = new TreeNode(5, null, null);
        tree.left.right.left = new TreeNode(7, null, null);
        tree.left.right.right = new TreeNode(8, null, null);
        tree.right.left = new TreeNode(6, null, null);


        TreeNode tree2;
        tree2 = new TreeNode(1, null, null);
        tree2.left = new TreeNode(3, null, null);
        tree2.right = new TreeNode(2, null, null);
        tree2.left.right = new TreeNode(6, null, null);
        tree2.right.left = new TreeNode(4, null, null);
        tree2.right.right = new TreeNode(5, null, null);
        tree2.right.right.left = new TreeNode(8, null, null);
        tree2.right.right.right = new TreeNode(7, null, null);

        System.out.println(flipEquiv(tree, tree2));



    }
}
