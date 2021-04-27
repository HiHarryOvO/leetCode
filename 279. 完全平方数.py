class Solution:
    def numSquares(self, n: int) -> int:
        square_num = [i ** 2 for i in range(1, int(n ** 0.5) + 1)]
        dp = [0] + [1] * n

        for i in range(2, n + 1):
            dp[i] = min([dp[i - j] for j in square_num if j <= i]) + 1
        return dp[n]