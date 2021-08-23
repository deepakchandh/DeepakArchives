package com.java.practice;
//$Id$

import java.util.Queue;

public class StackUsingQ {

	Queue<Integer> queue;

	void push(int x){
		queue.add(x);
		queue.isEmpty();
		for(int i=1;i<queue.size();i++)
			queue.add(queue.poll());
	}

	void pop(){
		queue.poll();
	}

	int top(){
		return queue.peek();
	}
	public boolean empty() {
	    return queue.isEmpty();
	}

}
