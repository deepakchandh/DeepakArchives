package com.java.practice;

import java.util.Deque;
import java.util.LinkedList;

public class Dequeu {

    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();

        deque.add(13);
        deque.add(9);
        deque.add(8);
        deque.add(5);
        deque.add(3);
        System.out.println(deque);
        deque.remove();
        System.out.println(deque);
        deque.removeLast();
        System.out.println(deque);
        deque.remove(8);
        System.out.println(deque);

    }
}
