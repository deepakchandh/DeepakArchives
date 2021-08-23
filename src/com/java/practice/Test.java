//$Id$
package com.java.practice;

import java.util.ArrayList;

public class Test {

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
        for(int i=0;i<nums.length;i++){
            if(nums[i] < nums[i+1]){
                sum-=nums[i];
            }else{
                sum+=nums[i];
            }
        }
        System.out.println(nums[nums.length-1]);
        return sum+nums[nums.length-1];
                
    }
	
	 public static void main(String[] args)
	    {
	     int ss  = romanToInt("MCMIV");
	     System.out.println(ss);
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
