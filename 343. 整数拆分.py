class Solution:
    def integerBreak(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[1] = 1

        for i in range(2, n + 1):
            max_prod = 0
            for j in range(1, int(i / 2) + 1):
                max_prod = max(max_prod, dp[j] * dp[i - j])
            # 这里是大坑
            dp[i] = max(max_prod, i) if i != n else max_prod
        
        return dp[n]


# 官方解答
class Solution:
    def integerBreak(self, n: int) -> int:
        dp = [0] * (n + 1)
        for i in range(2, n + 1):
            for j in range(i):
                dp[i] = max(dp[i], j * (i - j), j * dp[i - j])
        return dp[n]