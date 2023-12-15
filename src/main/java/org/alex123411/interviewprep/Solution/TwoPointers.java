package org.alex123411.interviewprep.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {
    // 125. Valid Palindrome
    // https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length - 1;
        while (l < r) {
            if (arr[l] != arr[r]) return false;
            l++;
            r--;
        }
        return true;
    }

    // 167. 3Sum
    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSumTwo(int[] numbers, int target) {
        int[] res = new int[2];

        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            }
            ;

            if (sum > target) r -= 1;
            if (sum < target) l += 1;
        }

        return res;
    }

    // 15. Two Sum II - Input Array is Sorted
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(nums);

        int a;

        for (int i = 0; i < nums.length; i++) {

            a = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = a + nums[l] + nums[r];

                if (sum > 0) r -= 1;
                if (sum < 0) l += 1;
                if (sum == 0) {
                    list.add(new ArrayList<>(List.of(a, nums[l], nums[r])));
                    l += 1;
                    while (nums[l] == nums[l - 1] && l < r) l += 1;
                }


            }
        }

        return list;
    }

    // 11. Container With Most Water
    // https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = calcVolume(height, l, r);

        while (l < r) {
            if (height[l] >= height[r]) r -= 1;
            else if (height[l] < height[r]) l += 1;

            max = Math.max(max, calcVolume(height, l, r));
        }
        return max;
    }

    public int calcVolume(int[] height, int l, int r) {
        int tempHeight = Math.min(height[l], height[r]);
        int tempWidth = r - l;

        return tempHeight * tempWidth;
    }

    // 0,1,0,2,1,0,1,3,2,1,2,1
    // 0,0,1,1,2,2,2,2,3,3,3,3
    // 42. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int res = 0;

        int l = 0, r = height.length - 1;
        int lMax = height[l], rMax = height[r];

        while (l < r) {
            if (lMax < rMax) {
                l += 1;
                lMax = Math.max(lMax, height[l]);
                res += lMax - height[l];
            } else {
                r -= 1;
                rMax = Math.max(rMax, height[r]);
                res += rMax - height[r];
            }
        }

        return res;
    }
}