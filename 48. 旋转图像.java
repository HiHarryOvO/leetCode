class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0].length == 1) {
            return;
        }

        int n = matrix[0].length;

        // 先进行转置操作
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        // 然后左右翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (n >> 1); j++) {
                swap(matrix, i, j, i, n-1-j);
            }
        }

        return;

    }

    public void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}
