package com.java.trees;

import java.util.LinkedList;
import java.util.Queue;

class Nodei {
    TreeNode node;
    int idx;
    Nodei(TreeNode node, int idx){
        this.node = node;
        this.idx = idx;
    }
}

public class WidthOfBt {
    public static int widthOfBinaryTree(TreeNode root) {
        Queue<Nodei> queue = new LinkedList<>();
        queue.add(new Nodei(root,0));
        int max = 0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            int start = 0, end = 0;
            for(int i=0; i<size; i++)
            {
                Nodei eachNode = queue.remove();
                int index = eachNode.idx;
                if(i==0)
                    start = index; //start and end index for each level

                if(i==size-1)
                    end = index;

                if(eachNode.node.left!=null)
                    queue.add(new Nodei(eachNode.node.left, 2*eachNode.idx));

                if(eachNode.node.right!=null)
                    queue.add(new Nodei(eachNode.node.right, 2*eachNode.idx+1));

            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }


    public static void main(String args[]) {

        TreeNode  root = new TreeNode(1);
        root . left = new TreeNode(3);
        root . left . left = new TreeNode(5);
        root . left . left . left = new TreeNode(7);
        root . right = new TreeNode(2);
        root . right . right = new TreeNode(4);
        root . right . right . left = new TreeNode(6);

        int maxWidth = widthOfBinaryTree(root);
        System.out.println("The maximum width of the Binary Tree is "+maxWidth);


    }



}
