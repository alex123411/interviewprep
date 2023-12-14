package org.alex123411.interviewprep;

import java.util.LinkedList;
import java.util.Queue;

// Binary tree
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean add(int val) {
        TreeNode tempNode = this;
        while (true) {
            if (tempNode.val <= val) {
                if (tempNode.right == null) {
                    tempNode.right = new TreeNode(val);
                    return true;
                }
                tempNode = tempNode.right;
            } else {
                if (tempNode.left == null) {
                    tempNode.left = new TreeNode(val);
                    return true;
                }
                tempNode = tempNode.left;
            }
        }
    }

    public void printDFS(TreeNode root) {
        if (root == null) return;
        System.out.printf("%d ", root.val);
        printDFS(root.left);
        printDFS(root.right);
    }

    public void printBFS() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Get the number of nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");

                if (current.left != null) {
                    queue.add(current.left);
                }

                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            System.out.println(); // Add a new line after printing nodes at the current level
        }
    }
}
