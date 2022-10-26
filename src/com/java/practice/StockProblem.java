//$Id$
package com.java.practice;

import java.util.ArrayList;

public class StockProblem {

    class Interval {
        int buy, sell;
    }
    // In the given array, calculates the max profit
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
            Interval e = new Interval();
            e.buy = i++;

            while ((i < n) && (price[i] >= price[i - 1]))
                i++;

            // Store the index of maxima
            e.sell = i - 1;
            sol.add(e);

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
		int arr[]={100, 180, 260, 310, 40, 535, 695};
        StockProblem stockProblem = new StockProblem();
//		System.out.println(maxProfit(arr));
        stockProblem.stockBuySell(arr, 7);
//		System.out.println();

	}
}
