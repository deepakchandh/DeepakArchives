package com.java.stack;

import java.util.Stack;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();

        for(String part: parts){
            if(part.equals("") || part.equals(".")){
                continue;
            }
            else if(part.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else {
                stack.push(part); // valid directory name
            }
        }
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.length() == 0 ? "/" : result.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/user/Documents/../Pictures"));
    }
}
