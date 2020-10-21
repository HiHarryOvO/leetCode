class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 边界情况
        if (nums.length == 0) {
            int[] answer = {-1, -1};
            return answer;
        }

        int low = 0;
        int high = nums.length - 1;

        return _search(nums, target, low, high);
    }

    public int[] _search(int[] nums, int target, int low, int high) {
        int[] answer = {-1, -1};
        int mid = (low + high) >> 1;

        // 在这种写法下存在这样的可能性，e.g. [2,3] 1  -> 试图在2的左边寻找
        if (low > high) {
            return answer;
        }

        if (low == high) {
            if (nums[low] == target) {
                answer[0] = low;
                answer[1] = low;
            }

            return answer;
        }

        // 当产生匹配时，并不结束查找，而是分头向两边寻找是否有比mid更宽的边界
        if (nums[mid] == target) {
            int[] leftM = _search(nums, target, low, mid - 1);
            int[] rightM = _search(nums, target, mid + 1, high);
            
            // 如果左边没有更远边界，则mid为左边界
            if (leftM[0] == -1) {
                answer[0] = mid;
            } else {
                answer[0] = leftM[0];
            }

            // 如果右边没有更远边界，则mid为右边界
            if (rightM[1] == -1) {
                answer[1] = mid;
            } else {
                answer[1] = rightM[1];
            }
        } else if (target < nums[mid]) {
            return _search(nums, target, low, mid - 1);
        } else if (nums[mid] < target) {
            return _search(nums, target, mid + 1, high);
        }

        return answer;
    }
}
