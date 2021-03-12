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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        } 
        return search(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean search(TreeNode A, TreeNode B) {
        // 这里即使B为空，B也是至少有一个节点，因此返回true
        if (B == null) {
            return true;
        }

        if (A == null || A.val != B.val) {
            return false;
        }

        return search(A.left, B.left) && search(A.right, B.right);
    }
}