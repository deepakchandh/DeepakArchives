package com.java.Threading;

//https://javabypatel.blogspot.com/2019/04/simple-deadlock-program-in-java.html
public class DeadlockProgram {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Scanner scanner = new Scanner();

        //John has bunch of documents that it wants to Print and also want to take a scan later
        new Thread (new PrintAndScan(printer, scanner), "John").start();

        //Michael has bunch of documents that it wants to Scan and also want to take a print later
        new Thread (new ScanAndPrint(printer, scanner), "Michael").start();
    }

}

class PrintAndScan implements Runnable{
    Printer printer;
    Scanner scanner;

    public  PrintAndScan(Printer printer, Scanner scanner){
        this.printer = printer;
        this.scanner = scanner;
    }
    @Override
    public void run() {
        // allowing only 1 thread to execute a given time..
        synchronized (printer){
            System.out.println("Thread "+Thread.currentThread().getName() + " acquired printer");
            try {
                //Do printing work
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread "+Thread.currentThread().getName() + " is waiting for scanner");
            synchronized (scanner) {
                System.out.println("Thread "+Thread.currentThread().getName() + " is scanning");
            }

        }
    }
}

class ScanAndPrint implements Runnable {
    Printer printer;
    Scanner scanner;

    public ScanAndPrint (Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        //Do scanning and printing simultaneously.
        synchronized (scanner) {
            System.out.println("Thread "+Thread.currentThread().getName() + " acquired scanner");
            try {
                //Do scanning work
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Scan the documents already printed
            System.out.println("Thread "+Thread.currentThread().getName() + " is waiting for printer");
            synchronized (printer) {
                System.out.println("Thread "+Thread.currentThread().getName() + " is printing");
            }
        }
    }
}

class Printer {}
class Scanner {}


