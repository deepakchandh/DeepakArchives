//$Id$
package com.java.dynamic;

import java.util.Arrays;

//https://leetcode.com/problems/coin-change/
public class CoinChange {

    // 3 variants

    // 1 - no.of changes with Rep
    // 2 - no.of changes without Rep
    // 3 - min no. of coins


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
        int max = amount + 1;
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String arg[]) {

        /*
        coins = [1, 5, 10] amount = 12
         */
		int [] coins  = {1,5,10};
		int ways  = noOfCoins(12, coins);
//        int[] coins = {2};
//        int ways = noOfCoins(3, coins);


        System.out.println(ways);

        try {
        } catch (Exception e) {

        }

    }

}
