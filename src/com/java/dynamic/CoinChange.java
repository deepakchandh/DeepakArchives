//$Id$
package com.java.dynamic;

//https://leetcode.com/problems/coin-change/
public class CoinChange {

    // rep coin logic
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    // Non-rep coin logic
    public static int changeNonRep(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = amount; i >= coin; i--)
                dp[i] += dp[i - coin];
        }
        return dp[amount];
    }

    public static int noOfCoins(int amount, int[] coins) {
        if (coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        System.out.println("dp[amount]:"+ dp[amount]);
        System.out.println("Result:"+ dp[amount]);

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    public static void main(String arg[]) {
//		int [] coins  = {1,5,10};
//		int ways  = change(12, coins);
        int[] coins = {2};
        int ways = noOfCoins(3, coins);


        System.out.println(ways);

        try {
        } catch (Exception e) {

        }

    }

}
