//$Id$
package com.java.practice;

public class RemoveKDigits {

static String res="";
    
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
        String s = "765028321";
        int k = 5;
        System.out.println(removeKdigits(s, k));
    }
}
