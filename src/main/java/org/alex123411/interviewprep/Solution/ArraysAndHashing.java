package org.alex123411.interviewprep.Solution;

public class ArraysAndHashing {

    public void printArr(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println("\n");
    }

    // 1
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = 0;
        int index1 = 0;
        int index2 = 0;

        int[] res = new int[m + n];


        while (index1 < m && index2 < n) {
            if (nums1[index1] < nums2[index2]) {
                res[i] = nums1[index1];
                index1++;
            } else {
                res[i] = nums2[index2];
                index2++;
            }
            i++;
        }
        if (index1 != m) {
            while (index1 != m) {
                res[i] = nums1[index1];
                index1++;
                i++;
            }
        }
        if (index2 != n) {
            while (index2 != n) {
                res[i] = nums2[index2];
                index2++;
                i++;
            }
        }

        for (int j = 0; j < nums1.length; j++) {
            nums1[j] = res[j];
        }
    }

    // 2
    public int removeElement(int[] nums, int val) {

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }

        }

        return index;

    }

    // 3
    public int removeDuplicates(int[] nums) {

//        Solution with HashMap, works with sorted and unsorted arrays

//        int index = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[i], nums[i]);
//                nums[index] = nums[i];
//                index++;
//            }
//        }
//
//        return index;

//        Solution without HashMap


        int index = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    // 4
    public int removeDuplicatesII(int[] nums) {
        if (nums.length == 1 || nums.length == 2) return nums.length;

        int index = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[index - 1] == nums[i] && nums[index - 2] == nums[i]) {
                continue;
            }
            nums[index] = nums[i];
            index++;
        }

        return index;
    }

    // 5
    public int majorityElement(int[] nums) {

        // Moore Voting Algorithm

        int r = nums[0];
        int c = 1;

        for (int i = 1; i < nums.length; i++) {
            if (r == nums[i]) c++;
            else c--;
            if (c == 0) {
                r = nums[i];
                c = 1;
            }
        }

        return r;
    }

    // 6 Medium
    public void rotate(int[] nums, int k) {
        int d = k % nums.length;
        if (d == -1) return;

        int[] copy = nums.clone();

        for (int i = 0; i < nums.length; i++) {
            if (d - 1 < i) {
                nums[i] = copy[i - d];
                continue;
            }
            nums[i] = copy[nums.length - d + i];
        }
    }

    public void rotateBESTSOLUTION(int[] nums, int k) {
        k %= nums.length;
        int n = nums.length;
        reverseNum(nums, 0, n - 1);
        reverseNum(nums, 0, k - 1);
        reverseNum(nums, k, n - 1);
    }

    public void reverseNum(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 7 Easy
    public int maxProfit(int[] prices) {
        int bestPrice = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (bestPrice - prices[i] >= 0) bestPrice = prices[i];
            if (prices[i] - bestPrice > res) res = prices[i] - bestPrice;
        }
        return res;
    }

    // 8 Medium
    public int maxProfitII(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // 9 Medium

    // jump until step is not >= length of an array -> return true
    // if you are on zero and it the end -> return true
    // if you are on zero return false
    // if you stopped jump one back

    public boolean canJump(int[] nums) {
        int maxIndex = nums[0];
        for (int i = 0; i < nums.length; i++) {
            System.out.println(maxIndex);
            System.out.println(i);
            if (maxIndex < i) return false;
            maxIndex = Math.max(maxIndex, nums[i] + i);
        }
        return true;
    }

    // 10 Medium

    // TASK
    // Get to n - 1 index in the least number of jumps

    // GIVEN
    // It is guaranteed that I can reach the end

    // SOLUTION (use Sliding Window pattern)
    // Returning number of jumps if we are at the end or above the array length
    //

    public int canJumpII(int[] nums) {
        if (nums.length == 1) return 0;

        int left = 0;
        int res = 0;
        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[left] + left >= nums.length) {
                res++;
                return res;
            }

            if (max < nums[i] + i - maxIndex) {
                max = nums[i];
                maxIndex = i;
            }

            if (nums[left] + left == i) {
                left = maxIndex;
                max = nums[i];
                res++;
            }
        }

        return res;
    }
}
