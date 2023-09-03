package com.java.trees;

import java.util.*;

public class ZigZagTraversal {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> op = new LinkedList<>();
        if(root==null){
            return op;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean odd=true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> res = new LinkedList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if(odd){
                    res.add(node.val);
                } else {
                    res.addFirst(node.val);
                }
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }

            }
            op.add(res);
            odd=!odd;
        }
        return op;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(zigzagLevelOrder(root));
    }

}
