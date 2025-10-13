package com.java.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToBit {

    private static int[] toBinary(int num){
//        int[] arr = new int[];

        List<Integer> bits = new ArrayList<>();

        while (num > 0){
            bits.add(num%2);
            num= num/2;
        }
        Collections.reverse(bits);
        return bits.stream().mapToInt(i -> i).toArray();

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(toBinary(23)));

    }
}
