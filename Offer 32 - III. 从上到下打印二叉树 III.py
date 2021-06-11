# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []

        res, q = [], collections.deque([root])

        while q:
            tmp = collections.deque()
            for _ in range(len(q)):
                node = q.popleft()
                
                if len(res) % 2:
                    tmp.appendleft(node.val)
                else:
                    tmp.append(node.val)

                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

            res.append(list(tmp))
            
        return res