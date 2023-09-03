package com.java.concepts;

public class ThreadUnSafe extends Thread{
    int count = 0;

    // method which would be called upon
    // the start of execution of a thread
    public void run()
    {
        int max = 1_000_00_000;

        // incrementing counter
        // total of max times
        for (int i = 0; i < max; i++) {
            count++;
        }
    }
}

class UnSafeCounter {
    public static void main(String[] args)
            throws InterruptedException
    {
        // Instance of Counter Class
        ThreadUnSafe c = new ThreadUnSafe();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        // Threads start executing
        first.start();
        second.start();

        // main thread will wait for
        // both threads to get completed
        first.join(); // allows a thread to wait until the other thread completes its execution
        second.join();

        // Printing final value of count variable
        System.out.println(c.count);
    }
}
