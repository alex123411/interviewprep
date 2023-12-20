package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

import org.alex123411.interviewprep.Solution.Structures.ListNode;
import org.alex123411.interviewprep.Solution.Structures.TreeNode;

import java.util.*;
import java.util.LinkedList;

public class Trees {

    // 226. Invert Binary Tree
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

    // 104. Maximum Depth of Binary Tree
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft, maxRight) + 1;
    }

    // 543. Diameter of Binary Tree
    // https://leetcode.com/problems/diameter-of-binary-tree/
    public int diameterOfBinaryTree(TreeNode root) {
        // Need to find the deepest left + the deepest right
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

    // 110. Balanced Binary Tree
    // https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) {
        int[] res = new int[1];
        helperDFS(root, res);
        return res[0] == 0;
    }

    // 100. Same Tree
    // https://leetcode.com/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return (isSameTree(p.right, q.right) && isSameTree(p.left, q.left));
    }

    // 572. Subtree of Another Tree
    // https://leetcode.com/problems/subtree-of-another-tree/
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) return false;
        boolean res = false;
        Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current.val == subRoot.val && isSameTree(current, subRoot)) return true;
            if (current.right != null) q.add(current.right);
            if (current.left != null) q.add(current.left);
        }

        return res;
    }

    // 235. Lowest Common Ancestor of a Binary Search Tree
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

    // 102. Binary Tree Level Order Traversal
    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new java.util.LinkedList<>();
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

    // 199. Binary Tree Right Side View
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

    // 1448. Count Good Nodes in Binary Tree
    // https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
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
