class Solution {
    public boolean canJump(int[] nums) {
        int target = nums.length - 1;
        // 记录能够到达的最远距离
        int rightMost = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, nums[i] + i);
            }
        }
        return (rightMost >= target);
    }
}
