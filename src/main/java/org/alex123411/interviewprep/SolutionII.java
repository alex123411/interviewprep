package org.alex123411.interviewprep;

import java.util.*;

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

    // 26. Time Based Key Value Store
    // https://leetcode.com/problems/time-based-key-value-store/
    class Pair {
        int timestamp;
        String val;

        Pair(int timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }
    }

    class TimeMap {

        Map<String, ArrayList<Pair>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                ArrayList<Pair> list = new ArrayList<>();
                list.add(new Pair(timestamp, value));
                map.put(key, list);
            } else map.get(key).add(new Pair(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";

            ArrayList<Pair> list = map.get(key);

            int l = 0, r = list.size() - 1;
            int mid;
            String res = "";
            while (l <= r) {
                mid = (l + r) / 2;
                Pair temp = list.get(mid);

                if (temp.timestamp == timestamp) return temp.val;
                if (timestamp > list.get(l).timestamp) res = list.get(l).val;
                if (temp.timestamp < timestamp) l = mid + 1;
                if (temp.timestamp > timestamp) r = mid - 1;
            }

            return res;
        }
    }

    // 17. Min Stack
    // https://leetcode.com/problems/min-stack/
    class MinStack {
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
