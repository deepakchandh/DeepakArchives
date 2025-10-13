package com.java.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedLists {

    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list
        boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public static int depthSum(List<NestedInteger> nestedList) {
        // Write your code here
        int sum = 0;
        if (nestedList == null || nestedList.size() == 0) {
            return sum;
        }
        int depth = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger nestedInteger : nestedList) {
            queue.offer(nestedInteger);
        }
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nestedInteger = queue.poll();
                if (nestedInteger.isInteger()) {
                    sum += depth * (int) nestedInteger.getInteger();
                } else {
                    for (NestedInteger innerInteger : nestedInteger.getList()) {
                        queue.offer(innerInteger);
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        NestedInteger ni1 = new SimpleNestedInteger(1);
        NestedInteger ni2 = new SimpleNestedInteger(1);
        List<NestedInteger> sublist1 = new ArrayList<>();
        sublist1.add(ni1);
        sublist1.add(ni2);
        NestedInteger nested1 = new SimpleNestedInteger(sublist1);

        NestedInteger ni3 = new SimpleNestedInteger(2);

        NestedInteger ni4 = new SimpleNestedInteger(1);
        NestedInteger ni5 = new SimpleNestedInteger(1);
        List<NestedInteger> sublist2 = new ArrayList<>();
        sublist2.add(ni4);
        sublist2.add(ni5);
        NestedInteger nested2 = new SimpleNestedInteger(sublist2);

        List<NestedInteger> topList = new ArrayList<>();
        topList.add(nested1);
        topList.add(ni3);
        topList.add(nested2);

        int result = depthSum(topList);
        System.out.println("Weighted sum = " + result);  // expected: 10 for [[1,1],2,[1,1]]


    }

    static class SimpleNestedInteger implements NestedInteger {
        private Integer singleInteger;
        private List<NestedInteger> list;

        // constructor for integer
        public SimpleNestedInteger(Integer value) {
            this.singleInteger = value;
            this.list = null;
        }

        // constructor for list
        public SimpleNestedInteger(List<NestedInteger> list) {
            this.list = list;
            this.singleInteger = null;
        }

        @Override
        public boolean isInteger() {
            return singleInteger != null;
        }

        @Override
        public Integer getInteger() {
            return singleInteger;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
}
