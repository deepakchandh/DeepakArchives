//$Id$
package com.java.dynamic;

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
        int dp[] = new int[amount+1];
        for (int target = 1; target < dp.length; target++) {
            dp[target] = dp.length;
            for (int coin : coins) {
                if (target >= coin) {
                    System.out.println(dp[target] + "__"+ dp[target - coin]);
                    dp[target] = Math.min(dp[target], dp[target - coin] + 1);
                }
            }
        }
        return dp[amount] == dp.length ? -1 : dp[amount];
    }


    public static void main(String arg[]) {
		int [] coins  = {1,2,5};
		int ways  = noOfCoins(11, coins);
//        int[] coins = {2};
//        int ways = noOfCoins(3, coins);


        System.out.println(ways);

        try {
        } catch (Exception e) {

        }

    }

}
