/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 迭代
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

// 递归
class Solution {
    public ListNode reverseList(ListNode head) {
        // 至少需要两个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 递归部分
        ListNode newHead = reverseList(head.next);
        // 修改当前位置的指向
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}