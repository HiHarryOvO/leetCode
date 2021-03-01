class NumMatrix {
    int[][] sum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        // 考虑边界情况
        if (m > 0) {
            int n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            // sum[i][j]储存 [0, i - 1] x [0, j - 1] 的前缀和
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }
        }
        
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 一定要弄明白下面式子中每个位置的索引应该填什么
        return sum[row2 + 1][col2 +1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */