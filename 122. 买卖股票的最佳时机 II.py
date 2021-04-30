# 动态规划
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        dp = [[0] * 2 for _ in range(n)]

        dp[0][0], dp[0][1] = 0, -prices[0]

        for i, price in enumerate(prices[1:], start=1):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])

        return dp[n - 1][0]


# 贪心
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        return sum([prices[i + 1] - prices[i] for i in range(len(prices) - 1) if prices[i + 1] > prices[i]])