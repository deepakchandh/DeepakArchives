package com.java.concepts.javafunctions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> {
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Result from Future1";
                });
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("Result from Future2");

        CompletableFuture<String> combinedFuture = future1.
                thenCombine(future2, (x, y) -> x +" and " +y);
        combinedFuture.thenAccept(System.out::println);
        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
