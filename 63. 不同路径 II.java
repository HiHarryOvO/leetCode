class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 设置这个start是为了方便初始化第一行和第一列
        int start = 1;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;


        int[] lastRow = new int[n];
        int[] thisRow = new int[n];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                start = 0;
            }
            lastRow[i] = start;
        }

        // 边界情况：只有一行
        if (m == 1) {
            return lastRow[n-1];
        }

        // 边界情况：起点就是障碍物
        start = (obstacleGrid[0][0] == 1)? 0: 1;

        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                start = 0;
            }
            thisRow[0] = start;

            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    thisRow[j] = 0;
                } else {
                    thisRow[j] = thisRow[j-1] + lastRow[j];
                }
            }

            if (i != m - 1) {
                System.arraycopy(thisRow, 0, lastRow, 0, n);
            }
        }

        return thisRow[n-1];
    }
}
