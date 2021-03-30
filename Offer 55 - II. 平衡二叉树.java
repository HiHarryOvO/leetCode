/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 无剪枝写法
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

// 有剪枝
class Solution {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = recur(root.left);
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;

        return (Math.abs(right - left) < 2)? Math.max(left, right) + 1: -1;
    }
}