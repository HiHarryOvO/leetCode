/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// DFS
class Solution {
    Map<Node, Node> visited;

    public Node copyRandomList(Node head) {
        visited = new HashMap<>();
        return dfs(head);
    }

    private Node dfs(Node head) {
        if (head == null) {
            return null;
        }

        if (!visited.containsKey(head)) {
            Node copy = new Node(head.val);
            visited.put(head, copy);
            copy.next = dfs(head.next);
            copy.random = dfs(head.random);
            return copy;
        } else {
            return visited.get(head);
        }
    }
}

// BFS
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // 创建哈希表记录访问情况
        Map<Node, Node> map = new HashMap<>();
        Node clone = new Node(head.val);
        map.put(head, clone);

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.next != null && !map.containsKey(node.next)) {
                map.put(node.next, new Node(node.next.val));
                queue.offer(node.next);
            }
            if (node.random != null && !map.containsKey(node.random)) {
                map.put(node.random, new Node(node.random.val));
                queue.offer(node.random);
            }
            // 连接节点
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
        }

        return clone;
    }
}

// 优化迭代
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node p = head;
        // 把所有next连接起来
        while (p != null) {
            Node clone = new Node(p.val);
            clone.next = p.next;
            p.next = clone;

            p = p.next.next;
        }

        // 把所有random连接起来
        p = head;
        while (p != null) {
            if (p.random == null) {
                p.next.random = null;
            } else {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        // 分离原链表和拷贝链表
        Node p1 = head, p2 = head.next, res = head.next;
        while (p1 != null) {
            p1.next = p1.next.next;
            if (p2.next != null) {
                p2.next = p2.next.next;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        return res;
    }
}