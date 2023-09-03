package com.java.concepts;

public class ThreadSafe extends Thread{

    int count = 0;

    // method which would be called upon
    // the start of execution of a thread
    public synchronized void run()
    {

        int max = 1_000_00_000;

        // incrementing counter total of max times
        for (int i = 0; i < max; i++) {
            count++;
        }
    }

}
class SynchronizedCounter {
    public static void main(String[] args)
            throws InterruptedException
    {
        // Instance of Counter Class
        ThreadSafe c = new ThreadSafe();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        // Threads start executing
        first.start();
        second.start();

        // main thread will wait for both
        // threads to complete execution
        first.join();
        second.join();

        // Printing final value of count variable
        System.out.println(c.count);
    }
}