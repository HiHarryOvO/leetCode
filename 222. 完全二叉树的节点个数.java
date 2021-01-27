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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        // root为第0层
        while (node.left != null) {
            level++;
            node = node.left;
        }
        // 最少2^h个节点，最多2^(h+1)-1个节点
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            // mid向右取整，否则low和high相差1时无法收敛
            int mid = (high - low + 1) / 2 + low;
            if (exist(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exist(TreeNode root, int level, int k) {
        // 最高位恒为1，所以从第二位开始
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}