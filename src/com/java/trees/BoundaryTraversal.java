//$Id$
package com.java.trees;

public class BoundaryTraversal {
	
	class Node  
	{ 
	    int data; 
	    Node left, right; 
	   
	    public Node(int d)  
	    { 
	        data = d; 
	        left = right = null; 
	    } 
	}
	
	void printLeaves(Node root)
    {
        if(root==null)
        return ;
        
          if(root.left==null && root.right==null)
            System.out.print(root.data+" ");
         printLeaves(root.left);
         printLeaves(root.right);
      
        
       
    }
    
    void printLeft(Node root)
    {
        if(root==null)
        return ;
        
        if(root.left!=null)
        {
            System.out.print(root.data+" ");
            printLeft(root.left);
            
        }
         else if (root.right != null) { 
            System.out.print(root.data + " "); 
            printLeft(root.right); 
        } 
    }
    
    void printRight(Node root)
    {
        if(root==null)
        return ;
        
        if(root.right!=null)
        {
            printRight(root.right);
            System.out.print(root.data+" ");
        }
         else if (root.left != null) { 
            printRight(root.left); 
            System.out.print(root.data + " "); 
        } 
    }
	void printBoundary(Node root)
	{
	    System.out.print(root.data+" ");
	    
	    printLeft(root.left);
	    printLeaves(root);
	    printRight(root.right);
	}

}
