package org.alex123411.interviewprep;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SolutionII {
    // 23. Binary Search
    // https://leetcode.com/problems/binary-search/submissions/
    public static int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;
            if (target == nums[mid]) return mid;
            if (target < nums[mid]) r = mid - 1;
            if (target > nums[mid]) l = mid + 1;
        }

        return -1;
    }

    // 17. Min Stack
    // https://leetcode.com/problems/min-stack/
    static class MinStack {
        private final List<Integer> mins;
        private final List<Integer> stack;

        public MinStack() {
            stack = new LinkedList<>();
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
