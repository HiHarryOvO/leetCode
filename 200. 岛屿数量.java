// DFS
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    // 找到所有与当前点连通的点，并且置0
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return num;
    }

    public void dfs(char[][] grid, int i, int j, int m, int n) {
        // 搜索终止条件
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        // 置0后继续搜索连通点
        dfs(grid, i-1, j, m, n);
        dfs(grid, i+1, j, m, n);
        dfs(grid, i, j-1, m, n);
        dfs(grid, i, j+1, m, n);
    }
}

// BFS
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int num = 0;
        Queue<Integer> queue = new LinkedList<>(); 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    grid[i][j] = '0';
                    // 计算该点的id并推入队列中
                    queue.offer(i * n + j);
                    // 队列为空时，和本点连通的所有点就遍历完毕了
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        int row = id / n;
                        int col = id % n;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.add((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < m && grid[row + 1][col] == '1') {
                            queue.add((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.add(row * n + (col - 1));
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < n && grid[row][col + 1] == '1') {
                            queue.add(row * n + (col + 1));
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return num;
    }
}