//$Id$
package com.java.practice.heap;

public class Testt {
	 public static void swap(char a, char b){
	        char c ='\0' ;
	        a=b;
	        b=c;
	        c=a;
	    }
	    
	    public void reverseString(char[] s) {
	        int n =s.length;
	        for(int i=0; i<n/2; i++){
	            swap(s[i], s[n-1-i]);
	        }
	    }
	 
}
