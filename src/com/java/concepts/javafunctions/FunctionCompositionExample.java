package com.java.concepts.javafunctions;

import java.util.function.Function;

public class FunctionCompositionExample {

    public static void main(String[] args) {
        // Create functions for adding 2 and multiplying by 3
        Function<Integer, Integer> addTwo = x -> x + 2;
        Function<Integer, Integer> mulThree = x -> x * 3;
        // Function composition using "andThen"
        Function<Integer, Integer> addAndMultiply = addTwo.andThen(mulThree);
        int result = addAndMultiply.apply(5);
        System.out.println(result);


    }
}
