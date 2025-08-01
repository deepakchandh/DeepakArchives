package com.java.Greedy;

public class ValidParanthesis {

    public static boolean checkValidString(String s) {

        int leftMin = 0, leftMax = 0;
        for(char c: s.toCharArray()){
            if(c=='('){
                leftMin++;
                leftMax++;
            }
            else if(c==')'){
                leftMin--;
                leftMax--;
            }else{
                leftMin--;
                leftMax++;
            }
            if (leftMax < 0) {
                return false;
            }
            if (leftMin < 0) {
                leftMin = 0;
            }
        }
        return leftMin == 0;

    }

    public static void main(String[] args) {
        System.out.println(checkValidString("((**)"));
    }
}
