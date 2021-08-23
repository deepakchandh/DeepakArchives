//$Id$
package com.java.functionalIntf;

public class Main {

	public static void main(String... args) {
		// anonymous class
		
		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t*2);
			}
		};
		
		Consumer<Integer> consumer2 = new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t*2);
			}
		};
		
		consumer.accept(6);
	}
}
