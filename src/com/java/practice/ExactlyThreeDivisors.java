package com.java.practice;

import java.util.Arrays;

//https://www.geeksforgeeks.org/numbers-exactly-3-divisors/
public class ExactlyThreeDivisors {

    public static void numbersWith3Divisors(int N)
    {
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int p = 2; p * p <= N; p++) {

            // If prime[p] is not changed,
            // then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= N; i += p)
                    prime[i] = false;
            }
        }

        // print squares of primes upto n
        System.out.println("Numbers with 3 divisors : ");
        for (int i = 0; i * i <= N; i++)
            if (prime[i])
                System.out.print(i * i + " ");
    }

    public static void main(String[] args) {
        numbersWith3Divisors(300);
    }
}
