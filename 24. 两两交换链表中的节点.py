# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        sentinel = ListNode(0, head)
        
        # right指示要交换的第一个节点，left指示right的上一个节点
        left, right = sentinel, head
        while left and right and right.next:
            left.next = right.next
            right.next = right.next.next
            left.next.next = right

            left = right
            right = left.next

        return sentinel.next