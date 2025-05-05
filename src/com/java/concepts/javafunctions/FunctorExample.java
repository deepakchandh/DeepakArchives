package com.java.concepts.javafunctions;

// https://freedium.cfd/https://medium.com/@avicsebooks/functional-programming-in-java-8b3f73b11df0
import java.util.function.Function;

interface Functor<T> {
    <R> Functor<R> map(Function<T, R> function);
}
// Example Functor implementation for a Box that holds a value
class Box<T> implements Functor<T> {
    private final T value;
    Box(T value) {
        this.value = value;
    }
    @Override
    public <R> Functor<R> map(Function<T, R> function) {
        R result = function.apply(value);
        return new Box<>(result);
    }
    public T getValue() {
        return value;
    }
}

public class FunctorExample {
    // you can apply a function to the values inside the type while preserving the structure of the type

    public static void main(String[] args) {

        Box<Integer> integerBox = new Box<>(5);
        // Define a function to double the value
        Function<Integer, Integer> doubleFunction = x -> x * 2;
        // Map the function over the Box
        Functor<Integer> doubledBox = integerBox.map(doubleFunction);
        // Get the result from the mapped Box
//        int result = doubledBox.getValue();
//        System.out.println("Doubled value: " + result);

    }
}
