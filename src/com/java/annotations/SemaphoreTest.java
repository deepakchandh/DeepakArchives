//$Id$
package com.java.annotations;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	static Semaphore semaphore = new Semaphore(1);
	// to test mutex - set the semaphore count as 1.
	
	static class MyATMThread extends Thread {
		
		String name = "";

        MyATMThread(String name) {
            this.name = name;
        }

        public void run(){
        	try{
        		System.out.println(name + " : acquiring lock...");
        		System.out.println(name + " : available Semaphore permits now: " 
                        + semaphore.availablePermits());
        		semaphore.acquire();
        		System.out.println(name + " : got the permit!");
        		try{
        			for(int i=1;i<=5;i++){
        				System.out.println(name + " : is performing operation " + i 
                                + ", available Semaphore permits : "
                                + semaphore.availablePermits());
        				Thread.sleep(1000);
        			}
        		}
        		finally {
        			System.out.println(name + " : releasing lock...");
                    semaphore.release();
                    System.out.println(name + " : available Semaphore permits now: " 
                                + semaphore.availablePermits());
				}
        	}
        	catch (Exception e) {
				// TODO: handle exception
			}
        }
	}
	
	public static void main(String[] args) {
		System.out.println("Total available Semaphore permits : " 
                + semaphore.availablePermits());
		MyATMThread m1 = new MyATMThread("A");
		m1.start();
		
		MyATMThread t2 = new MyATMThread("B");
        t2.start();

       /* MyATMThread t3 = new MyATMThread("C");
        t3.start();

        MyATMThread t4 = new MyATMThread("D");
        t4.start();

        MyATMThread t5 = new MyATMThread("E");
        t5.start();

        MyATMThread t6 = new MyATMThread("F");
        t6.start();*/
	}
	
}
