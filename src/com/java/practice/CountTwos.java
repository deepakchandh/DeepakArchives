//$Id$
package com.java.practice;

public class CountTwos {

	static int count2sinRangeAtDigit(int number, int d)
    {
        int powerOf10 = (int) Math.pow(10, d);
        int nextPowerOf10 = powerOf10 * 10;
        int right = number % powerOf10;
 
        int roundDown = number - number % nextPowerOf10;
        int roundup = roundDown + nextPowerOf10;
 
        int digit = (number / powerOf10) % 10;
 
        if (digit < 2)
        {
            return roundDown / 10;
        }
 
        if (digit == 2)
        {
            return roundDown / 10 + right + 1;
        }
        return roundup / 10;
    }
 
    static int numberOf2sinRange(int number)
    {
        String convert;
        convert = String.valueOf(number);
        String s = convert;
        int len = s.length();
 
        int count = 0;
        for (int digit = 0; digit < len; digit++)
        {
            count += count2sinRangeAtDigit(number, digit);
        }
 
        return count;
    }
 
    public static void main(String[] args)
    {
//        System.out.println(numberOf2sinRange(22));
        System.out.println(numberOf2sinRange(61523));
    }
}
