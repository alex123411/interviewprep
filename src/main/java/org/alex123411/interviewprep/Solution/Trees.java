package org.alex123411.interviewprep.Solution;

import org.alex123411.interviewprep.TreeNode;

public class Trees {

    // 1448. Count Good Nodes in Binary Tree
    // https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
    public int goodNodes(TreeNode root) {
        if(root == null) return 0;
        int[] res = new int[]{0};
        goodNodesDFS(root, root.val, res);
        return res[0];
    }

    public void goodNodesDFS(TreeNode root, int max, int[] res) {
        if (root == null) return;

        if (root.val >= max) {
            max = root.val;
            res[0]++;
        }

        goodNodesDFS(root.left, max, res);
        goodNodesDFS(root.right, max, res);
    }
}
