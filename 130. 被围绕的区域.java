// DFS
class Solution {
    int height, width;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        height = board.length;
        width = board[0].length;
        // 从边界开始搜索
        for (int i = 0; i < height; i++) {
            dfs(board, i, 0);
            dfs(board, i, width - 1);
        }
        // 从边界开始搜索
        for (int i = 1; i < width - 1; i++) {
            dfs(board, 0, i);
            dfs(board, height - 1, i);
        }
        // 修改标记的部分
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        // 在条件语句中筛掉不为'O'的点
        if (i < 0 || i >= height || j < 0 || j >= width || board[i][j] != 'O') {
            return;
        }
        // 由于我们是从边界开始找的，因此这一点一定没有被X围绕
        board[i][j] = 'A';
        // 找现在这一点的邻居
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}

// BFS
class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int height = board.length, width = board[0].length;
        // 维护一个队列
        Queue<int[]> queue = new LinkedList<>();
        // 从边界开始搜索
        for (int i = 0; i < height; i++) {
            queue.offer(new int[]{i, 0});
            queue.offer(new int[]{i, width - 1});
        }
        // 从边界开始搜索
        for (int i = 1; i < width - 1; i++) {
            queue.offer(new int[]{0, i});
            queue.offer(new int[]{height - 1, i});
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            if (x < 0 || x >= height || y < 0 || y >= width || board[x][y] != 'O') {
                continue;
            }
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                queue.offer(new int[]{x + dx[i], y + dy[i]});
            }
        }

        // 修改标记的部分
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}