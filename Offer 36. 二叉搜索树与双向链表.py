"""
# Definition for a Node.
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
"""
class Solution:
    def treeToDoublyList(self, root: 'Node') -> 'Node':
        if not root:
            return

        head, pre = None, None
        stack = []

        while stack or root:
            while root:
                stack.append(root)
                root = root.left

            root = stack.pop()
            if not head:
                head = root

            if pre:
                pre.right = root
                root.left = pre

            pre = root
            root = root.right

        head.left, pre.right = pre, head
        return head