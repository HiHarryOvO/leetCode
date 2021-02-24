class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            // 左右双指针
            int left = 0, right = n - 1;
            while (left < right) {
                // 左右相同时各自反转就行
                // 左右不同时，翻转再反转相当于不变
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            // 注意中间值
            if (left == right) {
                A[i][left] ^= 1;
            }
        }
        return A;
    }
}