//$Id$
package com.java.practice;

public class SortWave {
	
    void swap(int arr[], int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
 
    void sortInWave(int arr[], int n)
    {
        for (int i = 0; i < n; i+=2)
        {
            if (i>0 && arr[i-1] > arr[i] )
                swap(arr, i-1, i);
 
            if (i<n-1 && arr[i] < arr[i+1] )
                swap(arr, i, i + 1);
        }
    }
 
    public static void main(String args[])
    {
        SortWave ob = new SortWave();
        int arr[] = {10, 90, 49, 2, 1, 5, 23};
        int n = arr.length;
        ob.sortInWave(arr, n);
        for (int i : arr)
            System.out.print(i+" ");
    }

}
