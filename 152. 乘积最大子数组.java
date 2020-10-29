class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 维护两个动态规划数组，一个存储最大值，一个存储最小值
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];

        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        // 引入新的数字时，可能使最大值更大，也可能使最小值最大，也可能自己单独最大（e.g. [-2,0,1]）
        // 因此，也可能使最大值最小，可能使最小值更小，也可能自己单独最小（e.g. [-2,0,-5]）
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(Math.max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i]), nums[i]);
            dpMin[i] = Math.min(Math.min(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i]), nums[i]);
        }

        // 寻找dpMax中的最大值
        int _max = dpMax[0];
        for (int i = 1; i < n; i++) {
            _max = Math.max(_max, dpMax[i]);
        }

        return _max;
    }
}
