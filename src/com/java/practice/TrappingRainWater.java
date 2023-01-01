//$Id$
package com.java.practice;

import java.util.ArrayList;
import java.util.Stack;

public class TrappingRainWater {

	static int stackBasedApproach(int[] height, int n){
		Stack<Integer> stack = new Stack<>();

		int ans = 0;

		for (int i = 0; i < n; i++) {

			// Remove bars from the stack
			// until the condition holds
			while ((!stack.isEmpty()) && (height[stack.peek()] < height[i])) {

				int pop_height = height[stack.peek()];
				stack.pop();

				// If the stack does not have any bars or the popped bar has no left boundary
				if (stack.isEmpty())
					break;

				// Get the distance between the
				// left and right boundary of
				// popped bar
				int distance = i - stack.peek() - 1;

				int min_height = Math.min(height[stack.peek()], height[i]) - pop_height;
				ans += distance * min_height;
			}
			stack.push(i);
		}
		return ans;
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

	// Driver code
	public static void main(String []args)
	{
		
		int[] arr = {3,0,2,0,4};
		int n = arr.length;
//		System.out.print(maxWater(arr, n));
		System.out.print(stackBasedApproach(arr, n));
	}

}
