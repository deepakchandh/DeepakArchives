package com.java.concepts;

import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {
        // doesn't take any inp but returns a op
        supply(() -> "Hi");
        supply(() -> "Hey");
        supply(() -> "Hello");
        Supplier<Integer> supplierInteger = () -> 50;
        System.out.println(supplierInteger.get());

        Supplier<String> supplierString = () -> "Deepak";
        System.out.println(supplierString.get());
    }

    public static void supply(Supplier<?>supplier){
        System.out.println(supplier.get());
    }
}
