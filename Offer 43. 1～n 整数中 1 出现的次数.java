class Solution {
    public int countDigitOne(int n) {
        int res = 0;
        int high = n / 10, cur = n % 10, digit = 1;
        int low = 0;

        // 逐位计算该位的1的数量
        while (high != 0 || cur != 0) {
            // 按当前位的值进行讨论
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }

        return res;
    }
}