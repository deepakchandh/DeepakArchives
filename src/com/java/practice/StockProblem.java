//$Id$
package com.java.practice;

public class StockProblem {

	public static int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
	
	public static void main(String[] args)
	{
		int arr[]={1, 5, 3, 2};
		System.out.println(maxProfit(arr));
		
	}
}
