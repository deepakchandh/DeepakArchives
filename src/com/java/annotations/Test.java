//$Id$
package com.java.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation{
	int value() default 15;
	String str1();
	String str2();
}

//Declaring annotation
class Hello{
	@MyAnnotation(value=10, str1="deepak", str2="chandh")
	public void sayHello(){System.out.println("hello annotation");}
}

//Accessing annotation
class Test{
	public static void main(String args[])throws Exception{

		Hello h=new Hello();
		Method m=h.getClass().getMethod("sayHello");

		MyAnnotation manno=m.getAnnotation(MyAnnotation.class);
		System.out.println("value is: "+manno.str1());
	}}
