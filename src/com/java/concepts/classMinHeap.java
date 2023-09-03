package com.java.concepts;

public class classMinHeap {

    private int[] heapData;
    private int sizeOfHeap;
    private int heapMaxSize;

    private static final int FRONT = 1;

    public classMinHeap(int heapMaxSize)  {
        this.heapMaxSize = heapMaxSize;
        this.sizeOfHeap = 0;
        heapData = new int[this.heapMaxSize + 1];
        heapData[0] = Integer.MIN_VALUE;
    }




}
