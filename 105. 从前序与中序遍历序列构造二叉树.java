/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归解法
class Solution {
    // 构造一个HashMap存储每个值在inorder中出现的位置
    private Map<Integer, Integer> map;
    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        this.preorder = preorder;
        this.inorder = inorder;

        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(0, n-1, 0, n-1);
    }

    // 递归函数
    public TreeNode myBuildTree(int pre_left, int pre_right, int in_left, int in_right) {
        // 终止条件
        if (pre_left > pre_right) {
            return null;
        }
        // 确认root值 -> 前序的第一个值
        int rootVal = preorder[pre_left];
        TreeNode root = new TreeNode(rootVal);
        // 确认左子树的长度
        int size_left = map.get(rootVal) - in_left;
        // 有了左子树的长度就可以得到左子树和右子树的边界位置所在
        root.left = myBuildTree(pre_left+1, pre_left+size_left, in_left, in_left+size_left-1);
        root.right = myBuildTree(pre_left+size_left+1, pre_right, in_left+size_left+1, in_right);
        return root;
    }
}

// 迭代解法
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        // 记录inorder中位置的指针
        int inOrderIndex = 0;

        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            int preorderVal = preorder[i];
            // 判断有没有走到最左
            if (node.val != inorder[inOrderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inOrderIndex]) {
                    node = stack.pop();
                    inOrderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}

// 迭代解法
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 边界情况
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // 使用栈记录节点
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        // 记录inorder数组的位置
        int inOrderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preOrderVal = preorder[i];
            TreeNode node = stack.peek();
            // 通过比较确定是否应该继续向左走
            if (node.val != inorder[inOrderIndex]) {
                node.left = new TreeNode(preOrderVal);
                stack.push(node.left);
            } else {
                // 确定应该在哪里插入右儿子节点
                while (!stack.isEmpty() && stack.peek().val == inorder[inOrderIndex]) {
                    node = stack.pop();
                    inOrderIndex++;
                }
                node.right = new TreeNode(preOrderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}