//$Id$
package com.java.practice;

public class EditDistance {

	public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
//        for(int i = 0; i <= m; i++){
//        	for(int j=0; j<=n;j++)
//        		System.out.print(cost[i][j]+ " - ");
//        	System.out.println();
//        }
        	
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = Math.min(a,Math.min(b,c));//a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        for(int i = 0; i <= m; i++){
        	for(int j=0; j<=n;j++)
        		System.out.print(cost[i][j]+ " -");
        	System.out.println();
        }
        
        return cost[m][n];
    }
	
	public static void main(String arg[])
    {
		String str1 = "sunday", str2 = "saturday";
		int ss= minDistance(str1, str2);
		System.out.println(ss);
    }
}
