# 自己用哈希表写的DP，有点蠢
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        prev = {0: 1}
        for num in nums:
            curr = {}
            for k, v in prev.items():
                if k + num in curr.keys():
                    curr[k + num] += v
                else:
                    curr[k + num] = v

                if k - num in curr.keys():
                    curr[k - num] += v
                else:
                    curr[k - num] = v
            prev = curr

        return prev[target] if target in prev.keys() else 0


# DP
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        _sum, n = sum(nums), len(nums)
        neg = (_sum - target) // 2

        if neg < 0 or (_sum - target) % 2:
            return 0

        dp = [[0] * (neg + 1) for _ in range(n + 1)]
        # 注意初始化特例
        dp[0][0] = 1
        for i, num in enumerate(nums):
            for j in range(neg + 1):
                if j < num:
                    dp[i + 1][j] = dp[i][j]
                else:
                    dp[i + 1][j] = dp[i][j] + dp[i][j - num]
        return dp[n][neg]


# DP优化空间
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        _sum, n = sum(nums), len(nums)
        neg = (_sum - target) // 2

        if neg < 0 or (_sum - target) % 2:
            return 0

        dp = [0] * (neg + 1)
        # 注意初始化特例
        dp[0] = 1
        for num in nums:
            for j in range(neg, -1, -1):
                if j >= num:
                    dp[j] = dp[j] + dp[j - num]
        return dp[neg]