//$Id$
package com.java.practice;

public class SquareMatrix {

	public static void main(String args[]){
//		Scanner s = new Scanner(System.in);
		int n=3;
		int k=2;
		int mat[][] = {{1, 2,3},
				{4,5,6},
				{7,8,9}
		};
		int a[] = new int[n];
		//store sum of first k elements column by column in all n columns
		for(int i=0;i<n;i++){
			for(int j=0;j<k;j++){
				a[i]+=mat[j][i];
			}
		}
		for(int i=k;i<n;i++){
			int sum=0;
			//sum of first k cloumns
			for(int j=0;j<k;j++){
				sum=sum+a[j];
			}

			System.out.print(sum+" ");

			//sum of remaining n-k columns
			for(int j=k;j<n;j++){
				sum=sum+a[j]-a[j-k];
				System.out.print(sum+" ");
			}
			System.out.println();

			//updating array by adding i-th element of each column and removing i-k-th element
			for(int j=0;j<n;j++){
				a[j]=a[j]+mat[i][j]-mat[i-k][j];
			}
		}
		int sum=0;
		for(int j=0;j<k;j++){
			sum=sum+a[j];
		}
		System.out.print(sum+" ");
		for(int j=k;j<n;j++){
			sum=sum+a[j]-a[j-k];
			System.out.print(sum+" ");
		}
	}

}
