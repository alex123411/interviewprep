package org.alex123411.interviewprep;

import com.sun.source.tree.Tree;

import java.util.*;

public class SolutionI {

    // 1. Two Sum
    // https://leetcode.com/problems/two-sum/
    public static int[] twoSum(int[] nums, int target) {
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

    // 2. Best Time to Buy and Sell Stock
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) minPrice = prices[i];
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
        }

        return maxProfit;
    }

    // 3. Contains Duplicate
    // https://leetcode.com/problems/contains-duplicate/
    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) return true;
            map.put(num, num);
        }

        return false;
    }

    // 4. Product of Array Except Self
    // https://leetcode.com/problems/product-of-array-except-self/

    // You must write an algorithm that runs in O(n) time
    // and without using the division operation.

    // 1 2 3 4 -> 24 12 8 6 (prefix x postfix)
    // 1 2 6 24 - prefix
    // 24 24 12 4 - postfix
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = 1;
        }
        int prefix = 1;
        int postfix = 1;

        for (int i = 1; i < nums.length; i++) {
            prefix *= nums[i - 1];
            res[i] *= prefix;

            postfix *= nums[nums.length - i];
            res[nums.length - i - 1] *= postfix;
        }

        return res;
    }


    // 5. Maximum Subarray
    // https://leetcode.com/problems/maximum-subarray/
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = nums[0];

        for (int num : nums) {
            if (sum < 0) sum = 0;
            sum += num;
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    // 6. Maximum Product Subarray
    // https://leetcode.com/problems/maximum-product-subarray/

    // 2 3 -1 4 3
    public static int maxProduct(int[] nums) {
        int left = 1, right = 1;
        int maxProd = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (left == 0) left = 1;
            if (right == 0) right = 1;

            left *= nums[i];
            right *= nums[nums.length - i - 1];

            maxProd = Math.max(maxProd, Math.max(left, right));
        }

        return maxProd;
    }

    // 7. Find Minimum in Rotated Sorted Array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

    // You must write an algorithm that runs in O(log n) time.

    // The answer will be nums[rot]
    // 3,4,5,1,2
    // 4,5,6,7,8,0,1,2
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (right - left > 1) {
            mid = (left + right) / 2;

            if (nums[right] < nums[mid]) left = mid;
            else if (nums[right] > nums[mid]) right = mid;
        }

        return Math.min(nums[right], nums[left]);
    }

    // 8. Search in Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/

    // 9, 10, 11, 12, 13, 14, 4, 5, 7, 8
    // 12, 13, 4, 5, 7, 8, 9, 10, 11
    public static int search(int[] nums, int target) {
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

    // 9. 3Sum
    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public static int[] twoSumTwo(int[] numbers, int target) {
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

    // 10. Two Sum II - Input Array is Sorted
    // https://leetcode.com/problems/3sum/
    public static List<List<Integer>> threeSum(int[] nums) {
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
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = calcVolume(height, l, r);

        while (l < r) {
            if (height[l] >= height[r]) r -= 1;
            else if (height[l] < height[r]) l += 1;

            max = Math.max(max, calcVolume(height, l, r));
        }
        return max;
    }

    public static int calcVolume(int[] height, int l, int r) {
        int tempHeight = Math.min(height[l], height[r]);
        int tempWidth = r - l;

        return tempHeight * tempWidth;
    }

    // 12. Valid Anagram
    // https://leetcode.com/problems/valid-anagram/
    public static boolean isAnagram(String s, String t) {
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

    // 13. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/
    public static List<List<String>> groupAnagrams(String[] strs) {

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

    // 14. Valid Palindrome
    // https://leetcode.com/problems/valid-palindrome/
    public static boolean isPalindrome(String s) {
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

    // 0,1,0,2,1,0,1,3,2,1,2,1
    // 0,0,1,1,2,2,2,2,3,3,3,3

    // 15. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/
    public static int trap(int[] height) {
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

    // 16. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
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

    // 18. Evaluate Reverse Polish Notation
    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

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

    // 19. Generate Parentheses
    // https://leetcode.com/problems/generate-parentheses/

    // Len will be always n*2
    // Starts always from '('
    // Ends always with ')'

    // 1 [()]
    // 2 [()() , (())]
    // 3 ["((()))","(()())","(())()","()(())","()()()"]
    // 4 ["
    public static List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder("(");

        return helperOne(1, 0, sb, n);
    }

    public static List<String> helperOne(int open, int closed, StringBuilder s, int n) {
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

    // 20. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        Stack<Integer> stack = new Stack<>();
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

    // 21. Car Fleet
    // https://leetcode.com/problems/car-fleet/
    public static int carFleet(int target, int[] position, int[] speed) {
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

    // 22. Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    public static int largestRectangleArea(int[] heights) {
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

        Stack<int[]> stack = new Stack<>();

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

    // 24. Search a 2D Matrix
    // https://leetcode.com/problems/search-a-2d-matrix/
    public static boolean searchMatrix(int[][] matrix, int target) {
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

    // 25. Koko Eating Bananas
    // https://leetcode.com/problems/koko-eating-bananas/
    public static int minEatingSpeed(int[] piles, int h) {
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

    // Sliding Window
    // 26. https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
    public static int lengthOfLongestSubstring(String s) {
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

    // 27. Longest Repeating Character Replacement
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    public static int characterReplacement(String s, int k) {
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

    // 28. Invert Binary Tree
    // https://leetcode.com/problems/invert-binary-tree/
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 29. Maximum Depth of Binary Tree
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft, maxRight) + 1;
    }

    // 30. Diameter of Binary Tree
    // https://leetcode.com/problems/diameter-of-binary-tree/
    public int diameterOfBinaryTree(TreeNode root) {
        // Need to find deepest left + deepest right
        int[] diam = new int[1];
        helper(root, diam);
        return diam[0];
    }
    public int helper(TreeNode root, int[] diameter){
        if (root == null) return 0;
        int maxLeft = helper(root.left, diameter);
        int maxRight = helper(root.right, diameter);
        diameter[0] = Math.max(diameter[0], maxLeft+maxRight);
        return Math.max(maxLeft, maxRight) + 1;
    }

    // 31. Balanced Binary Tree
    // https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) {
        int[] res = new int[1];
        helperDFS(root, res);
        return res[0] == 0;
    }
    public int helperDFS(TreeNode root, int[] res){
        if (root == null) return 0;
        int maxLeft = helperDFS(root.left, res);
        int maxRight = helperDFS(root.right, res);
        if(Math.abs(maxLeft - maxRight) > 1) res[0] = -1;
        return Math.max(maxLeft, maxRight) + 1;
    }
}
