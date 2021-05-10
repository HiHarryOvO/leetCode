# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def leafSimilar(self, root1: TreeNode, root2: TreeNode) -> bool:
        # 中序遍历（其实前序、中序、后序都可以，因为不影响叶节点顺序）
        def inorder(root: TreeNode, leaf: List[int]):
            if not root:
                return
            inorder(root.left, leaf)
            if not root.left and not root.right:
                leaf.append(root.val)
            inorder(root.right, leaf)
        
        leaf1, leaf2 = [], []
        inorder(root1, leaf1)
        inorder(root2, leaf2)

        return leaf1 == leaf2