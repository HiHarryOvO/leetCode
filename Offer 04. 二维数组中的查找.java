class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 边界情况
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        // 从右上角开始搜索
        // 根据matrix[r][c]和target的关系选择左移或者下移
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}