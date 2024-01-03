package com.java.practice;
//$Id$
//https://leetcode.com/problems/implement-stack-using-queues/
//https://leetcode.com/problems/implement-queue-using-stacks/discuss/64206/Short-O(1)-amortized-C%2B%2B-Java-Ruby - 2 stacks
import java.util.Queue;

public class StackUsingQ {

	Queue<Integer> queue;

	void push(int x){
		queue.add(x);
		for(int i=1;i<queue.size();i++) // //rotate the queue to make the tail be the head[
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
