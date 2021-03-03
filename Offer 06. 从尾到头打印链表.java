/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 栈
class Solution {
    public int[] reversePrint(ListNode head) {
        // 使用栈
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode tmp = head;
        while (tmp != null) {
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        // 存入结果
        int n = stack.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}

// 递归
class Solution {
    List<Integer> ls = new ArrayList<>();
    public int[] reversePrint(ListNode head) {
        // 递归
        recur(head);
        // 存入结果
        int[] res = new int[ls.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ls.get(i);
        }
        return res;
    }

    private void recur(ListNode head) {
        if (head == null) {
            return;
        }
        // 进入下一层
        recur(head.next);
        // 添加值
        ls.add(head.val);
    }
}