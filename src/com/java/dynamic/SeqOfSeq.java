//$Id$
package com.java.dynamic;
//https://www.geeksforgeeks.org/sequences-given-length-every-element-equal-twice-previous/
public class SeqOfSeq {

	static int numberSequence(int m, int n){

		int T[][]=new int[m+1][n+1];
        for (int i=0; i<m+1; i++)
        {
            for (int j=0; j<n+1; j++)
            {
                if (i == 0 || j == 0 || i<j)
                    T[i][j] = 0;

                else if (j == 1)
                    T[i][j] = i;
                else
                    T[i][j] = T[i-1][j] + T[i/2][j-1];
            }
        }
        return T[m][n];
	}
	
	
	public static void main(String args[])
	{
		System.out.println(numberSequence(10, 4));;
	}

}
