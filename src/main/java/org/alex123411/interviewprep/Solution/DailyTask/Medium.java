package org.alex123411.interviewprep.Solution.DailyTask;

import java.util.Arrays;

public class Medium {
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
}
