//$Id$
package com.java.recursion;

public class SqrtOfN {

	
	public static void main(String[] args)
    {
        double x = 5;
        int n = 2;
        findNthRoot(x, n);
    }

	static void findNthRoot(double x, int n)
    {
 
        double low, high;
        if (x >= 0 && x <= 1)
        {
            low = x;
            high = 1;
        }
        else
        {
            low = 1;
            high = x;
        }
        double epsilon = 0.00000001;
 
        // Do binary search
        double guess = (low + high) / 2;
        while (Math.abs((Math.pow(guess, n)) - x) >= epsilon)
        {
        	double test = Math.abs((Math.pow(guess, n)));
        	System.out.println(test - x);
            if (Math.pow(guess, n) > x)
            {
                high = guess;
            }
            else
            {
                low = guess;
            }
            guess = (low + high) / 2;
        }
 
        System.out.println(guess);
    }
	
}
