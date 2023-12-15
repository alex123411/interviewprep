package org.alex123411.interviewprep.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Stack {
    // 20. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        char[] arr = s.toCharArray();

        for (char value : arr) {
            if (stack.empty()) stack.push(value);
            else {
                char c = stack.peek();
                if (c == '{' && value == '}') stack.pop();
                else if (c == '[' && value == ']') stack.pop();
                else if (c == '(' && value == ')') stack.pop();
                else stack.push(value);
            }
        }

        return stack.empty();
    }

    // 150. Evaluate Reverse Polish Notation
    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public int evalRPN(String[] tokens) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        for (String s : tokens) {
            if (Objects.equals(s, "*")) {
                stack.push(stack.pop() * stack.pop());

                continue;
            }
            if (Objects.equals(s, "+")) {
                stack.push(stack.pop() + stack.pop());

                continue;
            }
            if (Objects.equals(s, "/")) {
                int val1 = stack.pop();
                int val2 = stack.pop();

                stack.push(val2 / val1);

                continue;
            }
            if (Objects.equals(s, "-")) {
                int val1 = stack.pop();
                int val2 = stack.pop();

                stack.push(val2 - val1);

                continue;
            }
            stack.push(Integer.parseInt(s));
        }

        return stack.pop();
    }

    // 1 [()]
    // 2 [()() , (())]
    // 3 ["((()))","(()())","(())()","()(())","()()()"]
    // 4 ["
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder("(");

        return helperOne(1, 0, sb, n);
    }

    // 22. Generate Parentheses
    // https://leetcode.com/problems/generate-parentheses/

    // Len will be always n*2
    // Starts always from '('
    // Ends always with ')'

    public List<String> helperOne(int open, int closed, StringBuilder s, int n) {
        List<String> list = new ArrayList<>();

        if (closed > open || open > n) return new ArrayList<>();

        if (s.toString().length() == n * 2 - 1) {
            s.append(')');
            return new ArrayList<>(List.of(s.toString()));
        }

        StringBuilder copy = new StringBuilder(s);
        list.addAll(helperOne(open + 1, closed, s.append('('), n));
        list.addAll(helperOne(open, closed + 1, copy.append(')'), n));

        return list;
    }

    // 739. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            if (!stack.empty()) {
                int val = stack.peek();
                while (!stack.empty() && temperatures[val] < temperatures[i]) {
                    res[val] = i - val;
                    stack.pop();
                    val = stack.peek();
                }
            }

            stack.push(i);
        }

        return res;
    }

    // 853. Car Fleet
    // https://leetcode.com/problems/car-fleet/
    public int carFleet(int target, int[] position, int[] speed) {
        int res = 0;

        double[] timeArr = new double[target];
        for (int i = 0; i < position.length; i++) {
            timeArr[position[i]] = (double) (target - position[i]) / speed[i];
        }

        double prev = 0.0;
        for (int i = target - 1; i >= 0; i--) {
            double cur = timeArr[i];

            if (cur > prev) {
                res++;
                prev = cur;
            }

        }

        return res;
    }

    // 84. Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] heights) {
        int max = 0;

        // Brute force too slow O(n^2)
//        for (int i = 0; i < heights.length; i++) {
//            int l = heights[i];
//            for (int j = i; j < heights.length; j++) {
//                l = Math.min(l, heights[j]);
//                if(l == 0) break;
//                max = Math.max(max, l*(j-i+1));
//            }
//        }

        java.util.Stack<int[]> stack = new java.util.Stack<>();

        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] temp = stack.pop();

                int index = temp[0], height = temp[1];
                max = Math.max(max, height * (i - index));
                start = index;
            }
            stack.push(new int[]{start, heights[i]});
        }

        while (!stack.isEmpty()) {
            max = Math.max(max, stack.peek()[1] * (heights.length - stack.peek()[0]));
            stack.pop();
        }

        return max;
    }

    // 155. Min Stack
    // https://leetcode.com/problems/min-stack/
    public class MinStack {
        private final List<Integer> mins;
        private final List<Integer> stack;

        public MinStack() {
            stack = new java.util.LinkedList<>();
            mins = new LinkedList<>();
        }

        public void push(int val) {
            if (mins.isEmpty()) mins.add(0, val);
            else if (mins.get(0) >= val) mins.add(0, val);

            stack.add(0, val);
        }

        public void pop() {
            if (Objects.equals(mins.get(0), stack.get(0))) mins.remove(0);
            stack.remove(0);
        }

        public int top() {
            return stack.get(0);
        }

        public int getMin() {
            return mins.get(0);
        }
    }
}
