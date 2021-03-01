class NumArray {
    int[] data;

    public NumArray(int[] nums) {
        this.data = new int[nums.length];
        System.arraycopy(nums, 0, data, 0, nums.length);
    }
    
    public int sumRange(int i, int j) {
        // 朴素想法，复杂度O(j - i)
		int sum = 0;
        for (int p = i; p <= j; p++) {
            sum += data[p];
        }
        return sum;
    }
}

class NumArray {
    // sums[i]储存nums数组从索引0开始到索引为i（不包括i）的和
    int[] sums;

    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        // 复杂度O(1)
		return sums[j + 1] - sums[i];
    }
}


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */