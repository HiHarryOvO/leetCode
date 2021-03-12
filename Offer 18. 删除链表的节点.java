/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        // 删除节点在首节点的情况
        if (head.val == val) {
            head = head.next;
            return head;
        }

        ListNode prev = head, curr = head.next;
        while (curr != null) {
            // 定位到需要删除的节点
            if (curr.val == val) {
                prev.next = curr.next;
                break;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
        }
        return head;
    }
}