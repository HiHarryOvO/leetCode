class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        dp = [[0] * 3 for _ in range(n)]
        # 0 - 拥有股票，1 - 未拥有且冷静期，2 - 未拥有且非冷静期
        dp[0][0] = -prices[0]

        for i, price in enumerate(prices[1:], start=1):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][2] - prices[i])
            dp[i][1] = dp[i - 1][0] + prices[i]
            dp[i][2] = max(dp[i - 1][1], dp[i - 1][2])

        return max(dp[n - 1][1], dp[n - 1][2])