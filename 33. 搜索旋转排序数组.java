class Solution {
    public int search(int[] nums, int target) {
        // 需要考虑的特殊情况
        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return (nums[0] == target)? 0:-1;
        }

        int n = nums.length;
        int low = 0, high = n - 1;
        
        // 采用二分查找
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[high]) { // 判断右边是否是有序数组
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else { // 左边是有序数组
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}