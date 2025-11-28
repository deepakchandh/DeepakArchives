package com.java.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOddPsvm {

    public static void main(String[] args) {
        ZeroEvenOdd2 zeo = new ZeroEvenOdd2(5); // change n here

        IntConsumer printer = x -> System.out.print(x);

        Thread tZero = new Thread(() -> {
            try { zeo.zero(printer); } catch (InterruptedException ignored) {}
        });

        Thread tEven = new Thread(() -> {
            try { zeo.even(printer); } catch (InterruptedException ignored) {}
        });

        Thread tOdd = new Thread(() -> {
            try { zeo.odd(printer); } catch (InterruptedException ignored) {}
        });

        tZero.start();
        tEven.start();
        tOdd.start();

        try {
            tZero.join();
            tEven.join();
            tOdd.join();
        } catch (InterruptedException ignored) {}

        System.out.println(); // newline at end
    }



}

class ZeroEvenOdd2 {
    private int n;

    private Semaphore zeroSem = new Semaphore(1);
    private Semaphore oddSem  = new Semaphore(0);
    private Semaphore evenSem = new Semaphore(0);

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);

            if (i % 2 == 1) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
}


