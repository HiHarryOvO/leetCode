class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word.length() == 0) {
            return false;
        }
        
        int m = board.length, n = board[0].length;
        // 遍历矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, String word, int pos) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(pos)) {
            return false;
        }

        if (pos == word.length() - 1) {
            return true;
        }

        // 防止格子重复使用
        board[i][j] = '\0';
        boolean res = dfs(board, i - 1, j, word, pos + 1) || dfs(board, i + 1, j, word, pos + 1) 
                   || dfs(board, i, j - 1, word, pos + 1) || dfs(board, i, j + 1, word, pos + 1);
        // 回溯
        board[i][j] = word.charAt(pos);
        return res;
    }
}