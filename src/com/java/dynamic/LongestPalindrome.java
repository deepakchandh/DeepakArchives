//$Id$
package com.java.dynamic;

public class LongestPalindrome {

	public static String longestPalindrome(String s) {
        int n = s.length();
        int palindromeStartsAt = 0, maxLen = 0;
        String cp = s;
        boolean[][] dp = new boolean[n][n];
        // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome
        
     // keep increasing the possible palindrome string
        for(int i = n-1; i >= 0; i--) { 
        	// find the max palindrome within this window of (i,j)
            for(int j = i; j < n; j++) { 
                
                //check if substring between (i,j) is palindrome
            	// chars at i and j should match
            	// if window is less than or equal to 3, just end chars should match
            	 // if window is > 3, substring (i+1, j-1) should be palindrome too
            	if(i==0 && j==6){
            		char ss = s.charAt(i);
                	char sss = s.charAt(j);
                	System.out.println(ss +" "+ sss);
            	}
            	
            	
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && ( j-i < 3  || dp[i+1][j-1]  );
                
                System.out.println(dp[i][j]);
                //update max palindrome string
                if(dp[i][j] && (j-i+1 > maxLen)) {
                    palindromeStartsAt = i;
                    maxLen = j-i+1;
                }
            }
        }
        if (s.length()>1) {
        	return s.substring(palindromeStartsAt, palindromeStartsAt+maxLen);
		}else{
			return cp.charAt(0)+"";
		}
    }
	
	public static void main(String... args){
		
		String ss = "aaabbaa";
		String sss= longestPalindrome(ss);
		System.out.println(sss);
		
		
	}
}
