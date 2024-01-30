package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

import java.util.Arrays;

public class OneDP {

    // 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs/
    public int climbStairs(int n) {
        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        return climbStairsHelper(n, memo, 0, 0);
    }

    public int climbStairsHelper(int n, int[] memo, int currentStair, int step) {
        if (n == currentStair) return 1;
        if (currentStair > n) return 0;

        if (memo[currentStair] != -1) return memo[currentStair];

        memo[currentStair] = climbStairsHelper(n, memo, currentStair + 1, step + 1) + climbStairsHelper(n, memo, currentStair + 2, step + 1);

        return memo[currentStair];
    }

    // 198. House Robber
    // https://leetcode.com/problems/house-robber/
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robHelper(nums, memo, 0, 0);
    }

    public int robHelper(int[] nums, int[] memo, int index, int current) {
        if (index >= nums.length) return current;
        if (memo[index] != -1) return memo[index];

        memo[index] = Math.max(
                robHelper(nums, memo, index + 2, current + nums[index]),
                robHelper(nums, memo, index + 1, current)
        );

        return memo[index];
    }
}
