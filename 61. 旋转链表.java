/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode p = head;
        int n = 1;
        // 求链表长度
        while (p.next != null) {
            p = p.next;
            n++;
        }

        int newK = k % n;
        ListNode end = null, start = null;
        if (newK == 0) {
            return head;
        } else {
            int index = 0;
            // 尾部与头部连接
            p.next = head;

            ListNode q = head;
            while (q != null) {
                if (index + newK == n - 1) {
                    // 找到起点，断开连接
                    end = q;
                    start = q.next;
                    break;
                }

                index++;
                q = q.next;
            }

            end.next = null;
            return start;
        }
    }
}