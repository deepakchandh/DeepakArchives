package com.java.lc;

public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;

        int maxx = 0;

        while(right > left){

            int w = right - left;
            int ht = Math.min(height[left], height[right]);
            int area = w * ht;
            maxx = Math.max(area, maxx);

            if(height[right] > height[left]) left++;
            else if(height[left] > height[right]) right--;
            else{
                left++;
                right --;
            }
        }
        return maxx;
    }


    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
