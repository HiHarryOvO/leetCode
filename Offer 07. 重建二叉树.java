/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归
class Solution {
    Map<Integer, Integer> map;
    int[] preorder, inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        this.preorder = preorder;
        this.inorder = inorder;    
        // 把中序遍历存入哈希表中方便查找
        int n = preorder.length;
        map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return build(0, n - 1, 0, n - 1);
    }

    public TreeNode build(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preL]);
        // 查找root在inorder中的位置
        int rootPos = map.get(preorder[preL]);
        // 递归重建树
        // 左子树的长度为：rootPos - inL
        root.left = build(preL + 1, preL + rootPos - inL, inL, rootPos - 1);
        root.right = build(preL + rootPos - inL + 1, preR, rootPos + 1, inR);
        return root;
    }
}