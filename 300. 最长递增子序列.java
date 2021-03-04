// 动态规划
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
		// dp[i]表示前i个元素中，以第i个元素结尾的最长递增子序列
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 动态规划方程
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新结果
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

// 贪心 + 二分查找
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            // 大于最后一个值时，插入到最后
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 否则通过二分查找寻找插入位置（准确地说是修改的位置）
                int l = 1, r = len, pos = 0; // 如果没有找到位置，即nums[i]比d中所有值都小，修改d[1]
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (nums[i] > d[mid]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}