// 解法1：深度优先搜索
class Solution {
    // 存储所有 先修课 - 子课程 信息
    List<List<Integer>> edges;
    // 记录节点状态：0 - 未访问；1 - 正在访问；2 - 已访问
    // 搜索过程中遇到正在访问的点：存在环
    int[] visited;
    boolean valid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        visited = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        // 记录课程信息
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 依次对所有课程进行搜索，出现环时结束搜索
        for (int i = 0; i < numCourses && valid; i++) {
            dfs(i);
        }
        return valid;
    }

    public void dfs(int v) {
        // 正在访问
        visited[v] = 1;
        for (int u : edges.get(v)) {
            if (visited[u] == 0) {
                dfs(u);
                if (!valid) {
                    return;
                }
            } else if (visited[u] == 1) { // 出现环
                valid = false;
                return;
            }
        }
        // 访问结束
        visited[v] = 2;
    }
}

// 解法2：广度优先搜索
class Solution {
    // 邻接表存储所有课程关系信息
    List<List<Integer>> edges;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        // indeg[i]记录课程i的先修课数量
        int[] indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // 先修课数量为0的课程直接进入队列
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        // visited记录当前已访问的可以学习的课程数目
        int visited = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            visited++;
            // 遍历v的所有子课程
            for (int u : edges.get(v)) {
                indeg[u]--;
                // 这里必须判断，因为一门课的先修课可能有好几门
                if (indeg[u] == 0) {
                    queue.offer(u);
                }
            }
        }
        // 比较 可学习课程数 和 所有课程数；
        return visited == numCourses;
    }
}