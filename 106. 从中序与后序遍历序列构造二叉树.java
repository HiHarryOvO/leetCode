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
    private int[] inorder;
    private int[] postorder;
    private int post_idx;
    private Map<Integer, Integer> map = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        int n = inorder.length;
        if (n == 0) {
            return null;
        }
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        post_idx = n - 1;
        return myBuildTree(0, n-1);
    }

    public TreeNode myBuildTree(int inL, int inR) {
        if (inL > inR) {
            return null;
        }
        int rootVal = postorder[post_idx];
        post_idx--;
        int rootPos = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 一定要从右边开始，因为后序遍历先储存左子树再储存右子树
        root.right = myBuildTree(rootPos + 1, inR);
        root.left = myBuildTree(inL, rootPos - 1);
        
        return root;
    }
}
