package com.java.craft;


import java.util.*;

class Node {
    int id;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int item)
    {
        id = item;
        left = right = null;
    }
}

public class DeleteNodesOfTree {
    Vector<Integer> vector = new Vector<>();

    Vector<Integer> removeNode(Node root, int nodeToBeRemoved){
        root = deleteNodes(root, nodeToBeRemoved);
        if(root != null)
            vector.add(root.id);
        return vector;
    }

    private Node deleteNodes(Node node, int nodeToBeRemoved){
        if(node == null)
            return null;
        node.left = deleteNodes(node.left, nodeToBeRemoved);
        node.right = deleteNodes(node.right, nodeToBeRemoved);

        if(node.id == nodeToBeRemoved){
            if(node.left != null)
                vector.add(node.left.id);
            if (node.right != null)
                vector.add(node.right.id);
            node = null;
        }
        return node;
    }

    public static void main(String[] args) {
        Node tree;
        tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.right = new Node(6);
        tree.right.right.left = new Node(7);

        DeleteNodesOfTree deleteNodesOfTree = new DeleteNodesOfTree();
        System.out.println(deleteNodesOfTree.removeNode(tree, 2));


    }
}

/*
//Time: O(to_delete.length + N) where N is the number of nodes in the given tree.
//Space: O(to_delete.length + H) where H is the height of the given tree.
DFS
Vector<Integer> removeNode(Node root, int nodeToBeRemoved){
        root = dfs(root, nodeToBeRemoved);
        if(root != null)
            vector.add(root.id);
        return vector;
    }

    private Node dfs(Node node, int nodeToBeRemoved){
        if(node == null)
            return null;
        node.left = dfs(node.left, nodeToBeRemoved);
        node.right = dfs(node.right, nodeToBeRemoved);

        if(node.id == nodeToBeRemoved){
            if(node.left != null)
                vector.add(node.left.id);
            if (node.right != null)
                vector.add(node.right.id);
            node = null;
        }
        return node;
    }

 */
