package com.java.specifics;

import java.util.*;
// https://www.javatpoint.com/java-treemap

// purpose
public class TreeMap1 {

    public static void main(String args[]){
        TreeMap<Integer,String> map=new TreeMap<Integer,String>();
        map.put(102,"Ravi");
        map.put(103,"Rahul");
        map.put(100,"Amit");
        map.put(101,"Vijay");

        for(Map.Entry m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }

        // key features
        // head-map

        //Maintains descending order
        System.out.println("descendingMap: "+map.descendingMap());

        //Returns key-value pairs whose keys are less than or equal to the specified key.
        System.out.println("headMap: "+map.headMap(102,true));

        //Returns key-value pairs whose keys are greater than or equal to the specified key.
        System.out.println("tailMap: "+map.tailMap(102,true));

        //Returns key-value pairs exists in between the specified key.
        System.out.println("subMap: "+map.subMap(100, true, 102, true));

    }
}
