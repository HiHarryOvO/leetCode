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
		// 为什么这样做能解决溢出问题呢？
		// 首先需要明确，采用指数方式查找最终解，最后一次的查找是越过dividend的
		// 因此最后一次查找存在越界的可能
		// 如果发生越界，则有 tmp + tmp 越界，因此加入判断条件 tmp + tmp < 0
		// 为什么不全部改为正数进行运算？因为Integer.MIN_VALUE转换成正数会越界的
        while ((tmp + tmp) >= dividend && (tmp + tmp) < 0) {
            count = count + count;
            tmp = tmp + tmp;
        }
        return count + div(dividend - tmp, divisor);
    }
}