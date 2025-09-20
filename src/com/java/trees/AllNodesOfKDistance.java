package com.java.trees;

import java.util.*;

public class AllNodesOfKDistance {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Map each node to its parent
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        // BFS from target
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int currentDistance = 0;
        // Perform BFS until distance = k
        while (!queue.isEmpty()) {
            int size = queue.size();

            if (currentDistance == k) {
                // All nodes in queue now are at distance k
                List<Integer> result = new ArrayList<>();
                for (TreeNode node : queue) {
                    result.add(node.val);
                }
                return result;
            }

            // Expand to next level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check neighbors: left child, right child, parent
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode parent = parentMap.get(node);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }

            currentDistance++;
        }

        // If we finish BFS without reaching distance k, return empty list
        return new ArrayList<>();
    }

    private void buildParentMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        parentMap.put(root, null); // root has no parent

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        // Build sample tree
    /*
              3
             / \
            5   1
           / \ / \
          6  2 0  8
            / \
           7   4
    */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        AllNodesOfKDistance sol = new AllNodesOfKDistance();

        // Example test: distance 2 from target=5
        List<Integer> result = sol.distanceK(root, root.left, 2);

        System.out.println("Nodes at distance 2 from target 5: " + result);

    }
}
