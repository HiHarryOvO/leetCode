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

// 解法1：显式中序遍历
class Solution {
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new LinkedList<>();
        // 获得中序遍历数组
        inorder(root, nums);
        // 从数组中找到错误节点的值
        int[] swapped = findTwoSwapped(nums);
        // 根据值在树中寻找节点
        recover(root, 2, swapped[0], swapped[1]);
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size(), count = 0;
        int[] res = new int[2];
        int left = 0, right = 1;
        // 在循环过程中会发现1到2次顺序错误的情况
        // 如果只有1次，此时两个值找到；如果2次，第一次的left为一个结果，第二次的right为另一个结果
        while (right < n) {
            if (nums.get(left) > nums.get(right) && count == 0) {
                res[0] = nums.get(left);
                res[1] = nums.get(right);
                count++;
            } else if (nums.get(left) > nums.get(right)) {
                res[1] = nums.get(right);
            }
            left++;
            right++;
        }
        return res;
    }

    // DFS寻找x和y值对应的节点，找到后交换
    public void recover(TreeNode root, int count, int x, int y) {
        if (root == null || count == 0) {
            return;
        }

        if (root.val == x || root.val == y) {
            root.val = (root.val == x)? y: x;
            count--;
        }

        recover(root.left, count, x, y);
        recover(root.right, count, x, y);
    }
}


// 解法2：隐式中序遍历
class Solution {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;
        // 使用stack实现中序遍历
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 将当前root同pred进行比较
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }
        swap(x, y);
    }

    // 交换两个节点的值
    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
}