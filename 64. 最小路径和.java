class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 之前两道题都是从左上到右下求解
        // 本题是从右下往左上求解
        // 每一个点的最小路径和 = min(右边邻居的最小路径，下面邻居的最小路径) + 本点的值
        int[] bottom = new int[n];
        int[] row = new int[n];

        bottom[0] = grid[m-1][n-1];

        // 计算最下面一行
        for (int i = 1; i < n; i++) {
            bottom[i] = bottom[i-1] + grid[m-1][n-1-i];
        }

        if (m == 1) {
            return bottom[n-1];
        }

        for (int i = 1; i < m; i++) {
            row[0] = bottom[0] + grid[m-1-i][n-1];
            for (int j = 1; j < n; j++) {
                // min(右边邻居的最小路径，下面邻居的最小路径) + 本点的值
                row[j] = Math.min(row[j-1], bottom[j]) + grid[m-1-i][n-1-j];
            }

            if (i != m - 1) {
                System.arraycopy(row, 0, bottom, 0, n);
            }
        }

        return row[n-1];
    }
}
