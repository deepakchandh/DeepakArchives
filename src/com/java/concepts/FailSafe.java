package com.java.concepts;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
public class FailSafe {

    public static void main(String[] args) {
        // example 1
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>(new Integer[] { 1, 7, 9, 11 });
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Integer i = (Integer)itr.next();
            System.out.println(i);
            if (i == 7)
                list.add(15); // It will not be printed
            //This means it has created a separate copy of the collection
        }
        System.out.println("---------------");
        // example 2

        ConcurrentHashMap<String, Integer> m
                = new ConcurrentHashMap<String, Integer>();
        m.put("ONE", 1); //Adding values
        m.put("SEVEN", 7);
        m.put("FIVE", 5);
        m.put("EIGHT", 8);
        // Getting an iterator using map
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            System.out.println(key + " : " + m.get(key));
            // This will reflect in iterator.
            // This means it has not created separate copy of objects
            m.put("NINE", 9);
        }


    }
}
