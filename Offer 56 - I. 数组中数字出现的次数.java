class Solution {
    public int[] singleNumbers(int[] nums) {
        int m = 0;
        for (int num : nums) {
            m ^= num;
        }

        // 找到m从右往左出现的第一个1（在该位x和y不相同）
        int div = 1;
        while ((m & div) == 0) {
            div <<= 1;
        }

        // 分组异或
        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & div) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }
}