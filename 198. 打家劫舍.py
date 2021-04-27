class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * (n + 1)
        dp[1] = nums[0]
        for i in range(1, n):
            dp[i + 1] = max(dp[i], dp[i - 1] + nums[i])
        return dp[n]
		

# 优化空间
class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        
        n = len(nums)
        if n == 1:
            return nums[0]

        first, second = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            first, second = second, max(second, first + nums[i])
        return second