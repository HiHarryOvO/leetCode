# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

# 方法1：堆
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if not lists:
            return 

        hp = []
        for i, p in enumerate(lists):
            if p:
                heapq.heappush(hp, (p.val, i))
        
        sentinel = ListNode(0)
        node = sentinel
        while hp:
            val, ind = heappop(hp)
            node.next = lists[ind]
            lists[ind] = lists[ind].next
            node = node.next

            if lists[ind]:
                heapq.heappush(hp, (lists[ind].val, ind))

        return sentinel.next

# 方法2：归并
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if not lists:
            return
        
        self.lists = lists
        return self.mergeLists(0, len(lists) - 1)

    def mergeLists(self, l, r):
        if l > r:
            return

        if l == r:
            return self.lists[l]

        mid = (l + r) // 2
        return self.mergeTwoLists(self.mergeLists(l, mid), self.mergeLists(mid + 1, r))

    def mergeTwoLists(self, la, lb):
        if not la and not lb:
            return

        if not la or not lb:
            return la if not lb else lb

        sentinel = ListNode(0)
        tail = sentinel
        pa, pb = la, lb
        while pa and pb:
            if pa.val <= pb.val:
                tail.next = pa
                pa = pa.next
                tail = tail.next
            else:
                tail.next = pb
                pb = pb.next
                tail = tail.next
        tail.next = pa if not pb else pb
        return sentinel.next