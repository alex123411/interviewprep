package org.alex123411.interviewprep.Solution;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    // 121. Best Time to Buy and Sell Stock
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) minPrice = prices[i];
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
        }

        return maxProfit;
    }

    // 3. Longest Substring Without Repeating Characters
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return 1;
        int res = 0, l = 0, r = 0;
        Map<Character, Integer> map = new HashMap<>();

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            r = i;
            if (map.containsKey(c) && map.get(c) >= l) {
                l = map.get(c);
            }
            map.put(c, i);
            res = Math.max(res, r - l);
        }

        return res;
    }

    // 424. Longest Repeating Character Replacement
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    public int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        char[] arr = s.toCharArray();
        int l = 0, max = 0;
        // shifting right until the length of the window - most frequent character <= k
        int maxCount = 1;

        for (int i = 0; i < s.length(); i++) {
            counts[arr[i] - 'A']++;
            maxCount = Math.max(maxCount, counts[arr[i] - 'A']);

            while ((i - l + 1) - maxCount > k) {
                counts[arr[l] - 'A']--;
                l++;
            }

            max = Math.max(max, i - l + 1);

        }
        return max;
    }
}