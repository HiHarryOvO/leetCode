# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# DFS
class Solution:
    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        depth_x, father_x, found_x = 0, None, False
        depth_y, father_y, found_y = 0, None, False
        
        # DFS中额外引入深度信息和父节点信息
        def dfs(root, depth, parent):
            if not root:
                return
            
            # 修改变量作用域
            nonlocal depth_x, father_x, found_x, depth_y, father_y, found_y

            if root.val == x:
                depth_x, father_x, found_x = depth, parent, True
            elif root.val == y:
                depth_y, father_y, found_y = depth, parent, True

            # 剪枝
            if found_x and found_y:
                return
            
            dfs(root.left, depth + 1, root)
            dfs(root.right, depth + 1, root)
            

        dfs(root, 0, None)
        return depth_x == depth_y and father_x != father_y


# BFS
import collections

class Solution:
    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        depth_x, father_x, found_x = 0, None, False
        depth_y, father_y, found_y = 0, None, False

        def update(root, depth, parent):
            if root.val == x:
                nonlocal depth_x, father_x, found_x
                depth_x, father_x, found_x = depth, parent, True
            elif root.val == y:
                nonlocal depth_y, father_y, found_y
                depth_y, father_y, found_y = depth, parent, True

        q = collections.deque([(root, 0)])
        update(root, 0, None)

        while q:
            node, depth = q.popleft()
            if node.left:
                update(node.left, depth + 1, node)
                q.append((node.left, depth + 1))
            if node.right:
                update(node.right, depth + 1, node)
                q.append((node.right, depth + 1))

            if found_x and found_y:
                break

        return depth_x == depth_y and father_x != father_y