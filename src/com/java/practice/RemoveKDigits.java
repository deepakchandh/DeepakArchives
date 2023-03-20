//$Id$
package com.java.practice;

import java.util.*;

//https://www.geeksforgeeks.org/build-lowest-number-by-removing-n-digits-from-a-given-number/
public class RemoveKDigits {

static String res="";

    public static String removeKdigitWithStackLogic(String num, int k)
    {
        StringBuilder result = new StringBuilder();
        // base cases
        if (k >= num.length()) {
            return "0";
        }if (k == 0) {
        return num;
        }

        Stack<Character> s = new Stack<Character>();

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            // Removing all digits in stack that are greater
            // than this digit(since they have higher
            // weightage)
            while (!s.isEmpty() && k > 0 && s.peek() > c) {
                s.pop();
                k--;
            }
            // ignore pushing 0
            if (!s.isEmpty() || c != '0')
                s.push(c);
        }

        // If our k isnt 0 yet then we keep popping out the
        // stack until k becomes 0
        while (!s.isEmpty() && k > 0) {
            k--;
            s.pop();
        }
        if (s.isEmpty())
            return "0";
        while (!s.isEmpty()) {
            result.append(s.pop());
        }
        String str = result.reverse().toString();

        return str;
    }

    static void buildLowestNum(String str, int n){
        int length = str.length();
        if(n==0){
            res+=str;
            return;
        }
        if(length <= n)
            return;
        
        int minIndex=0;
        for(int i=1;i<=n;i++){
            if(str.charAt(i) < str.charAt(minIndex) ){
                minIndex = i;
            }
        }
        
        res += str.charAt(minIndex);
        String newString = str.substring(minIndex+1);
        buildLowestNum(newString, n - minIndex);
    }
    
    public static String removeKdigits(String str, int n)
    {
        res="";
       buildLowestNum(str, n);
       String ans="";
      int flag=0;
       for(int i=0;i<res.length(); i++){
           if(res.charAt(i)!='0' || flag==1){
                flag =1 ;
                ans+=res.charAt(i);
           }
               
       }
       
       if (ans.length() == 0)
			return "0";
		else
			return ans;
    }
    
    public static void main(String[] args)
    {
        String s = "788";
        int k = 1;
//        System.out.println(removeKdigits(s, k));
        System.out.println(removeKdigitWithStackLogic(s,k));
    }
}
