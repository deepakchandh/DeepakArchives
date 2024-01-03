//$Id$
package com.java.practice;
//https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/505505890/
import java.util.*;

public class LongestSubStringWithoutRepeatingCharacters {

    public static int romanToInt(String s) {
        int nums[]=new int[s.length()];
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }
        int sum=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] < nums[i+1]){
                sum-=nums[i];
            }else{
                sum+=nums[i];
            }
        }
        return sum+nums[nums.length-1];


    }
	
	 public static void main(String[] args)
     {
	     int ss  = romanToInt("MCMXCIV");
	     System.out.println(ss);
//	     System.out.println(lengthOfLongestSubstring2("abcbbcb"));

     }

    public static int lengthOfLongestSubstring2(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();

        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return max;

    }



	public static int lengthOfLongestSubstring(String s) {

		if (s.length()==0) 
			return 0;
		int maxi=0;
		ArrayList<Character> list = new ArrayList<>();
		for (int i=0; i<s.length(); ++i){
			if(! list.contains(s.charAt(i))){
				list.add(s.charAt(i));
			}else{
				maxi = Math.max(maxi, list.size());
				list.clear();
				list.add(s.charAt(i));
			}

		}
		return maxi;
	}
}
