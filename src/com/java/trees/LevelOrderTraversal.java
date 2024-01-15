package com.java.trees;

import java.util.*;

public class LevelOrderTraversal {


    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> finalAns = new ArrayList<List<Integer>>();
        if(root==null){
            return finalAns;
        }
        q.add(root);
        while(!q.isEmpty()){
            int levels = q.size();
            List<Integer> subLevels = new ArrayList<>();
            for(int i=0;i<levels;i++){
                if(q.peek().left!=null){
                    q.add(q.peek().left);
                }
                if(q.peek().right!=null){
                    q.add(q.peek().right);
                }
                subLevels.add(q.remove().val);
            }
            finalAns.add(subLevels);
        }
        return finalAns;



    }

    public static void main(String[] args) {

        TreeNode  root = new TreeNode(1);
        root . left = new TreeNode(3);
        root . right = new TreeNode(2);

        root . left . left = new TreeNode(5);
        root . left . right = new TreeNode(7);

        root . right . left = new TreeNode(4);
        root . right . right  = new TreeNode(6);

        System.out.println("Level Order "+levelOrder(root));

    }
}
