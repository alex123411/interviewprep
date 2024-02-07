package org.alex123411.interviewprep.Solution.DailyTask;

import java.util.*;

public class Medium {
    public int maxWidthOfVerticalArea(int[][] points) {
        int res = 0;

        int[] axis = new int[points.length];
        for (int i = 0; i < points.length; i++) axis[i] = points[i][0];

        Arrays.sort(axis);

        for (int i = 1; i < points.length; i++) {
            res = Math.max(res, axis[i] - axis[i - 1]);
        }
        return res;
    }

    // 91. Decode Ways
    // https://leetcode.com/problems/decode-ways/?envType=daily-question&envId=2023-12-25
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return helper(s.toCharArray(), 0, memo);
    }

    public int helper(char[] chars, int index, int[] memo) {
        if (index >= chars.length) return 1;
        // Check memoization table
        if (memo[index] != -1) {
            return memo[index];
        }
        if (chars[index] == '0') return 0;

        int res = helper(chars, index + 1, memo);

        if (index < chars.length - 1) {
            if (chars[index] == '1' ||
                    (chars[index] == '2' && chars[index + 1] < '7') ||
                    chars[index + 1] == '0'
            ) res += helper(chars, index + 2, memo);
        }

        memo[index] = res;

        return res;
    }

    // 1578. Minimum Time to Make Rope Colorful
    // https://leetcode.com/problems/minimum-time-to-make-rope-colorful/?envType=daily-question&envId=2023-12-27
    public int minCost(String colors, int[] neededTime) {
        int res = 0;
        int i = 1;
        while (i < neededTime.length) {
            int time = neededTime[i - 1];
            int maxTime = neededTime[i - 1];
            while (i < neededTime.length && colors.charAt(i) == colors.charAt(i - 1)) {
                time += neededTime[i];
                maxTime = Math.max(maxTime, neededTime[i]);
                i++;
            }
            res += time - maxTime;
            i++;
        }
        return res;
    }

    // 2610. Convert an Array Into a 2D Array With Conditions
    // https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/?envType=daily-question&envId=2024-01-02


    // 2125. Number of Laser Beams in a Bank
    // https://leetcode.com/problems/number-of-laser-beams-in-a-bank/?envType=daily-question&envId=2024-01-03
    public int numberOfBeams(String[] bank) {
        int res = 0;

        int prev = 0;
        int temp = 0;

        for (int i = 0; i < bank.length; i++) {
            if (i == 0) {
                for (char c : bank[i].toCharArray()) if (c == '1') prev++;
            } else {
                for (char c : bank[i].toCharArray()) if (c == '1') temp++;
                if (temp != 0) {
                    res += prev * temp;
                    prev = temp;
                    temp = 0;
                }
            }
        }

        return res;
    }

    // 2870. Minimum Number of Operations to Make Array Empty
    // https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
    public int minOperations(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);

        for (int n : map.values()) {
            if (n == 1) return -1;
            if (n % 3 == 0) {
                res += n / 3;
            } else {
                res += n / 3;
                if (n % 3 % 2 != 0 && (n % 3 + 3) % 2 != 0) return -1;
                if (n % 3 % 2 == 0) res += n % 3 / 2;
                if ((n % 3 + 3) % 2 == 0) {
                    res = res - 1;
                    res += (n % 3 + 3) / 2;
                }
            }
        }

        return res;
    }

    // 931. Minimum Falling Path Sum
    // https://leetcode.com/problems/minimum-falling-path-sum/
    public int minFallingPathSum(int[][] matrix) {
        int[] res = new int[]{10000};
        int n = matrix.length;
        int[][] memo = new int[n][n];

        for (int i = 0; i < matrix.length; i++) Arrays.fill(memo[i], 1000);

        for (int i = 0; i < n; i++) {
            minFallingPathSumHelper(matrix, 0, i, 0, memo, res);
        }

        return res[0];
    }

    public void minFallingPathSumHelper(int[][] matrix, int i, int j, int currentSum, int[][] memo, int[] res) {
        if (j < 0 || j >= matrix.length) return;
        if (i >= matrix.length) return;

        currentSum += matrix[i][j];
        if (i == matrix.length - 1) {
            res[0] = Math.min(res[0], currentSum);
        }

        if (memo[i][j] <= currentSum) return;
        memo[i][j] = currentSum;

        minFallingPathSumHelper(matrix, i + 1, j, currentSum, memo, res);
        if (j != 0) minFallingPathSumHelper(matrix, i + 1, j - 1, currentSum, memo, res);
        if (j != matrix.length - 1) minFallingPathSumHelper(matrix, i + 1, j + 1, currentSum, memo, res);
    }

    // 1239. Maximum Length of a Concatenated String with Unique Characters
    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
    public int maxLength(List<String> arr) {
        int[] res = new int[1];

        maxLengthHelper(arr, 0, new StringBuilder(), res);
        return res[0];
    }

    public void maxLengthHelper(List<String> arr, int index, StringBuilder current, int[] res) {
        if (index >= arr.size()) return;
        res[0] = Math.max(res[0], current.length());
        int[] chars = new int[26];
        for (char c : current.toString().toCharArray()) chars[c - 'a'] = 1;
        boolean contains = false;
        for (char c : arr.get(index).toCharArray()) {
            contains = chars[c - 'a'] != 0;
            if (contains) break;
        }
        if (contains) return;

        for (int i = index; i < arr.size(); i++) {
            current.append(arr.get(index));
            maxLengthHelper(arr, index + 1, current, res);
        }
    }

    // 451. Sort Characters By Frequency
    // https://leetcode.com/problems/sort-characters-by-frequency
    public String frequencySort(String s) {
        int[][] map = new int[128][2];

        for (int i = 0; i < 128; i++)
            map[i][0] = i;

        for (char c : s.toCharArray())
            map[c][1]++;

        Arrays.sort(map, (a, b) -> Integer.compare(b[1], a[1]));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 128; i++)
            while (map[i][1]-- > 0)
                sb.append((char) map[i][0]);

        return sb.toString();
    }
}
