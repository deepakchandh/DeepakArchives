package com.java.practice;

import java.util.Scanner;

public class SticklerThief {

    public static int maxLoot(int array[], int n)
    {

        int include = array[0];
        int exclude = 0;
        int newexclude = 0;
        for(int i = 1; i<n; i++){
            if(include > exclude){
                newexclude = include;
            }else{
                newexclude = exclude;
            }
            include = exclude + array[i];
            exclude = newexclude;
        }
        System.out.println(Math.max(include,exclude));

    return 0;

    }

    public static void main(String[] args) {
        int hval[] = { 6, 7, 1, 3, 8, 2, 4 };
        int n = hval.length;
        System.out.println("Maximum loot possible : "
                + maxLoot(hval, n));
    }
}
