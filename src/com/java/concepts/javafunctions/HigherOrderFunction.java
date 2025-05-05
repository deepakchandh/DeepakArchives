package com.java.concepts.javafunctions;

import java.util.function.IntBinaryOperator;

public class HigherOrderFunction {

    public static int applyOperation(int a, int b, IntBinaryOperator operation){
        return operation.applyAsInt(a, b);
    }

    public static void main(String[] args) {

        IntBinaryOperator add = (a,b) -> a+b;
        IntBinaryOperator sub = (a,b) -> a-b;
        IntBinaryOperator mul = (a,b) -> a*b;

        int x = 5, y = 10;

        int resultAdd = applyOperation(x,y,add);
        int resultSub = applyOperation(x,y,sub);
        int resultMul = applyOperation(x,y,mul);


        System.out.println("Addition: " + resultAdd); // Output: Addition: 15
        System.out.println("Subtraction: " + resultSub); // Output: Subtraction: 5
        System.out.println("Multiplication: " + resultMul); // Output: Multiplication: 50
    }
}
