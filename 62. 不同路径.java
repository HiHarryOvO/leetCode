class Solution {
    public int uniquePaths1(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        int[] lastRow = new int[n];
        int[] thisRow = new int[n];

        // 第一行全为1（1,1处的值不重要）
        for (int i = 0; i < n; i++) {
            lastRow[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            // 第一列全为1
            thisRow[0] = 1;
            // 由于每个单位的值只取决于他左边的单位和上面的单位，
            // 当我们知道上一行的值，以及本行第一个值（1），就可以求出本行所有值，
            // 因此创建两个数组即可
            for (int j = 1; j < n; j++) {
                thisRow[j] = thisRow[j-1] + lastRow[j];
            }
            
            // 这次循环的当前行变成下一次循环的上一行
            if (i != m - 1) {
                System.arraycopy(thisRow, 0, lastRow, 0, n);
            }
        }
        return thisRow[n-1];
    }

    // 题目中 1 <= m, n <= 200
    public int uniquePaths2(int m, int n) {
        // 采用组合的思想，一共走m+n-2步，其中向右n-1步，向下m-1步
        // 即m+n-2抽n-1的组合数
        return (int)(fac(m + n - 2) / (fac(m - 1) * fac(n - 1)));
    }

    // 求阶乘
    public long fac(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
