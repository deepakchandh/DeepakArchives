//$Id$
package com.java.generics;

public class TestGenerics3 {
	public static void main(String args[]){  
		MyGen<Integer> m=new MyGen<Integer>();  
		m.add(2);  
		//m.add("vivek");//Compile time error  
		System.out.println(m.get());  
		
		MyGen<String> list = new MyGen<String>();
		list.add("chandh");
		System.out.println(list.get());
	}
}
