package com.java.concepts;

import java.util.ArrayList;
import java.util.HashSet;

public class test {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(99);
        arrayList.add(877);
        arrayList.add(22);

        HashSet<Integer> aa = new HashSet<>();
        aa.add(99);
        aa.add(88);

        System.out.println(aa);
        aa.remove(99);

//        arrayList.remove(877);

        System.out.println(aa);


    }
}
