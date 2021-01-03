// 递归
class Solution {
    public double myPow(double x, int n) {
        // 防止越界问题
        long N = n;
        return N > 0? quickMul(x, N): 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1;
        }
        // 分而治之
        double y = quickMul(x, N / 2);
        return (N % 2 == 0)? y * y: y * y * x;
    }
}

// 迭代
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return (N > 0)? quickMul(x, N): 1.0 / quickMul(x, -N);
    }

	// 主要思想：借助二进制表示N，从而迭代计算
    public double quickMul(double x, long N) {
        double ans = 1.0;
        double x_pow = x;
        // 从最低位向上查询
        while (N > 0) {
            if (N % 2 == 1) {
                ans *= x_pow;
            }

            x_pow *= x_pow;
            N /= 2;
        }
        return ans;
    }
}
