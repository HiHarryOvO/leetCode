// 解法1：BFS
class Solution {
    public int movingCount(int m, int n, int k) {
        // BFS的队列
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int ans = 1;

        // 方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        // 记录是否遍历过当前点
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 2; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                // 可以到达的点入队
                if (tx < m && ty < n && !visited[tx][ty] && get(tx) + get(ty) <= k) {
                    queue.offer(new int[]{tx, ty});
                    ans++;
                    visited[tx][ty] = true;
                }
            } 
        }

        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}

// 解法2：DFS
class Solution {
    int ans = 0;
    int[][] matrix;

    public int movingCount(int m, int n, int k) {
        // 记录是否遍历过
        matrix = new int[m][n];
        dfs(0, 0, m, n, k);
        return ans;
    }

    private void dfs(int i, int j, int m, int n, int k) {
        if (i < 0 || j < 0 || i >= m || j >= n || matrix[i][j] == 1) {
            return;
        }

        matrix[i][j] = 1;

        if (get(i) + get(j) <= k) {
            ans += 1;
            dfs(i - 1, j, m, n, k);
            dfs(i + 1, j, m, n, k);
            dfs(i, j - 1, m, n, k);
            dfs(i, j + 1, m, n, k);
        }
    }

    // 计算数位和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}

// 解法3：DP
class Solution {
    public int movingCount(int m, int n, int k) {
        int ans = 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 跳过0,0因为ans已经为1
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }

                if (i >= 1) {
                    dp[i][j] |= dp[i - 1][j];
                }

                if (j >= 1) {
                    dp[i][j] |= dp[i][j - 1];
                }

                ans += dp[i][j]? 1: 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}