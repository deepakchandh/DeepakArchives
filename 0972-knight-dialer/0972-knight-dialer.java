class Solution {
    int mod = 1_000_000_007;

    public int knightDialer(int n) {
        if(n == 1) 
            return 10;
        
        long[][] dp = new long[n][10];
        for(int i = 0; i < 10; i++)
            dp[0][i] = 1;
        
        for(int i = 1; i < n; i++)
            for(int j = 0; j < 10; j++)
                helper(dp, i, j);

        long ans = 0;

        for(int j=0; j< 10;j++){
            ans += dp[n-1][j];
            ans %= mod;
        }
        return (int)ans;
    }

    private void helper(long[][] dp, int i, int j){
        if(j == 1)
            dp[i][j] = (dp[i-1][6] + dp[i-1][8]) % mod;
        else if(j == 3)
            dp[i][j] = (dp[i-1][4] + dp[i-1][8]) % mod;
        else if(j == 7)
            dp[i][j] = (dp[i-1][6] + dp[i-1][2]) % mod;
        else if(j == 9)
            dp[i][j] = (dp[i-1][4] + dp[i-1][2]) % mod;
        else if(j == 2)
            dp[i][j] = (dp[i-1][7] + dp[i-1][9]) % mod;
        else if(j == 8)
            dp[i][j] = (dp[i-1][1] + dp[i-1][3]) % mod;
        else if(j == 4)
            dp[i][j] = (dp[i-1][3] + dp[i-1][9] + dp[i-1][0]) % mod;
        else if(j == 6)
            dp[i][j] = (dp[i-1][1] + dp[i-1][7] + dp[i-1][0]) % mod;
        else if(j == 0)
            dp[i][j] = (dp[i-1][4] + dp[i-1][6]) % mod;
    }


    
}