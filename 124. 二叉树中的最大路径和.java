/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 维护全局最大路径和的值
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 分别计算左右子节点的最大贡献值
        // 注意贡献值不能小于0（因为可以选择不使用左或右节点，此时贡献值为0）
        int leftPathSum = Math.max(dfs(root.left), 0);
        int rightPathSum = Math.max(dfs(root.right), 0);

        // 本节点最大路径和为左右最大贡献值之和加上本节点值
        int path = root.val + leftPathSum + rightPathSum;
        maxSum = Math.max(maxSum, path);

        // 返回的是本节点最大贡献值
        return root.val + Math.max(leftPathSum, rightPathSum);
    }
}