package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BinarySearch {
    // 704. Binary Search
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

    // 74. Search a 2D Matrix
    // https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix[0].length; // length
        int n = matrix.length; // height

        int xL = 0, xR = m - 1;
        int yL = 0, yR = n - 1;
        int arrIndex = -1;

        while (yL <= yR) {
            int yMid = (yL + yR) / 2;
            int[] arr = matrix[yMid];
            if (arr[0] <= target && target <= arr[m - 1]) {
                arrIndex = yMid;
                break;
            }

            if (arr[0] > target) yR = yMid - 1;
            if (arr[m - 1] < target) yL = yMid + 1;
        }
        if (arrIndex == -1) return false;

        int[] arr = matrix[arrIndex];
        while (xL <= xR) {
            int xMid = (xL + xR) / 2;
            if (arr[xMid] == target) return true;
            if (arr[xMid] > target) xR = xMid - 1;
            if (arr[xMid] < target) xL = xMid + 1;
        }

        return false;
    }

    // 875. Koko Eating Bananas
    // https://leetcode.com/problems/koko-eating-bananas/
    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];

        // Iterate through the array to find the maximum value
        for (int i = 1; i < piles.length; i++) {
            if (piles[i] > max) {
                max = piles[i];
            }
        }

        int r = max;
        int l = 1;
        int res = r;

        while (l <= r) {
            int k = (l + r) / 2;
            int i = 0;
            for (double pile : piles) {
                i += Math.ceil(pile / k);
            }
            if (i <= h) {
                res = Math.min(k, res);
                r = k - 1;
            }
            if (i > h) l = k + 1;
        }

        return res;
    }
    // 153. Find Minimum in Rotated Sorted Array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

    // You must write an algorithm that runs in O(log n) time.

    // The answer will be nums[rot]
    // 3,4,5,1,2
    // 4,5,6,7,8,0,1,2
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (right - left > 1) {
            mid = (left + right) / 2;

            if (nums[right] < nums[mid]) left = mid;
            else if (nums[right] > nums[mid]) right = mid;
        }

        return Math.min(nums[right], nums[left]);
    }

    // 33. Search in Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/

    // 9, 10, 11, 12, 13, 14, 4, 5, 7, 8
    // 12, 13, 4, 5, 7, 8, 9, 10, 11
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = (right + left) / 2;

            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }

    // 981. Time Based Key Value Store
    // https://leetcode.com/problems/time-based-key-value-store/
    public class Pair {
        int timestamp;
        String val;

        Pair(int timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }
    }

    public class TimeMap {

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
}
