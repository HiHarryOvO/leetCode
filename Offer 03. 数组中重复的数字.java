class Solution {
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        // 记录数字出现次数
        int[] dict = new int[n];
        for (int num : nums) {
            dict[num]++;
            if (dict[num] > 1) {
                return num;
            }
        }
        return -1;
    }
}