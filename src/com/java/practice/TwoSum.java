package com.java.practice;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            if(map.containsKey(target - nums[i])){
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                System.out.println(result[0]+" "+result[1]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static void main(String []args)
    {
        int[] arr = {2,7,11,15};
        int target = 9;
        System.out.println(twoSum(arr,target));

    }
}
