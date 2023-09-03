package com.java.practice;

public class CountAndSay {

    public String countAndSay(int n) {

//        if(n == 1)
//            return "1";
//        String res = countAndSay(n-1);
//        StringBuilder ans = new StringBuilder();
//        int left = 0, right = 0;
//        while(right < res.length()){
//            int counter = 0;
//            while(right<res.length() && res.charAt(left) == res.charAt(right)){
//                counter++;
//                right++;
//            }
//            ans.append(counter);
//            ans.append(res.charAt(left));
//            left = right;
//        }
//        return ans.toString();

        if( n == 1) return "1";

//         String s = countAndSay(n - 1);
         String s = count(countAndSay(n - 1));

//        return  count(countAndSay(n - 1));
         return s;
    }

    private String count(String s){
        StringBuilder sb = new StringBuilder();

        int count = 0;

        for(int i = 0; i < s.length(); i++){

            count++;

            if(i == s.length()-1 || s.charAt(i) != s.charAt(i+1)){
                sb.append(count).append(s.charAt(i));
                count = 0;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        CountAndSay say = new CountAndSay();
        System.out.println(say.countAndSay(4));
//        System.out.println(say.countAndSay(6));

    }
}
