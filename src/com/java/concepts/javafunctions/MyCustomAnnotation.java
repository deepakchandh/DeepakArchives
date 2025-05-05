package com.java.concepts.javafunctions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyCustomAnnotation {

    String value();
    int count() default 1;

}


class MyClass {

    @MyCustomAnnotation(value = "Hello, World!", count = 3)
    public void myAnnotatedMethod() {
        System.out.println("Inside the annotated method.");
    }
    public void nonAnnotatedMethod() {
        System.out.println("Inside the non-annotated method.");
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        // Get the class object
        Class<?> cls = myClass.getClass();
        // Get the method object
        Method method = null;
        try {
            method = cls.getMethod("nonAnnotatedMethod");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // Check if the method has the custom annotation
        if (method != null && method.isAnnotationPresent(MyCustomAnnotation.class)) {
            // Get the annotation instance
            MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);
            // Access the annotation elements
            String value = annotation.value();
            int count = annotation.count();
            // Use the annotation elements
            System.out.println("Annotation value: " + value);
            System.out.println("Annotation count: " + count);
        } else {
            System.out.println("Method is not annotated with @MyCustomAnnotation.");
        }
    }
}

