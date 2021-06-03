# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        mp = dict()
        for i, num in enumerate(inorder):
            mp[num] = i
        
        # root是preorder的角度
        # in_left和in_right都是从inorder的角度
        def build(root, in_left, in_right):
            if in_left > in_right:
                return
            pre_val = preorder[root]
            i = mp[pre_val]
            node = TreeNode(pre_val)

            node.left = build(root + 1, in_left, i - 1)
            node.right = build(root + i - in_left + 1, i + 1, in_right)

            return node

        return build(0, 0, len(inorder) - 1)