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

// 迭代
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // root非空，不考虑边界情况
        Deque<TreeNode> stack = new LinkedList<>();
        int index = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            
            // 判断部分
            index++;
            if (index == k) {
                return root.val;
            }

            root = root.right;
        }
        return 0;
    }
}

// 递归
class Solution {
    List<Integer> res = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        inorder(root);
        return res.get(k-1);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }
}