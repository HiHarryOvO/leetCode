/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 自己写的DFS
class Solution {
    List<TreeNode> route_p;
    List<TreeNode> route_q;
    List<TreeNode> tmp = new ArrayList<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        int i = 0;
        for (; i < route_p.size() && i < route_q.size(); i++) {
            if (route_p.get(i).val != route_q.get(i).val) {
                return route_p.get(i-1);
            }
        }
        return route_p.get(i-1);
    }

    public void dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return;
        }

        tmp.add(root);
        if (root == p) {
            route_p = new ArrayList<>(tmp);
        }
        if (root == q) {
            route_q = new ArrayList<>(tmp);
        }
        dfs(root.left, p, q);
        dfs(root.right, p, q);
        tmp.remove(tmp.size() - 1);
    }
}

// 递归
class Solution {
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        // 左子树里面有没有p或q
        boolean lson = dfs(root.left, p, q);
        // 右子树里面有没有p或q
        boolean rson = dfs(root.right, p, q);
        // 有两种情况root为结果
        // 1. p和q一个在root的左子树，一个在root的右子树
        // 2. p和q一个就是root，另一个在root的子树中（左右均可）
        if ((lson && rson) || ((root.val == p.val) || (root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        // 返回true的情况：p或q在root子树中，p或q就是root
        // dfs(root, p, q)的主要含义：root中是否包含p或q
        return lson || rson || (root.val == p.val) || (root.val == q.val);
    }
}