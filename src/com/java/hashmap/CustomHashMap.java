package com.java.hashmap;

//https://www.devglan.com/java8/hashmap-custom-implementation-java

//https://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
//Collision is a scenario in which two unequal keys or hashcode results in the same index value and this is a very frequent scenario in a large
// collection of objects. Since, each bucket of the HashMap is a LinkedList internally, the existing node(Entry Object) points to the new node in case of a collision.

public class CustomHashMap<K,V> {
    private int capacity = 16;
    private Entry<K,V>[] table;

    public CustomHashMap(){
        table = new Entry[capacity];
    }

    public CustomHashMap(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public void put(K key, V value){
        int index = index(key);
        Entry newEntry = new Entry(key, value, null);
        if(table[index] == null)
            table[index] = newEntry;
        else {
            Entry<K,V> prevNode = null;
            Entry<K,V> currNode = table[index];
            while (currNode != null){
                if(currNode.getKey().equals(key)){
                    currNode.setValue(value);
                    break;
                }
                prevNode = currNode;
                currNode = currNode.getNext();
            }
            if(prevNode != null)
                prevNode.setNext(newEntry);
        }
    }

    public V get(K key){
        V value = null;
        int index = index(key);
        Entry<K, V> entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }

    public void remove(K key){
        int index = index(key);
        Entry previous = null;
        Entry entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)){
                if(previous == null){
                    entry = entry.getNext();
                    table[index] = entry;
                    return;
                }else {
                    previous.setNext(entry.getNext());
                    return;
                }
            }
            previous = entry;
            entry = entry.getNext();
        }

    }

    public void display(){
        for(int i=0;i<capacity;i++){
            if(table[i] != null){
                Entry<K, V> currentNode = table[i];
                while (currentNode != null){
                    System.out.println(String.format("Key is %s and value is %s", currentNode.getKey(), currentNode.getValue()));
                    currentNode = currentNode.getNext();
                }

            }
        }
    }

    private int index(K key){
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }

}
