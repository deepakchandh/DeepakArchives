//$Id$
package com.java.practice.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQue {

	public static void main(String... args){
		// in java by default, pq is min heap
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Arrays.asList(7, 10, 4, 3, 20, 15));
		// for Max heap
//		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
//		maxHeap.add(7);maxHeap.add(10);maxHeap.add(4);maxHeap.add(3);maxHeap.add(20);maxHeap.add(15);
		
		
		int k = 1;
		Boolean isAvailable = true;
//		Iterator<Integer> iterator = priorityQueue.iterator();
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		/*System.out.println("poll");
		while(isAvailable){
	           --k;
	           System.out.println(priorityQueue.poll());; // remove the head of the element
	           if(k<0){
	               isAvailable = false;
	           }
	       }*/
		System.out.println("result"+priorityQueue.poll());;
		/*System.out.println("maxheap");
		Iterator<Integer> iterator2 = maxHeap.iterator();
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}*/
		/* while(isAvailable){
	           --k;
	           priorityQueue.poll(); // remove the head of the element
	           if(k<0){
	               isAvailable = false;
	           }
	       }
		System.out.println(priorityQueue.peek());*/
	}
}
