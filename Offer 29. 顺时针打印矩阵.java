class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;
        // 定义四个边界
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            for (int c = left; c <= right; c++) {
                // 上边
                res[index++] = matrix[top][c];
            }

            for (int r = top + 1; r <= bottom; r++) {
                // 右边
                res[index++] = matrix[r][right];
            }

            if (left != right && top != bottom) {
                for (int c = right - 1; c >= left; c--) {
                    // 下边
                    res[index++] = matrix[bottom][c];
                }

                for (int r = bottom - 1; r > left; r--) {
                    // 左边
                    res[index++] = matrix[r][left];
                }
            }

            // 进入下一层
            top++;
            bottom--;
            left++;
            right--;
        }

        return res;
    }
}