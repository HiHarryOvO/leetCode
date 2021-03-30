/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 自己一开始写的，不简洁
class Solution {
    int depth;

    public int maxDepth(TreeNode root) {
        this.depth = Integer.MIN_VALUE;
        dfs(root, 0);
        return depth;
    }

    void dfs(TreeNode root, int k) {
        if (root == null) {
            depth = (k > depth)? k : depth;
            return;
        }

        dfs(root.left, k + 1);
        dfs(root.right, k + 1);
    }
}

// 递归
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}