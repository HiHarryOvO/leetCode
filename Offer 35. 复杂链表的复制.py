"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

# 哈希
class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return

        copyHead = Node(head.val)
        curr = head
        mp = {head: copyHead}
        while curr:
            copy = mp[curr]
            
            if curr.next:
                if curr.next in mp.keys():
                    copy.next = mp[curr.next]
                else:
                    mp[curr.next] = Node(curr.next.val)
                    copy.next = mp[curr.next]

            if curr.random:
                if curr.random in mp.keys():
                    copy.random = mp[curr.random]
                else:
                    mp[curr.random] = Node(curr.random.val)
                    copy.random = mp[curr.random]       

            curr = curr.next          

        return copyHead


# O(1)空间迭代
class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return

        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = cur.next.next

        cur = head
        while cur:
            if cur.random:
                cur.next.random = cur.random.next
            cur = cur.next.next

        cur = res = head.next
        pre = head
        while cur.next:
            pre.next = pre.next.next
            cur.next = cur.next.next
            pre = pre.next
            cur = cur.next

        return res