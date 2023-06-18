package com.java.graphs;

import java.util.*;

public class AverageOfLevelsInBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();

        if(root == null) return result;
        que.add(root);

        while(!que.isEmpty()){
            int n = que.size();
            double sum = 0.0;
            for(int i=0;i<n;i++){
                TreeNode node = que.poll();
                sum+=node.val;
                if(node.left != null) que.offer(node.left);
                if(node.right != null) que.offer(node.right);
            }
            result.add(sum/n);
        }
        return result;

    }

    public static void main(String[] args) {

    }
}
