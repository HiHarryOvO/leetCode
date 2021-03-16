/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// 迭代
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Deque<Node> stack = new LinkedList<>();   
        Node head = null, prev = null;

        // 实现中序遍历
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (head == null) {
                head = root;
            }

            if (prev != null) {
                prev.right = root;
                root.left = prev;
            }

            prev = root;
            root = root.right;
        }

        // 连接首尾节点
        head.left = prev;
        prev.right = head;

        return head;
    }
}

// 递归
class Solution {
    Node head, prev;
    
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        dfs(root);
        // 连接首尾节点
        head.left = prev;
        prev.right = head;

        return head;
    }

    // 递归
    void dfs(Node root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev; 
        }
        prev = root;
        dfs(root.right);
    }
}