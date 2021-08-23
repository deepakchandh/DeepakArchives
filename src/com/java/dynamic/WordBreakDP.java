//$Id$
package com.java.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakDP {


	public static boolean wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		
		for(int i=1;i<=s.length();i++){
			for(int j=0;j<i;j++){
				String ss = s.substring(j, i);
				Boolean gg = dp[j];
				if (dp[j] && set.contains(s.substring(j, i))) {
					dp[i] =true;
					break;
				}
			}
		}
		return dp[s.length()];

	}

	public static void main(String[] args){
		System.out.println(  wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
	}
}
