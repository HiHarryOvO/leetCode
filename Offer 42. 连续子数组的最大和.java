class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int last = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            // last记录以nums[i - 1]结尾的最大和连续子数组
            last = (last <= 0)? nums[i] : last + nums[i];
            // 更新最大值
            res = Math.max(res, last);
        }
        return res;
    }
}