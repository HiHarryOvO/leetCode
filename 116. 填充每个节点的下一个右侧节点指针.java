/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// 方法1：层次遍历，BFS
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 层次遍历，BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 用size记录本层有多少个结点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i != size - 1) {
                    node.next = queue.peek();
                } else {
                    node.next = null;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}

// 方法2：使用已建立的next指针
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            // 通过上一层的next指针不断构造本层的next指针
            while (head.next != null) {
                head.left.next = head.right;
                head.right.next = head.next.left;
                head = head.next;
            }
            head.left.next = head.right;
            leftmost = leftmost.left;
        }
        return root;
    }
}