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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// 解法1：分治 + 快慢指针
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    // 寻找中间节点，快慢指针
    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

// 解法2：分治 + 中序遍历特点
class Solution {
    // globalHead从链表头部到尾部逐个遍历
    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;

        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    // 计算链表长度，O(n)
    private int getLength(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }

    // 分治算法
    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        
        TreeNode root = new TreeNode();
        
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        // 按中序遍历的顺序生成，生成顺序一定和链表顺序相同
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);

        return root;
    }
}