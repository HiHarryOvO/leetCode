# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rob(self, root: TreeNode) -> int:
        def dfs(root):
            if not root:
                return
            
            dfs(root.left)
            dfs(root.right)
            
            f[root] = root.val + g[root.left] + g[root.right]
            g[root] = max(f[root.left], g[root.left]) + max(f[root.right], g[root.right])


        f, g = dict(), dict()
        f[None], g[None] = 0, 0
        dfs(root)
        return max(f[root], g[root])


# 一个更加简洁的解答
class Solution:
    def rob(self, root: TreeNode) -> int:
        def _rob(root):
            if not root:
                return 0, 0
            
            # 第一个返回值表示打劫当前节点的最大收益
            # 第二个返回值表示不打劫当前节点的最大收益
            ls, ln = _rob(root.left)
            rs, rn = _rob(root.right)

            return root.val + ln + rn, max(ls, ln) + max(rs, rn)

        return max(_rob(root))