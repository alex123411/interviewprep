package org.alex123411.interviewprep;

import java.util.*;

public class SolutionI {

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

    // 2. Best Time to Buy and Sell Stock
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

    // 3. Contains Duplicate
    // https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate(int[] nums) {
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
    public int[] productExceptSelf(int[] nums) {
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
    public int maxSubArray(int[] nums) {
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
    public int maxProduct(int[] nums) {
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

    // 8. Search in Rotated Sorted Array
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

    // 9. 3Sum
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

    // 10. Two Sum II - Input Array is Sorted
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

    // 12. Valid Anagram
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

    // 13. Group Anagrams
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

    // 14. Valid Palindrome
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

    // 0,1,0,2,1,0,1,3,2,1,2,1
    // 0,0,1,1,2,2,2,2,3,3,3,3

    // 15. Trapping Rain Water
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

    // 16. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {
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
    public int evalRPN(String[] tokens) {
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
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder("(");

        return helperOne(1, 0, sb, n);
    }

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

    // 20. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temperatures) {
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

    // 22. Largest Rectangle in Histogram
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

    // 25. Koko Eating Bananas
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

    // Sliding Window
    // 26. https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
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

    // 27. Longest Repeating Character Replacement
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

    // 28. Invert Binary Tree
    // https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
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

    public int helper(TreeNode root, int[] diameter) {
        if (root == null) return 0;
        int maxLeft = helper(root.left, diameter);
        int maxRight = helper(root.right, diameter);
        diameter[0] = Math.max(diameter[0], maxLeft + maxRight);
        return Math.max(maxLeft, maxRight) + 1;
    }

    public int helperDFS(TreeNode root, int[] res) {
        if (root == null) return 0;
        int maxLeft = helperDFS(root.left, res);
        int maxRight = helperDFS(root.right, res);
        if (Math.abs(maxLeft - maxRight) > 1) res[0] = -1;
        return Math.max(maxLeft, maxRight) + 1;
    }

    // 31. Balanced Binary Tree
    // https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) {
        int[] res = new int[1];
        helperDFS(root, res);
        return res[0] == 0;
    }

    // 32. Same Tree
    // https://leetcode.com/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return (isSameTree(p.right, q.right) && isSameTree(p.left, q.left));
    }

    // 33. Subtree of Another Tree
    // https://leetcode.com/problems/subtree-of-another-tree/
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) return false;
        boolean res = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current.val == subRoot.val && isSameTree(current, subRoot)) return true;
            if (current.right != null) q.add(current.right);
            if (current.left != null) q.add(current.left);
        }

        return res;
    }

    // 34. Lowest Common Ancestor of a Binary Search Tree
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode temp = root;
        while (temp != null) {
            if (p.val > temp.val && q.val > temp.val) temp = temp.right;
            else if (p.val < temp.val && q.val < temp.val) temp = temp.left;
            else return temp;
        }
        return temp;
    }

    // 35. Binary Tree Level Order Traversal
    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    list.add(current.val);
                    if (current.left != null) queue.add(current.left);
                    if (current.right != null) queue.add(current.right);
                }
            }
            res.add(list);
        }

        return res;
    }

    // 36. Binary Tree Right Side View
    // https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    if (current.left != null) queue.add(current.left);
                    if (current.right != null) queue.add(current.right);
                    if (i == size - 1) res.add(current.val);
                }
            }
        }

        return res;
    }

    // 37. Count Good Nodes in Binary Tree
    // https://leetcode.com/problems/count-good-nodes-in-binary-tree/
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        int res = 0;

        return res;
    }

    // 38. Reverse Linked List
    // https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode reversed = new ListNode(head.val);
        while (head.next != null) {

            ListNode temp = new ListNode(head.next.val);
            temp.next = reversed;
            reversed = temp;

            head = head.next;
        }
        return reversed;
    }

    // 39. Merge Two Sorted Lists
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode temp = new ListNode();
        ListNode res = temp;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1.next == null && list2.next != null) temp.next = list2;
        if (list2.next == null && list1.next != null) temp.next = list1;

        return res.next;
    }

    // 40. Reorder List
    // https://leetcode.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode reversed = reverseList(head);
        ListNode copy = new ListNode(head.val);

        int len = 1;

        ListNode temp1 = copy;
        ListNode temp2 = head.next;

        while (temp2 != null) {
            len++;
            temp1.next = new ListNode(temp2.val);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        int i = 2;

        ListNode temp3 = head;
        copy = copy.next;
        while (len != -1) {
            if (i % 2 == 0) {
                temp3.next = new ListNode(reversed.val);
                reversed = reversed.next;
                temp3 = temp3.next;
            } else if (i % 2 == 1) {
                temp3.next = new ListNode(copy.val);
                copy = copy.next;
                temp3 = temp3.next;
            }
            i++;
            len--;
        }
    }

    // 41. Remove Nth Node From End of List
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (n == 1 && head.next == null) return null;

        int i = 0;
        int len = 0;

        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        if (len - n == 0) return head.next;
        temp = head;
        while (temp != null) {
            i++;
            if (i == len - n) {
                if (temp.next != null) {
                    temp.next = temp.next.next;
                }
                break;
            }
            temp = temp.next;
        }

        return head;
    }

    // 42. Subsets
    // https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        subsetsDFS(nums, 0, temp, res);
        return res;
    }

    public void subsetsDFS(int[] nums, int index, List<Integer> temp, List<List<Integer>> res) {
        if (index >= nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[index]);
        subsetsDFS(nums, index + 1, temp, res);
        temp.remove(temp.size() - 1);
        subsetsDFS(nums, index + 1, temp, res);
    }

    // 43. Combination Sum
    // https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combinationSumDFS(candidates, target, 0, 0, temp, res);
        return res;
    }

    public void combinationSumDFS(int[] candidates, int target, int sum, int index, List<Integer> temp,
                                  List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target) return;
        if (index >= candidates.length) return;

        temp.add(candidates[index]);
        combinationSumDFS(candidates, target, sum + candidates[index], index, temp, res);

        temp.remove(temp.size() - 1);
        combinationSumDFS(candidates, target, sum, index + 1, temp, res);
    }

    // 44. Maximum Product of Two Elements in an Array
    // https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/?envType=daily-question&envId=2023-12-12
    public int maxProductDAILYCHALLENEGE(int[] nums) {
        int l = 0, max = 0;
        for (int r = 1; r < nums.length; r++) {
            max = Math.max(max, (nums[l] - 1) * (nums[r] - 1));
            if (nums[l] < nums[r]) l = r;
        }
        return max;
    }

    // 45. Permutations
    // https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        permuteHelper(nums, nums.length, current, res);
        return res;
    }

    public void permuteHelper(int[] nums, int len, List<Integer> current,
                              List<List<Integer>> res) {
        if (current.size() == len) {
            res.add(new ArrayList<>(current));
            return;
        }

        int[] copy = nums.clone();

        for (int i = 0; i < len; i++) {
            if (copy[i] == -11) continue;
            int temp = copy[i];

            current.add(temp);
            copy[i] = -11;
            permuteHelper(copy, len, current, res);
            copy[i] = temp;
            current.remove(current.size() - 1);
        }
    }

    // 46. Subsets II
    // https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subsetsWithDupHelper(nums, 0, ans, list);
        return ans;
    }

    public void subsetsWithDupHelper(
            int[] nums,
            int idx,
            List<List<Integer>> ans,
            List<Integer> list
    ) {
        ans.add(new ArrayList<>(list));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, ans, list);
            list.remove(list.size() - 1);
        }
    }

    // 47. Combination Sum II
    // https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        combinationSum2Helper(candidates, target, 0, 0, new ArrayList<>(), res);
        return res;
    }

    public void combinationSum2Helper(int[] candidates, int target, int sum, int index,
                                      List<Integer> temp,
                                      List<List<Integer>> res) {

        if (sum == target) res.add(new ArrayList<>(temp));
        if (sum > target || index >= candidates.length) return;

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            temp.add(candidates[i]);
            combinationSum2Helper(candidates, target,
                    sum + candidates[i], i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
