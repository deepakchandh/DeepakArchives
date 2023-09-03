package com.java.trees;

//https://www.youtube.com/watch?v=aaSFzFfOQ0o
import java.util.*;

public class DeleteNodesAndReturnForest {

    List<TreeNode> result = new ArrayList();
    Set<Integer> set = new HashSet(); // to quickly determine whether node is inside list or not.

    // Bottom up approach, here we go the very bottom of the tree and traverse from there.
    // if we go from top-bottom approach and we remove nodes, that could potentially lose things that we need references to.
    // so we go to the bottom of the tree and then return null for the base case. We have the base case, we've returned back to the previous recursive call.
    //

    // Doing a post order traversal.(left, right, root) and then check to see if the current node needs to be deleted or not.
    // if the node to be deleted has left/right child, then we add corresponding left/right child to final list


    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        //TC O(N+m) -> we traverse all the nodes once.. n- nodes, m - to_delete
        //SC O(N+m)

        for(int i : to_delete)
            set.add(i);
        root = dfs(root);

        // since we are doing bottom up approach, we need to ask the same question to root node whether to keep this or not
        if(root != null)
            result.add(root);
        return result;
    }

    private TreeNode dfs(TreeNode node){
        if(node == null)
            return null;
        node.left = dfs(node.left);
        node.right = dfs(node.right);
        if(set.contains(node.val)){
            if(node.left != null)
                result.add(node.left);
            if (node.right != null)
                result.add(node.right);
            node = null; // once we have saved the nodes we need to make that null.
        }
        return node;
    }





    public static void main(String[] args) {
        TreeNode tree;
        tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.right = new TreeNode(6);
        tree.right.right.left = new TreeNode(7);

        DeleteNodesAndReturnForest deleteNodesAndReturnForest = new DeleteNodesAndReturnForest();
        List<TreeNode> res = deleteNodesAndReturnForest.delNodes(tree, new int[]{5});
//        List<TreeNode> res = deleteNodesAndReturnForest.delNodesBfs(tree, new int[]{2});
        Vector<Integer> vector = new Vector<>();
        for(TreeNode node: res){
            vector.add(node.val);
        }
        System.out.println("Roots Present: "+vector);




    }


}

/*
public List<TreeNode> delNodesBfs(TreeNode root, int[] to_delete) {
        // TC: O(N)
        // SC: O(N)
        List<TreeNode> answer = new ArrayList<>();
        ArrayList<Integer> delete = new ArrayList<>();
        for(int i=0;i<to_delete.length;i++) {
            delete.add(to_delete[i]);
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            while(size>0) {

                TreeNode top = q.poll();

                if(delete.contains(top.val)) {
                    TreeNode l = top.left;
                    TreeNode r = top.right;
                    top.left = null;
                    top.right = null;
                    if(answer.contains(top)) {
                        answer.remove(top);
                    }
                    if(l!=null) {
                        q.offer(l);
                        answer.add(l);
                    }
                    if(r!=null) {
                        q.offer(r);
                        answer.add(r);
                    }
                }


                if(top.left!=null) {
                    if(delete.contains(top.left.val)) {
                        TreeNode l = top.left;
                        top.left = null;
                        if(l!=null) {
                            q.offer(l);
                        }
                    }
                    else {
                        q.offer(top.left);
                    }
                }

                if(top.right!=null) {
                    if(delete.contains(top.right.val)) {
                        TreeNode r = top.right;
                        top.right = null;
                        if(r!=null) {
                            q.offer(r);
                        }
                    }
                    else {
                        q.offer(top.right);
                    }
                }

                size--;
            }
        }
        if(root!=null && !delete.contains(root.val)) {
            answer.add(root);
        }

        return answer;
    }
 */

// 1 - 2,3
// 2 - 1,4,5
// 3 - 6, 1
// 4 - 1
// 5 - 1
// 6 - 7,1
// 7 - 1



