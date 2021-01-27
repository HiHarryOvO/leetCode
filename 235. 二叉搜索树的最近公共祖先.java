/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int diff = (root.val - p.val) * (root.val - q.val);
        if (diff <= 0) {
            return root;
        } else {
            if (root.val - p.val < 0) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return lowestCommonAncestor(root.left, p, q);
            }
        }
    }
}