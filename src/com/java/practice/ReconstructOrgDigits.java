package com.java.practice;

//https://leetcode.com/problems/reconstruct-original-digits-from-english/description/

    /*
z(zero) -> 0
 x(six)  -> 6
 u(four) -> 4
 w(two)  -> 2
 g(eigth)-> 8
 f(five+four) -> 5 && 4
 O(one +two + four + zero) -> 1 && 2 && 4 && 0
 t(three + eight + two) -> 3 && 8 && 2
 s(seven + six) -> 7 && 6
 i(nine + five + six + eight) -> 9 && 5 && 6 && 8
    */

public class ReconstructOrgDigits {

    public static String originalDigits(String s) {
        int[] map=new int[26]; //frequency of characters
        for(char ch:s.toCharArray())
            map[ch-'a']++;

        int[] digit=new int[10]; //frequency of numbers

        digit[0]=map['z'-'a'];
        digit[6]=map['x'-'a'];
        digit[4]=map['u'-'a'];
        digit[2]=map['w'-'a'];
        digit[8]=map['g'-'a'];
        digit[5]=map['f'-'a'] - digit[4];
        digit[7]=map['s'-'a'] - digit[6];
        digit[3]=map['t'-'a'] - digit[8] - digit[2];
        digit[1]=map['o'-'a']-digit[2]-digit[4]-digit[0];
        digit[9]=map['i'-'a']-digit[5]-digit[6]-digit[8];

        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<10;i++){
            int freq=digit[i];
            while(freq-->0){
                sb.append(i);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(originalDigits("fviefurow"));
    }
}
