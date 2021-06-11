# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# 递归
class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        def recur(A, B):
            if not A and not B:
                return True
            elif not A or not B or A.val != B.val:
                return False
            return recur(A.left, B.right) and recur(A.right, B.left)

        return recur(root.left, root.right) if root else True

# 迭代
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if not root:
            return True
            
        q = collections.deque([root.left, root.right])
        while q:
            # 两两比较
            ln, rn = q.popleft(), q.popleft()
            if not ln and not rn:
                continue
            elif not ln or not rn or ln.val != rn.val:
                return False
            
            # 注意进队顺序
            q.append(ln.left)
            q.append(rn.right)

            q.append(ln.right)
            q.append(rn.left)

        return True