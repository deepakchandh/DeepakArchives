package com.java.practice;

class ShortestPalindromeProblem {

//https://leetcode.com/problems/shortest-palindrome/solutions/60216/a-kmp-based-java-solution-with-explanation/
    public static String shortestPalindromeRabinKarp(String s) {
        if(s.length()<=1)
            return s;
        String temp = s+"#"+new StringBuilder(s).reverse().toString();
        int[] position = new int[temp.length()];

        for(int i=1;i<position.length;i++)
        {
            int pre_pos = position[i-1];
            while(pre_pos>0 && temp.charAt(pre_pos)!=temp.charAt(i))
                pre_pos = position[pre_pos-1];
            if(temp.charAt(pre_pos) == temp.charAt(i))
                position[i] = pre_pos+ 1;

//            position[i] = pre_pos+ ( (temp.charAt(pre_pos)==temp.charAt(i)) ? 1 : 0);
        }

        return new StringBuilder(s.substring(position[position.length-1])).reverse().toString()+s;
    }
    public static String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);
        for(int k: table){
            System.out.print(k +"_");
        }
//        System.out.println("Table :" + table[table.length - 1]);
        System.out.println(s.substring(table[table.length - 1]));
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;

    }

    public static int[] getTable(String s){ // abcd#dcba --> soln: dcbabcd
        int[] table = new int[s.length()];

        int index = 0;
        for(int i = 1; i < s.length(); ){
            if(s.charAt(index) == s.charAt(i)){
                table[i] = ++index;
                i++;
            } else {
                if(index > 0){
                    index = table[index-1];
                } else {
                    index = 0;
                    i ++;
                }
            }
        }
        return table;

    }

    public static void main(String[] args) {
//        System.out.println(shortestPalindromeRabinKarp("abcd"));
        System.out.println(shortestPalindrome("abcabc"));
    }
}

/*
private int[] computeKMPTable(String s) {
    int[] result = new int[s.length()];         // result[j] is the longest proper prefix that is also a suffix of s[0, j].
    result[0] = 0;   
    for (int i = 0, j = 1; j < s.length(); ++j) {
        if (s.charAt(i) == s.charAt(j)) {
            result[j] = ++i;   
        } else {
            while (i > 0 && s.charAt(i) != s.charAt(j)) {
                i = result[i - 1];
            }
            result[j] = s.charAt(i) == s.charAt(j) ? ++i : 0;
        }
    }
    return result;
}
*/