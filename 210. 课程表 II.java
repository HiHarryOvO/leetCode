// 解法1：深度优先搜索
class Solution {
    // 邻接表
    List<List<Integer>> edges;
    boolean valid = true;
    int[] visited, res;
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }

        res = new int[numCourses];
        visited = new int[numCourses];
        // 注意：从后往前存储结果
        index = numCourses - 1;
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid? res : new int[0];
    }

    public void dfs(int v) {
        visited[v] = 1;
        for (int u : edges.get(v)) {
            if (visited[u] == 0) {
                dfs(u);
                if (!valid) {
                    return;
                }
            } else if (visited[u] == 1) {
                valid = false;
                return;
            }
        }
        visited[v] = 2;
        res[index--] = v;
    }
}

// 解法2：广度优先搜索
class Solution {
    // 邻接表
    List<List<Integer>> edges;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        // indeg[i]记录课程i的先修课数量
        int[] indeg = new int[numCourses];
        // 完成 先修课 - 所有子课程 的对应关系
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // 先修课数量为0的课程入列
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int i = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            // 出列顺序即是正确的学习顺序
            res[i++] = v;
            for (int u : edges.get(v)) {
                indeg[u]--;
                // 满足学习条件则入列
                if (indeg[u] == 0) {
                    queue.offer(u);
                }
            }
        }
        return (i == numCourses)? res : new int[0];
    }
}