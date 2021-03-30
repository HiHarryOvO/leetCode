class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        return helper(nums, target) - helper(nums, target - 1);
    }

    // 二分查找找右边界（右边第一个不匹配的值）
    int helper(int[] nums, int tar) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= tar) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}