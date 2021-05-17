# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


# 搜索回溯
class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        path_p, path_q = [], []
        found_p, found_q = False, False
        
        # 搜索回溯
        def dfs(root, path):
            if not root:
                return

            nonlocal path_p, path_q, found_p, found_q

            path.append(root)
            if root.val == p.val:
                path_p, found_p = path[:], True
            elif root.val == q.val:
                path_q, found_q = path[:], True

            if found_p and found_q:
                return            

            dfs(root.left, path)
            dfs(root.right, path)
            path.pop()

        dfs(root, [])

        res = None
        for i in range(min(len(path_p), len(path_q))):
            if path_p[i] == path_q[i]:
                res = path_p[i]
        return res


# 递归
class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        # 首先要明确，寻找LCA的过程本质上就是寻找分岔位置的过程

        # 如果root为空，返回空值；如果root匹配到了p或者q，那么LCA就是root
        if not root or p == root or q == root:
            return root

        # 递归寻找，left不为空表示root.left的子树中某个节点是p或者q
        # 同理，right不为空表示root.right的子树中某个节点是p或q
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)

        if not left: return right # 如果left为空，则p和q都在右子树中
        if not right: return left # 如果right为空，则p和q都在左子树中
        return root # left和right都不为空，则root就是分岔点


# 哈希表
import collections

class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        visited, parent, queue = set(), dict(), collections.deque([root])
        
        while p not in parent or q not in parent:
            node = queue.popleft()
            if node.left:
                parent[node.left] = node
                queue.append(node.left)
            if node.right:
                parent[node.right] = node
                queue.append(node.right)
        
        while p:
            visited.add(p)
            p = parent.get(p) # 使用get()，查询不到时会返回None而不是报错
        while q:
            if q in visited:
                return q
            q = parent.get(q)