package org.alex123411.interviewprep.Solution.DailyTask;

import java.math.BigDecimal;
import java.util.*;

public class Easy {
    // 2706. Buy Two Chocolates
    // https://leetcode.com/problems/buy-two-chocolates/?envType=daily-question&envId=2023-12-20
    public int buyChoco(int[] prices, int money) {
        int[] chocs = new int[2];

        chocs[0] = prices[0];
        chocs[1] = prices[1];

        for (int i = 2; i < prices.length; i++) {
            if (chocs[0] > chocs[1]) {
                chocs[0] = Math.min(chocs[0], prices[i]);
            } else {
                chocs[1] = Math.min(chocs[1], prices[i]);
            }
        }
        return money - chocs[0] - chocs[1] < 0 ? money : money - chocs[0] - chocs[1];
    }

    // 50. Pow(x, n)
    // https://leetcode.com/problems/powx-n/?envType=study-plan-v2&envId=top-interview-150
    public double myPow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while (n != 0) {
            if ((n & 1) != 0) {
                pow *= x;
            }

            x *= x;
            n >>>= 1;

        }

        return pow;
    }

    // 1422. Maximum Score After Splitting a String
    // https://leetcode.com/problems/maximum-score-after-splitting-a-string/
    public int maxScore(String s) {
        int res = 0;
        int sumOne = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) if (c == '1') sumOne++;

        int sumZero = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == '0') sumZero++;
            else sumOne--;
            res = Math.max(res, sumOne + sumZero);
        }

        return res;
    }

    // 1496. Path Crossing
    // https://leetcode.com/problems/path-crossing/?envType=daily-question&envId=2023-12-23
    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean isPathCrossing(String path) {
        Set<Point> set = new HashSet<>();

        int x = 0;
        int y = 0;
        set.add(new Point(x, y));
        for (char c : path.toCharArray()) {
            if (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'W') x++;
            else if (c == 'E') x--;

            Point p = new Point(x, y);

            if (set.contains(p)) return true;
            set.add(p);
        }

        return false;
    }

    public int minOperations(String s) {
        int x = 0, y = 0;

        char[] arr = s.toCharArray();

        // 010101
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0 && arr[i] != '0') x++;
            else if (i % 2 == 1 && arr[i] != '1') x++;
        }

        // 1010101
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0 && arr[i] != '1') y++;
            else if (i % 2 == 1 && arr[i] != '0') y++;
        }

        return Math.min(x, y);
    }

    // 455. Assign Cookies
    // https://leetcode.com/problems/assign-cookies/?envType=daily-question&envId=2024-01-01
    public int findContentChildren(int[] g, int[] s) {
        int res = 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;

        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }

    // 1207. Unique Number of Occurrences
    // https://leetcode.com/problems/unique-number-of-occurrences
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) map.put(i, map.getOrDefault(i, 0) + 1);

        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (set.contains(entry.getValue())) return false;
            set.add(entry.getValue());
        }

        return true;
    }

    // 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return climbStairsHelper(dp, 0, n);
    }

    public int climbStairsHelper(int[] dp, int i, int n) {
        if (i > n) return 0;
        if (i == n) return 1;
        if (dp[i] != -1) return dp[i];

        dp[i] = climbStairsHelper(dp, i + 1, n) + climbStairsHelper(dp, i + 2, n);

        return dp[i];
    }

    // 35. Search Insert Position
    // https://leetcode.com/problems/search-insert-position/
    public int searchInsert(int[] nums, int target) {
        if (nums[0] > target) return 0;
        if (nums[nums.length - 1] < target) return nums.length;

        int l = 0, r = nums.length - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) r = mid - 1;
            if (nums[mid] < target) l = mid + 1;
        }

        return (l + r) / 2 + 1;
    }

    // 58. Length of Last Word
    // https://leetcode.com/problems/length-of-last-word/
    public int lengthOfLastWord(String s) {
        char[] arr = s.toCharArray();
        int res = 0;
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == ' ') i--;
        while (i >= 0 && arr[i] != ' ') {
            res++;
            i--;
        }
        return res;
    }

    // 66. Plus One
    // https://leetcode.com/problems/plus-one/
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            }
            if (digits[i] == 9) {
                while (i >= 0 && digits[i] == 9) {
                    digits[i] = 0;
                    i--;
                }
                if (i >= 0) {
                    digits[i]++;
                    break;
                } else {
                    int[] res = new int[digits.length + 1];
                    Arrays.fill(res, 0);
                    res[0] = 1;
                    return res;
                }
            }
        }
        return digits;
    }

    // 121. Best Time to Buy and Sell Stock
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        if (prices.length == 1) return 0;
        int l = prices[0];
        int max = 0;

        for (int price : prices) {
            if (l >= price) l = price;
            max = Math.max(price - l, max);
        }

        return max;
    }

    // 387. First Unique Character in a String
    // https://leetcode.com/problems/first-unique-character-in-a-string
    public int firstUniqChar(String s) {
        char[] chars = new char[26];
        char[] arr = s.toCharArray();
        for (char c : arr) chars[c - 'a']++;
        for (int i = 0; i < arr.length; i++) if (chars[arr[i] - 'a'] == 1) return i;
        return -1;
    }

    // 169. Majority Element
    // https://leetcode.com/problems/majority-element
    public int majorityElement(int[] nums) {
        int c = 1, x = nums[0];
        for (int i : nums) {
            if (i == x) c++;
            else c--;
            if (c <= 0) {
                x = i;
                c = 1;
            }
        }
        return x;
    }

    // 125. Valid Palindrome
    // https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while(start <= last) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(last);
            if (!Character.isLetterOrDigit(currFirst )) {
                start++;
            } else if(!Character.isLetterOrDigit(currLast)) {
                last--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                last--;
            }
        }
        return true;
    }

    // 2108. Find First Palindromic String in the Array
    // https://leetcode.com/problems/find-first-palindromic-string-in-the-array
    public boolean firstPalindromeHelper(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while(l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
    public String firstPalindrome(String[] words) {
        for (String s : words) {
            if(isPalindrome(s)) return s;
        }
        return "";
    }
}

