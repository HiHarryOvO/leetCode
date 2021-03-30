class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // 相等时在右区间查找
            if (nums[m] == m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // l是数组第一次出现不相等情况的位置，也是我们缺失的值
        return l;
    }
}