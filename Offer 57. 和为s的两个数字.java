class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{nums[l], nums[r]};
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[0];
    }
}