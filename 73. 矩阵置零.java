class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 记录第0列和第0行需不需要置0
        boolean flagCol0 = false, flagRow0 = false;

		
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) flagCol0 = true;
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) flagRow0 = true;
        }

		// 这里需要跳过第一行和第一列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

		// 跳过第一行和第一列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

		// 处理第0行和第0列
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}