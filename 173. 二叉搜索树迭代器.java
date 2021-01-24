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

// 方法1：递归
class BSTIterator {
    // 使用一个额外的list
    List<Integer> res;
    int index;

    public BSTIterator(TreeNode root) {
        res = new LinkedList<>();
        index = -1;
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }
    
    public int next() {
        return res.get(++index);
    }
    
    public boolean hasNext() {
        return index + 1 < res.size();
    }
}


// 方法2：迭代
class BSTIterator {
    // 使用一个辅助栈
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        leftmost(root);
    }
    
    // 一个辅助函数，将一个节点的所有左儿子推入栈中
    private void leftmost(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        // 将右儿子和右儿子的所有左儿子推入栈中
        if (node.right != null) {
            leftmost(node.right);
        }

        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */