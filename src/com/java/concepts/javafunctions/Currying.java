package com.java.concepts.javafunctions;

import java.util.function.Function;

public class Currying {

    /*
    instead of providing all the arguments at once, currying allows you to apply the function partially, one argument at a time,
    producing a new function with each application until all arguments are provided, and the final result is obtained.
     */
    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> add = x -> y -> x+y;

        Function<Integer, Integer> add5 = add.apply(5);

        int result = add5.apply(4);
        System.out.println(result);

    }
}
