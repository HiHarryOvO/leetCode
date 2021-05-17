# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

import collections

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        seq, q = [], collections.deque() # 注意如果直接写collections.deque([root])就有问题了
        if root:
            q.append(root)
        while q:
            node = q.popleft()
            if not node:
                seq.append("null")
            else:
                seq.append(str(node.val))
                q.append(node.left)
                q.append(node.right)
        return "[" + ",".join(seq) + "]"
        

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if data == "[]":
            return None
        vals = data[1:-1].split(",")
        
        root = TreeNode(int(vals[0]))
        q = collections.deque([root])
        i = 1

        while q:
            node = q.popleft()
            # 这里i应该不会报错，因为反序列化是根据序列化来的，序列化中都是成对加入的
            if vals[i] != "null":
                node.left = TreeNode(int(vals[i]))
                q.append(node.left)
            i += 1
            if vals[i] != "null":
                node.right = TreeNode(int(vals[i]))
                q.append(node.right)
            i += 1

        return root
        

# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))