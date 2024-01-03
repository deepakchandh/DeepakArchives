package com.java.trees;

import java.util.*;

public class AllNodesFromDistanceK {

    Map<TreeNode, Integer> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, Integer target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs(root, target, K, res);
        return res;
    }

    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, Integer target) {
        if (root == null) return -1;
        if (root.val == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }

    private void dfs(TreeNode root, int K, int length, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root))
            length = map.get(root);
        if (length == K)
            res.add(root.val);
        dfs(root.left,  K, length + 1, res);
        dfs(root.right, K, length + 1, res);
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.right = new TreeNode(1);

        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.left.right.right = new TreeNode(4);
        node.left.right.left = new TreeNode(7);

        node.right.right = new TreeNode(8);
        node.right.left = new TreeNode(0);

        AllNodesFromDistanceK allNodesFromDistanceK = new AllNodesFromDistanceK();
        System.out.println(allNodesFromDistanceK.distanceK(node, 5, 2));


    }
}
