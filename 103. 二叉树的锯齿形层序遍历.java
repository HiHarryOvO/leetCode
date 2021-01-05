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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        queue.offer(root);
        // 加一个判断顺序的变量
        boolean leftToRight = true;
        while(!queue.isEmpty()) {
            // 改用双端队列
            Deque<Integer> level = new LinkedList<>();
            int currLevelSize = queue.size();
            for (int i = 0; i < currLevelSize; i++) {
                TreeNode p = queue.poll();
                if (leftToRight) {
                    level.addLast(p.val);
                } else {
                    level.addFirst(p.val);
                }
                if (p.left != null) {
                    queue.offer(p.left);
                }
                if (p.right != null) {
                    queue.offer(p.right);
                }
            }
            ans.add(new LinkedList<Integer>(level));
			// 更新顺序
            leftToRight = !leftToRight;
        }
        return ans;
    }
}
