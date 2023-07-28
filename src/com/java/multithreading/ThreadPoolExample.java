package com.java.multithreading;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 1,  TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.AbortPolicy() );

        try{
            for (int i = 0; i < 6; i++) {
                threadPoolExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("This is worker thread " + Thread.currentThread().getName() + " executing");
                        try {
                            // simulate work by sleeping for 1 second
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            // ignore for now
                        }
                    }
                });
            }
        }
        catch (Exception e){

        }
        finally {
            threadPoolExecutor.shutdown();
        }
    }
}
