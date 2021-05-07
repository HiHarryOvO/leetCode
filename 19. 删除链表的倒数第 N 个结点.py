# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        sentinel = ListNode(0, head)
        second, first = sentinel, head

        # 第一个指针移动到位
        for i in range(n):
            first = first.next

        # 此时两个指针位置间隔为 n + 1
        # 两个指针同时移动
        while first:
            second = second.next
            first = first.next

        second.next = second.next.next
        return sentinel.next