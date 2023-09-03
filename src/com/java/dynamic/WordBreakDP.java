//$Id$
package com.java.dynamic;

import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.Set;

public class WordBreakDP {

	public static boolean wordBreakDfs(String s, List<String> wordDict) {
		Set<String> wordDictSet = new HashSet<>(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[s.length()];
		queue.add(0);
		while (!queue.isEmpty()) {
			int start = queue.remove();
			if (visited[start]) {
				continue;
			}
			for (int end = start + 1; end <= s.length(); end++) {
				if (wordDictSet.contains(s.substring(start, end))) {
					queue.add(end);
					if (end == s.length()) {
						return true;
					}
				}
			}
			visited[start] = true;
		}
		return false;
	}

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
		System.out.println(  wordBreak("catsandog", Arrays.asList("cats","dog","and")));
	}
}
