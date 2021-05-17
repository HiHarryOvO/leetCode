# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# 两次遍历
class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        def findPath(root, target, path):
            if not root:
                return
            path.append(root)
            if root.val == target.val:
                return
            elif root.val < target.val:
                findPath(root.right, target, path)
            else:
                findPath(root.left, target, path)

        path_p, path_q = [], []
        findPath(root, p, path_p)
        findPath(root, q, path_q)

        res = None
        for i in range(min(len(path_p), len(path_q))):
            if path_p[i] == path_q[i]:
                res = path_p[i]
        return res

# 一次遍历
class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        ancestor = root
        while True:
            if ancestor.val < p.val and ancestor.val < q.val:
                ancestor = ancestor.right
            elif ancestor.val > p.val and ancestor.val > q.val:
                ancestor = ancestor.left
            else: # 位于二者之间（闭区间），则找到答案
                break
        return ancestor