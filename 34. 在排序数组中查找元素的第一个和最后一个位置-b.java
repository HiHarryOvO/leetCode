class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] answer = {-1, -1};

        // 查询左边界
        int leftIndex = searchBorderIndex(nums, target, true);

        // 判断左边界是否合法，不合法则直接返回-1,-1
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return answer;
        }

        answer[0] = leftIndex;
        // 不要忘记减1
        answer[1] = searchBorderIndex(nums, target, false) - 1;
        return answer;
    }

    public int searchBorderIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        
        // 所以这个写法本质上是 [,mi)[mi+1,)
        // 只有mi符合时，左边可以匹配到mi，右边只能匹配到mi+1
        while (lo < hi) {
            int mi = (lo + hi) >> 1;
            if (target < nums[mi] || (left == true && target == nums[mi])) {
                hi = mi;
            } else {
                // 这里如果不加1的话，查找是死循环的
                // 但加1后就意味着匹配到右边界时，结果应该减1
                lo = mi + 1; 
            }
        }
        return lo;
    }
}
