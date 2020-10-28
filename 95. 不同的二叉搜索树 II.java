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
    // 接下来两个函数是方法一：递归
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> answer = new ArrayList<>();

        if (n == 0) {
            return answer;
        }

        // 采用递归思路
        return _generate(1, n);
    }

    // 递归函数
    public List<TreeNode> _generate(int left, int right) {
        List<TreeNode> allTrees = new ArrayList<>();

        // 终止条件
        if (left > right) {
            allTrees.add(null);
            return allTrees;
        }
        
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTrees = _generate(left, i - 1);
            List<TreeNode> rightTrees = _generate(i + 1, right);

            // 组合左子树和右子树
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree: rightTrees) {
                    TreeNode newTree = new TreeNode(i);
                    newTree.left = leftTree;
                    newTree.right = rightTree;
                    allTrees.add(newTree);
                }
            }
        }
        return allTrees;
    }


    // 以下两个函数为方法二：动态规划
    public List<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n+1];

        // 创建动态优化的数组
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }

        dp[0].add(null);

        for (int i = 1; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++) {
                // 左子树无需调整，直接引用
                List<TreeNode> leftTrees = dp[j-1];
                // 右子树需要引入偏差
                List<TreeNode> rightTrees = dp[i-j];

                for (TreeNode leftTree: leftTrees) {
                    for (TreeNode rightTree: rightTrees) {
                        TreeNode tree = new TreeNode(j);
                        tree.left = leftTree;
                        tree.right = treecopy(rightTree, j);
                        dp[i].add(tree);
                    }
                }
            }
        }
        return dp[n];
    }

    // 复制树结构的函数（通过调整偏差获得想要的右子树）
    public TreeNode treecopy(TreeNode root, int offset) {
        if (root == null) {
            return null;
        }

        TreeNode tree = new TreeNode(root.val + offset);
        tree.left = treecopy(root.left, offset);
        tree.right = treecopy(root.right, offset);

        return tree;
    }

    // 以下两个函数为方法三：动态规划
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> last = new ArrayList<>();
        
        if (n == 0) {
            return last;
        }
        last.add(null);

        for (int len = 1; len <= n; len++) {
            List<TreeNode> curr = new ArrayList<>();
            for (TreeNode lastTree: last) {
                // 在根节点插入
                TreeNode tree = new TreeNode(len, lastTree, null);
                curr.add(tree);
                
                if (lastTree == null) {
                    break;
                }
                // 在中间节点及叶节点插入
                TreeNode p = lastTree;
                int maxStep = 0;  // 这棵树的向右走最多走几次
                while (p.right != null) {
                    maxStep++;
                    p = p.right;
                }

                for (int i = 0; i <= maxStep; i++) {  // 一直往右走
                    tree = treecopy(lastTree);
                    TreeNode insert = tree;
                    for (int j = 0; j < i; j++) {
                        insert = insert.right;
                    }
                    insert.right = new TreeNode(len, insert.right, null);
                    curr.add(tree);
                }       
            }
            last = curr;
        }

        return last;
    }

    // 复制树结构的函数
    public TreeNode treecopy(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tree = new TreeNode(root.val);
        tree.left = treecopy(root.left);
        tree.right = treecopy(root.right);

        return tree;
    }
}
