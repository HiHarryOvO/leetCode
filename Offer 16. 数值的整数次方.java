// 解法1：递归
class Solution {
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }

        if (n < 0) {
            return myPow(1 / x, -(n + 1)) / x; // 小细节防止越界
        } else if (n == 0) {
            return 1;
        } else if ((n & 1) == 1) {
            return x * myPow(x, n - 1);
        } else {
            return myPow(x * x, n / 2);
        }
    }
}

// 解法2：迭代
class Solution {
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }

        long b = n;
        if (n < 0) {
            b = -b; // 这里如果写 b = -n就错了
            x = 1 / x;
        }

        double res = 1.0;
        // 快速幂算法
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}