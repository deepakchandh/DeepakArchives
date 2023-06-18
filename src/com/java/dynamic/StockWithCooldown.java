package com.java.dynamic;

public class StockWithCooldown {

    public static int maxProfit(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2}));

    }
}
