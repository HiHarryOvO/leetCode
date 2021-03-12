/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode left = head, right = head;
        // 右指针向右走k - 1步
        for (int i = 1; i < k; i++) {
            right = right.next;
        }

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }

        return left;
    }
}