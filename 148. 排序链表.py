# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

# 二分 + 归并
class Solution:
    def sortList(self, head: ListNode) -> ListNode:
        # 对链表 head 到 tail 的部分（不包括 tail）进行排序
        def sortFunc(head, tail):
            if not head:
                return
            if head.next == tail:
                head.next = None
                return head

            slow = fast = head
            while fast and fast != tail:
                fast = fast.next
                slow = slow.next
                # 不等于 tail 同时暗示了 fast 不可能为空
                if fast != tail:
                    fast = fast.next
            mid = slow
            return merge(sortFunc(head, mid), sortFunc(mid, tail))
        
        # 平平无奇的两个链表归并
        def merge(head1, head2):
            if not head1 and not head2:
                return

            if not head1 or not head2:
                return head1 if head1 else head2

            sentinel = ListNode(0)
            p = sentinel
            p1, p2 = head1, head2

            while p1 and p2:
                if p1.val <= p2.val:
                    p.next = p1
                    p1 = p1.next
                else:
                    p.next = p2
                    p2 = p2.next
                p = p.next
            p.next = p1 if p1 else p2

            return sentinel.next

        return sortFunc(head, None)


# 自底向上归并，优化空间
class Solution:
    def sortList(self, head: ListNode) -> ListNode:
        # 平平无奇的两个链表归并
        def merge(head1, head2):
            if not head1 and not head2:
                return

            if not head1 or not head2:
                return head1 if head1 else head2

            sentinel = ListNode(0)
            p = sentinel
            p1, p2 = head1, head2

            while p1 and p2:
                if p1.val <= p2.val:
                    p.next = p1
                    p1 = p1.next
                else:
                    p.next = p2
                    p2 = p2.next
                p = p.next
            p.next = p1 if p1 else p2

            return sentinel.next


        if not head:
            return 

        node = head
        length = 0
        while node:
            node = node.next
            length += 1

        dummy = ListNode(0, head)
        sub_len = 1
        while sub_len < length:
            # 注意这里 curr 不能被赋予 head，因为排序后 head 可能位置就变了
            prev, curr = dummy, dummy.next
            while curr:
                head1 = curr
                for i in range(1, sub_len):
                    if curr.next:
                        curr = curr.next
                    else:
                        break
                
                head2 = curr.next
                # 自己写的时候忘了这句
                curr.next = None
                curr = head2  # 注意在这里 curr 就可能为空，因此下面判断时除了 curr.next 还要判断 curr 不为空
                for i in range(1, sub_len):
                    if curr and curr.next:
                        curr = curr.next
                    else:
                        break
                
                # 如果 curr 为空，succ 默认为空
                succ = None
                if curr:
                    succ = curr.next
                    curr.next = None
                merged = merge(head1, head2)
                # 连接 prev 和排序部分
                prev.next = merged
                # 遍历 prev 至排序尾部
                while prev.next:
                    prev = prev.next
                # 更新 curr
                curr = succ
            # 子链表长度乘以 2
            sub_len <<= 1
        return dummy.next