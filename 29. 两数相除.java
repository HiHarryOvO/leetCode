class Solution {
    public int divide(int dividend, int divisor) {
        // 判断溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 判断符号
        int sign = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }

        dividend = (dividend > 0)? -dividend: dividend;
        divisor = (divisor > 0)? -divisor: divisor;

        int result = div(dividend, divisor);
        
        return (sign == 1)? result: - result;
    }

    public int div(int dividend, int divisor) {
        if (dividend > divisor) {
            return 0;
        }
        int count = 1;
        int tmp = divisor;
        // 为什么这样做能解决溢出问题呢
        while ((tmp + tmp) >= dividend && (tmp + tmp) < 0) {
            count = count + count;
            tmp = tmp + tmp;
        }
        return count + div(dividend - tmp, divisor);
    }
}