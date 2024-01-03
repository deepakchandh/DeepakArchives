package com.java.practice;
//https://www.scaler.com/topics/factors-of-a-number-in-java/
public class FactorsOfGivenNumber {


    static void printDivisors(int n)
    {
        // Note that this loop runs till square root
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                if (n/i == i)
                    System.out.print(" "+ i);

                else // Otherwise print both
                    System.out.print(i+" " + n/i + " " );
            }
        }
    }

    public static void main(String[] args) {
        int n = 40; // given number
        printDivisors(n);

        int i; // declaring i in outer scope
        for (i = 1; i * i < n; i++) {
            if (n % i == 0)
                System.out.print(i + " "); // numbers  which left remainder 0
        }
// now decrementing i to calculate quotients
        if (i - (n / i) == 1) {
            i--;
        }
        for (; i >= 1; i--) // quotient of numbers which give remainder as 0
        {
            if (n % i == 0)
                System.out.print(n / i + " ");
        }
    }
}
