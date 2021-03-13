/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 解法1：递归
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        // 递归终止条件
        if (root == null) {
            return null;
        }
        // 递归主体
        TreeNode leftRoot = mirrorTree(root.left);
        TreeNode rightRoot = mirrorTree(root.right);
        root.left = rightRoot;
        root.right = leftRoot;
        return root;
    }
}

// 解法2：辅助栈
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }

        return root;
    }
}