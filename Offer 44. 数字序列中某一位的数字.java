class Solution {
    public int findNthDigit(int n) {
        int digit = 1; // digit表示num的实际位数1,2,3,...
        // start表示10 ^ digit
        long start = 1, count = 9; // 存在越界可能
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * digit * start;
        }
        // 得到位置n所在的实际数字num
        long num = start + (n - 1) / digit;
        // 计算是num的哪一位
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}