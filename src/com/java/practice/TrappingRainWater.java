//$Id$
package com.java.practice;

import java.util.*;

// #Stack

//https://leetcode.com/problems/trapping-rain-water/solutions/178028/stack-with-explanation-java-python-scala/?orderBy=most_votes
public class TrappingRainWater {

	//https://leetcode.com/problems/trapping-rain-water/description/

	// also see https://leetcode.com/problems/container-with-most-water/submissions/877837628/ this problem


	/*public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		Stack<Integer> stack = new Stack();
		int maxArea = 0;
		for(int right = 0; right <= n; right++){
			int h = right == n ? 0 : heights[right];
			while(!stack.isEmpty() && h < heights[stack.peek()]){
				int curHeight = heights[stack.pop()];
				int left = stack.isEmpty() ? -1 : stack.peek();
				int area = curHeight * (right - left - 1);
				maxArea = Math.max(maxArea, area);
			}
			stack.push(right);
		}
		return maxArea;
	}*/


	static int trap(int[] height, int n) {
		int totalWater = 0;
		Stack<Integer> stack = new Stack<>();
		for(int right=0;right<height.length;right++){
			while(!stack.isEmpty() && height[stack.peek()] < height[right]){
				int bottom = stack.pop();
				if (stack.isEmpty()) {
					break;
				}

				int left = stack.peek();

				int width = right - left - 1;
				int ht = Math.min(height[right], height[left]) - height[bottom];
				int water = width * ht;
				totalWater += water;
			}
			stack.push(right);
		}
		return totalWater;
	}



	// Driver code
	public static void main(String []args)
	{
		
//		int[] arr = {3,0,2,0,4};
		int[] arr = {2,1,5,6,2,3};
		int n = arr.length;
//		System.out.print(maxWater(arr, n));
		System.out.print(trap(arr, n));
//		System.out.print(largestRectangleArea(arr));
	}

	static int maxWater(int[] arr, int n)
	{

		int left = 0;
		int right = n - 1;

		int l_max = 0;
		int r_max = 0;

		int result = 0;
		while (left <= right)
		{

			// We need check for minimum of left
			// and right max for each element
			if(r_max <= l_max)
			{

				// Add the difference between
				// current value and right max at index r
				result += Math.max(0, r_max-arr[right]);

				// Update right max
				r_max = Math.max(r_max, arr[right]);

				// Update right pointer
				right -= 1;
			}
			else
			{

				// Add the difference between
				// current value and left max at index l
				result += Math.max(0, l_max-arr[left]);

				// Update left max
				l_max = Math.max(l_max, arr[left]);

				// Update left pointer
				left += 1;
			}
		}
		return result;
	}

}
