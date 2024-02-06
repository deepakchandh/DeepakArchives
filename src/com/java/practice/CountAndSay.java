package com.java.practice;

import java.util.*;

public class CountAndSay {

    public HashMap<Integer, String> map = new HashMap<>();
    public String countAndSay(int n) {
        map.put(1,"1");
        for(int i = 2; i<=n; i++)
        {
            say(i);
        }
        return map.get(n);
    }

    public void say(int n)
    {
        String res = "";
        String s = map.get(n-1);
        int count = 0;
        char ch = s.charAt(0);
        for(int i = 0; i<s.length(); i++)
        {
            char temp = s.charAt(i);
            if(ch == temp)
            {
                count++;
            }
            else
            {
                res = res + String.valueOf(count) + ch;
                count = 1;
                ch = temp;
            }
        }
        res = res + String.valueOf(count) + ch;
        map.put(n , res);
    }

    public static void main(String[] args) {

        CountAndSay say = new CountAndSay();
        System.out.println(say.countAndSay(6));
//        System.out.println(say.countAndSay(6));

    }
}
