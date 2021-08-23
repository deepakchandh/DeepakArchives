//$Id$
package com.java.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tess {

	/*public static boolean wordBreak(String string, List<String> wordDict) {
        boolean isTrue = true;
        for(String str: wordDict){
            isTrue = isTrue && string.contains(str);
        }
        return isTrue;
    }*/
	
	
	public static boolean wordBreak(String s, List<String> wordDict) {
        // put all words into a hashset
        Set<String> set = new HashSet<>(wordDict);
        return wb(s, set);
    }
    private static boolean wb(String s, Set<String> set) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; ++i) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set)) {
                return true;
            }
        }
        return false;
    }
	
	
	 public static void main(String[] args){
		;
		Stack<Integer> stack =  new Stack<>();
		stack.push(1);
		stack.push(1);
		System.out.println(stack);
		
//		 System.out.println(  wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
		 
	 }
	 
}
