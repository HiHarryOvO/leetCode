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
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return res;
    }

    public void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        sum -= node.val;
        if (sum == 0 && node.left == null && node.right == null && path.size() != 0) {
            res.add(new LinkedList<Integer>(path));
        } 
        dfs(node.left, sum);
        dfs(node.right, sum);
        path.remove(path.size() - 1);
    }
}
