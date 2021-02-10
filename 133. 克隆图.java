/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// DFS
class Solution {
    Map<Integer, Node> nodeMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // 如果已经访问过这一节点，则返回克隆节点
        if (nodeMap.containsKey(node.val)) {
            return nodeMap.get(node.val);
        }

        // 创建新的节点
        Node cloneNode = new Node(node.val);
        // 存储到哈希表中
        nodeMap.put(node.val, cloneNode);

        // 访问本节点的所有邻居并更新本节点邻居列表
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}

// BFS
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> nodeMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        nodeMap.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node p = queue.poll();
            for (Node neighbor : p.neighbors) {
                if (!nodeMap.containsKey(neighbor)) {
                    nodeMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }
                nodeMap.get(p).neighbors.add(nodeMap.get(neighbor));
            }
        }
        return nodeMap.get(node);
    }
}