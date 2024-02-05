package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

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

    // 6. Zigzag Conversion
    // https://leetcode.com/problems/zigzag-conversion/
    public String convert(String s, int numRows) {
        List<StringBuilder> lists = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < numRows; i++) {
            lists.add(new StringBuilder());
        }

        int i = 0;
        while (i < chars.length) {
            int j = 0;

            // first we go down
            while (j < numRows && i < chars.length) {
                lists.get(j).append(chars[i]);
                i++;
                j++;
            }

            j = numRows - 2;
            // second we go on a zigzag from n - 1 to 1
            while (j > 0 && i < chars.length) {
                lists.get(j).append(chars[i]);
                i++;
                j--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (StringBuilder stringbuilder : lists) {
            sb.append(stringbuilder);
        }

        return sb.toString();
    }

    // 347. Top K Frequent Elements
    // https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for (int num : nums)
            counts.merge(num, 1, Integer::sum);

        for (int key : counts.keySet()) {
            int freq = counts.get(key);
            if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(key);
        }

        int[] res = new int[k];
        int ind = 0;
        for (int i = bucket.length; i > 0; i--) {
            if (bucket[i] == null) continue;
            for (int x : bucket[i]) {
                res[ind] = x;
                if (ind == k - 1) return res;
                ind++;
            }
        }
        return res;
    }

    // 238. Product of Array Except Self
    // https://leetcode.com/problems/product-of-array-except-self/
    /*
        [1,2,3,4]
        [1,2,6,24] -> prefix
        [24,24,12,4] <- postfix
        [24,12,8,6] - res (prefix*postfix)
     */
    public int[] productExceptSelf(int[] nums) {
        int prefix = 1, postfix = 1;
        int[] copy = nums.clone();

        for (int i = 1; i < nums.length; i++) {
            prefix *= nums[i - 1];
            copy[i] = prefix;
            System.out.println(prefix);
        }

        for (int i = 1; i < nums.length; i++) {
            postfix *= copy[nums.length - i];
            nums[nums.length - i - 1] = postfix;
        }

        return nums;
    }

    // 128. Longest Consecutive Sequence
    // https://leetcode.com/problems/longest-consecutive-sequence/
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);

        int res = 1;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int temp = num;
                int len = 1;
                while (set.contains(temp + 1)) {
                    temp++;
                    len++;
                }
                res = Math.max(res, len);
                if (len > nums.length / 2) break;
            }
        }

        return res;
    }

    // 36. Valid Sudoku
    // https://leetcode.com/problems/valid-sudoku/
    public boolean isValidSudoku(char[][] board) {
        // My Solution
        int i = 0, j = 0, n = 0;
        Set<Character> set = new HashSet<>();
        while (i < 9 && j < 9) {
            // Validating row
            set = new HashSet<>();
            n = 0;
            while (n < 9) {
                char c = board[i][n];
                if (set.contains(c)) return false;
                if (c != '.') set.add(c);
                n++;
            }
            // Validating col
            set = new HashSet<>();
            n = 0;
            while (n < 9) {
                char c = board[n][j];
                if (set.contains(c)) return false;
                if (c != '.') set.add(c);
                n++;
            }
            i++;
            j++;
        }

        // Validating  blocks
        i = 0;
        j = 0;
        while (j < 9) {
            set = new HashSet<>();
            for (int k = i; k < i + 3; k++) {
                for (int l = j; l < j + 3; l++) {
                    char c = board[k][l];
                    if (set.contains(c)) return false;
                    if (c != '.') set.add(c);
                }
            }
            i += 3;
            if (i >= 9) {
                i = 0;
                j += 3;
            }
        }
        return true;
    }

    // 271. String Encode and Decode
    // https://leetcode.com/problems/encode-and-decode-strings/
    public String encode(List<String> strs) {
        return "";
    }

    public List<String> decode(String str) {
        return new ArrayList<>();
    }
}
