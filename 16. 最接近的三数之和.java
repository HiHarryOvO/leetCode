class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 还是双指针，但和q15有些许区别
        int answer = 10000;
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            int k = nums.length - 1;
            for (int j = i + 1; j < n; j++) {
                if (j == k) {
                    break;
                }
                // 判断当前结果是否优于储存的结果
                int currSum = nums[i] + nums[j] + nums[k];
                int currDiff = Math.abs(currSum - target);
                if (currDiff < Math.abs(answer - target)) {
                    answer = currSum;
                }
                // 判断是应该移动头指针还是尾指针
                if (currSum < target) {
                    continue; // 移动j
                } else if (currSum > target) {
                    j--; // 相当于j不移动
                    k--;
                } else {
                    return target;
                }
            }
            
        }
        return answer;
    }
}