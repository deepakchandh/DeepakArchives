package com.java.trees.view;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}
public class RightView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }


}
