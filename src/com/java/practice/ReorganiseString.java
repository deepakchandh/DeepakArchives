package com.java.practice;

import java.util.PriorityQueue;

public class ReorganiseString {

     static class Key {
        int freq;
        char c;
        public Key(int freq, char c){
            this.freq = freq;
            this.c = c;
        }
    }

    public static String reorganizeString(String S) {

        int count[] = new int[26];

        for(int i =0;i<S.length();i++){
            count[S.charAt(i)-'a']++;
        }

        PriorityQueue<Key> pq = new PriorityQueue<>((a, b)->{
            return b.freq-a.freq;
        });

        for(char c = 'a';c<='z';c++){
            int value = c-'a';
            if(count[value]>0)
                pq.add(new Key(count[value], c));
        }

        Key pre = new Key(-1, '#');

        StringBuilder sb = new StringBuilder();

        while(pq.size()!=0){

            Key key = pq.poll();

            sb.append(key.c);

            if(pre.freq>0){
                pq.add(pre);
            }
            (key.freq)--;
            pre = key;
        }

        if(sb.toString().length()!=S.length())return "";

        return sb.toString();

    }
    public static void main(String[] args) {

        System.out.println(reorganizeString("aabbc"));
//        System.out.println(reorganizeString("bfrbs"));
    }
}
