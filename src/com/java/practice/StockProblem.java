//$Id$
package com.java.practice;

import java.util.ArrayList;

public class StockProblem {

    class Interval {
        int buy, sell;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/?envType=study-plan-v2&envId=top-interview-150
	public static int maxProfit(int prices[]) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            while (i < prices.length-1 && prices[i+1] <= prices[i]) // find local minimum
                i++;
            int min = prices[i++];
            while (i < prices.length-1 && prices[i+1] >= prices[i]) // find local maximum
                i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }





    // In the given array, calculates the MULTIPLE INTERVALS of max profit
    // calculate by local maxima, local minima.
    public void stockBuySell(int price[], int n)
    {
        if (n == 1) return;

        int count = 0;
        ArrayList<Interval> sol = new ArrayList<Interval>();
        int i = 0;
        while (i < n - 1) {
            while ((i < n - 1) && (price[i + 1] <= price[i]))
                i++;
            if (i == n - 1)
                break;
            Interval interval = new Interval();
            interval.buy = i++;

            while ((i < n) && (price[i] >= price[i - 1]))
                i++;

            // Store the index of maxima
            interval.sell = i - 1;
            sol.add(interval);
            count++;
        }

        // print solution
        if (count == 0)
            System.out.println(
                    "There is no day when buying the stock "
                            + "will make profit");
        else
            for (int j = 0; j < count; j++)
                System.out.println(
                        "Buy on day: " + sol.get(j).buy
                                + "        "
                                + "Sell on day : " + sol.get(j).sell);

        return;
    }

	public static void main(String[] args)
	{
//		int arr[]={100, 180, 260, 310, 40, 535, 695};// 865
//		int arr[]={7,1,5,3,6,4}; // 7
		int arr[]={1,2,3,4,5};
        StockProblem stockProblem = new StockProblem();
		System.out.println("Result: " +maxProfit(arr));
//        stockProblem.stockBuySell(arr, 7);
//		System.out.println();

	}
}
