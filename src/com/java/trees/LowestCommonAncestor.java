package com.java.trees;


import java.util.*;

public class LowestCommonAncestor {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // Map from node to its parent
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);

        // BFS queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Build parent pointers until we have both p and q in the map
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }

        // Build set of ancestors for p
        Set<TreeNode> ancestors = new HashSet<>();
        TreeNode current = p;
        while (current != null) {
            ancestors.add(current);
            current = parent.get(current);
        }

        // Now go up from q until we find a common ancestor
        current = q;
        while (!ancestors.contains(current)) {
            current = parent.get(current);
        }

        return current;
    }

    public static void main(String[] args) {
/*
        // Building the exact tree:
        //
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6   2  0  8
        //       / \
        //      7   4

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        // For input: p = 5, q = 1
        TreeNode p = root.left;   // node 5
        TreeNode q = root.right;  // node 1

        TreeNode ans = lowestCommonAncestor(root, p, q);

        System.out.println("LCA = " + ans.val);  // Expected Output: 3


 */

        // Building this tree:
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6   2  0  8
        //       / \
        //      7   4

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = root.left;      // node 5
        TreeNode q = root.left.right.right; // node 4

        TreeNode lca = lowestCommonAncestor(root, p, q);

        System.out.println("LCA: " + lca.val);  // Output should be 5
    }
}
