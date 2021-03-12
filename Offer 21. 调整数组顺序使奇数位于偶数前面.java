// 首尾指针
class Solution {
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 符合交换规则
            if ((nums[left] & 1) == 0 && (nums[right] & 1) == 1) {
                swap(nums, left, right);
            }

            if ((nums[left] & 1) == 1) {
                left++;
            }

            if ((nums[right] & 1) == 0) {
                right--;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}

// 快慢指针
class Solution {
    public int[] exchange(int[] nums) {
        int slow = 0, fast = 0;
        int n = nums.length;
        // 快慢指针
        while (fast < n) {
            if ((nums[fast] & 1) == 1) {
                swap(nums, slow, fast);
                slow++;
            }
            fast++;
        }
        return nums;
    }

    private void swap(int[] nums, int slow, int fast) {
        int tmp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = tmp;
    }
}