package com.java.dynamic;

public class MinimumBuckets {

    public static int minimumBuckets(String street) {
        int buckets = 0 ;
        int n = street.length() ;

        for(int i=0;i<n;i++){
            if(street.charAt(i) == 'H'){
                if(i+1 < n && street.charAt(i+1) == '.'){
                    buckets++;
                    i+=2;
                }
                else if(i-1 >=0 && street.charAt(i-1) == '.')
                    buckets++;
                else
                    return -1;
            }
        }
        return buckets;
    }

    public static void main(String[] args) {
        System.out.println(minimumBuckets("H..H"));
    }
}
