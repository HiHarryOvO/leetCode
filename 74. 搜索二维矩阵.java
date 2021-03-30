class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 二分查找第一列
        int l = 0, r = m - 1;
        int row = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cur = matrix[mid][0];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                row = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // 二分查找row对应的行
        l = 0;
        r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cur = matrix[row][mid];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }
}