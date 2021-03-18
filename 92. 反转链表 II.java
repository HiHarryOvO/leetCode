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
 
// 栈
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
		// 哨兵节点
        ListNode sentinel = new ListNode(0, head);
        ListNode p = sentinel;
        ListNode end = null, start = sentinel;
        int index = 0;

        Deque<ListNode> stack = new LinkedList<>();

        while (p != null) {
			// 待修改部分入栈
            if (index >= left && index <= right) {
                stack.push(p);
            }

			// 记录修改部分的前一个节点
            if (index == left - 1) {
                start = p;
            }

			// 记录修改部分的后一个节点
            if (index == right) {
                end = p.next;
            }

            p = p.next;
            index++;
        }

		// 出栈并重新连接
        ListNode q = start;
        while (!stack.isEmpty()) {
            q.next = stack.pop();
            q = q.next;
        }
        q.next = end;

        return sentinel.next;
    }
}

// 穿针引线
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        // 哨兵节点
        ListNode sentinel = new ListNode(0, head);
        // 记录位置
        int index = 0;

        // 辅助指针
        // start: 反转部分的前一个节点，下标为left - 1
        // next: 目前已反转的部分的终点，下标起初为left
        // curr: 当前进行插入的节点，下标起初为left + 1
        ListNode start, next, curr;

        ListNode p = sentinel;

        while (index <= right) {

            if (index == left - 1) {
                start = p;
                next = p.next;
                curr = p.next.next;
                index += 2; // 开始记录待修改节点的位置

                // index > right时，代表所有待修改节点结束
                while (index <= right) {
                    // 画个图就可以理解了
                    next.next = curr.next;
                    curr.next = start.next;
                    start.next = curr;

                    curr = next.next;
                    index++;
                }

                // 循环终止
                break;
            }

            p = p.next;
            index++;
        }

        return sentinel.next;
    }
}