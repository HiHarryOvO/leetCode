# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: TreeNode, target: int) -> List[List[int]]:
        ans, path = [], []
        
        def dfs(root, target):
            if not root:
                return

            path.append(root.val)
            # 直接修改target就不用额外变量来记录当前路径和了
            target -= root.val
            # 注意路径必须到叶节点
            if target == 0 and not root.left and not root.right:
                ans.append(path[:])

            dfs(root.left, target)
            dfs(root.right, target)
            # 回溯（注意只用pop()一次）
            path.pop()

        dfs(root, target)
        return ans