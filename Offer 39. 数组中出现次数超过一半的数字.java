class Solution {
    public int majorityElement(int[] nums) {
        int votes = 0, x = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            // 相同则加1，不同则减1
            votes += (num == x)? 1 : -1;
        }

        // 判断是否超过一半
        int count = 0;
        for (int num : nums) {
            if (num == x) {
                count++;
            }
        }

        return (count > nums.length / 2)? x : -1;
    }
}