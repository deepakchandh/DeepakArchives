package com.java.practice;
// https://leetcode.com/problems/daily-temperatures/
import java.util.Stack;
// dailyTemperatures
public class minimumTemperature {

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] results = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int index = stack.pop();
                results[index] = i - index;
            }

            stack.push(i);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(dailyTemperatures(new int[] {73,74,75,71,69,72,76,73}));
    }
}

/*
Loop walkthrough:
🔹 i = 0 → temp = 73
Stack is empty → push 0
✅ stack = [0]

🔹 i = 1 → temp = 74
74 > 73 (temps[0])
→ pop 0, res[0] = 1 - 0 = 1
✅ res = [1, 0, 0, 0, 0, 0, 0, 0]
→ push 1
✅ stack = [1]

🔹 i = 2 → temp = 75
75 > 74 (temps[1])
→ pop 1, res[1] = 2 - 1 = 1
✅ res = [1, 1, 0, 0, 0, 0, 0, 0]
→ push 2
✅ stack = [2]

🔹 i = 3 → temp = 71
71 < 75 → push 3
✅ stack = [2, 3]

🔹 i = 4 → temp = 69
69 < 71 → push 4
✅ stack = [2, 3, 4]

🔹 i = 5 → temp = 72
72 > 69 → pop 4, res[4] = 5 - 4 = 1

72 > 71 → pop 3, res[3] = 5 - 3 = 2
→ push 5
✅ res = [1, 1, 0, 2, 1, 0, 0, 0]
✅ stack = [2, 5]

🔹 i = 6 → temp = 76
76 > 72 → pop 5, res[5] = 6 - 5 = 1

76 > 75 → pop 2, res[2] = 6 - 2 = 4
→ push 6
✅ res = [1, 1, 4, 2, 1, 1, 0, 0]
✅ stack = [6]

🔹 i = 7 → temp = 73
73 < 76 → push 7
✅ stack = [6, 7]
 */
