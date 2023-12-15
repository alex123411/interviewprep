package org.alex123411.interviewprep.Solution;

import java.util.*;

public class ArraysAndHashing {

    // 217. Contains Duplicate
    // https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) return true;
            map.put(num, num);
        }

        return false;
    }

    // 242. Valid Anagram
    // https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        for (int val : count) {
            if (val != 0) return false;
        }

        return true;
    }

    // 1. Two Sum
    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            }

            if (!map.containsKey(nums[i])) map.put(nums[i], i);
        }

        return res;
    }

    // 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];

            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            String key = Arrays.toString(count);
            if (map.containsKey(key)) {
                List<String> l = map.get(key);
                l.add(s);
                map.put(key, l);
            } else map.put(key, new ArrayList<>(List.of(s)));
        }

        return new ArrayList<>(map.values());
    }

    // 1436. Destination City
    // https://leetcode.com/problems/destination-city/?envType=daily-question&envId=2023-12-15
    public String destCity(List<List<String>> paths) {
        Set<String> out = new HashSet<>();
        String res = "";
        for (List<String> list : paths) {
            String ou = list.get(0);
            out.add(ou);
        }

        for (List<String> list : paths) {
            String in = list.get(1);
            if (!out.contains(in)) res = in;
        }

        return res;
    }


    // 1464. Maximum Product of Two Elements in an Array
    // https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/?envType=daily-question&envId=2023-12-12
    public int maxProductDAILYCHALLENEGE(int[] nums) {
        int l = 0, max = 0;
        for (int r = 1; r < nums.length; r++) {
            max = Math.max(max, (nums[l] - 1) * (nums[r] - 1));
            if (nums[l] < nums[r]) l = r;
        }
        return max;
    }
}
