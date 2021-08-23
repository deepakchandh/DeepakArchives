//$Id$
package com.java.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

	public static void main(String[] args) {
		
		String string= "aabb";
		List<List<String>> res = new ArrayList<List<String>>();
		List<String> path = new ArrayList<>();
		func(0, string, path, res);
		System.out.println(res);
	}

	private static void func(int index, String string, List<String> path, List<List<String>> res) {
		if (index == string.length()) {
			res.add(new ArrayList<>(path));
			return;
		}
		for(int i=index;i<string.length();i++){
			if (isPalin(string, index, i)) {
				path.add(string.substring(index, i+1));
				func(i+1, string, path, res);
				path.remove(path.size() -1); //Why!!!
			}
		}
		
	}

	private static boolean isPalin(String string, int start, int end) {
		while(start <= end){
			if (string.charAt(start++) != string.charAt(end--)) {
				return false;
			}
		}
		return true;
	}
}
