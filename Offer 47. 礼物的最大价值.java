// 原地修改
class Solution {
    public int maxValue(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0) {
                    grid[i][j] += (j == 0)? 0 : grid[i][j - 1];
                } else {
                    grid[i][j] += (j == 0)? grid[i - 1][j] : Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[rows - 1][cols - 1];
    }
}

// 创建新DP数组
class Solution {
    public int maxValue(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] dp = new int[cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0) {
                    dp[j + 1] = dp[j] + grid[i][j];
                } else {
                    dp[j + 1] = (dp[j + 1] > dp[j])? dp[j + 1] : dp[j];
                    dp[j + 1] += grid[i][j];
                }
            }
        }
        return dp[cols];
    }
}