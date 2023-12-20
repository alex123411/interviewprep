package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
    // 78. Subsets
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

    // 39. Combination Sum
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

    // 46. Permutations
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

    // 90. Subsets II
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

    // 40. Combination Sum II
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