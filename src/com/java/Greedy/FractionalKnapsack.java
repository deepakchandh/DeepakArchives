//$Id$
package com.java.Greedy;

import java.util.Arrays;
import java.util.Comparator;

class Item{
	int val;
	int weight;
	
	public Item(int val, int weight) {
		this.val = val;
		this.weight = weight;
	}
}

class ItemComp implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		double d1 = (double) o1.val / (double) o1.weight;
		double d2 = (double) o2.val / (double) o2.weight;
		if (d1 < d2) {
			return 1;
		}else{
			return -1;
		}
	}
	
}
public class FractionalKnapsack {


	
	private static double getMaxValue(int capacity, Item arr[], int n)
	{
		Integer.bitCount(1);
		Arrays.sort(arr, new ItemComp());
		double finValue = 0.0;
		int currWeight =0;
		for(int i=0;i<n;i++){
			
			if (currWeight + arr[i].weight <= capacity) {
				currWeight +=  arr[i].weight;
				finValue+= arr[i].val;
			}
			else{
				int remain  = capacity - currWeight;
				finValue+=((double) arr[i].val /(double)arr[i].weight) * (double) remain; 
				break;
			}
		}
		return finValue;
	}


	// Driver code
	public static void main(String[] args)
	{
		int[] wt = { 10, 40, 20, 30 };
		int[] val = { 60, 40, 100, 120 };
		int capacity = 50;
		
		Item arr[] = new Item[4];
		arr[0] = new Item(val[0], wt[0]);
		arr[1] = new Item(val[1], wt[1]);
		arr[2] = new Item(val[2], wt[2]);
		arr[3] = new Item(val[3], wt[3]);
		
		System.out.println(getMaxValue(capacity, arr, 4));;
		

	}
}