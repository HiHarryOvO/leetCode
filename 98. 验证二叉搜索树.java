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
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        long inOrder = (long) Integer.MIN_VALUE - 1;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 每个元素都会被按中序pop一次形成遍历
			// 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            root = stack.pop();
            if (root.val <= inOrder) {
                return false;
            }
            inOrder = root.val;
            root = root.right;
        }
        return true;
    }
}
