package com.java.trees;
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

public class SortedListToBST {

    public static TreeNode sortedListToBST(ListNodee head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public static TreeNode toBST(ListNodee head, ListNodee tail){
        ListNodee slow = head;
        ListNodee fast = head;
        if(head==tail) return null;

        while(fast!=tail&& fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }

    public static void main(String[] args) {

        ListNodee l2 = new ListNodee(-10);
        l2.next = new ListNodee(-3);
        l2.next.next = new ListNodee(0);
        l2.next.next.next = new ListNodee(5);
        l2.next.next.next.next = new ListNodee(9);

        TreeNode res = sortedListToBST(l2);
        System.out.println(res);

    }


}

class ListNodee {

    int val;
    ListNodee next;
    ListNodee() {

    }
    ListNodee(int val) {
        this.val = val;
    }
    ListNodee(int val, ListNodee next) {
        this.val = val;
        this.next = next;
    }

}