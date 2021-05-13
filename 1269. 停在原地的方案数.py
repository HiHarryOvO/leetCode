class Solution:
    def numWays(self, steps: int, arrLen: int) -> int:
        edge = min(steps, arrLen - 1)
        dp = [[0] * (edge + 1) for _ in range(steps + 1)]
        dp[0][0] = 1

        mod = 10 ** 9 + 7

        for i in range(1, steps + 1):
            for j in range(0, edge + 1):
                # 注意处理左右边界
                if j == 0:
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j + 1]) % mod
                elif j == edge:
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % mod
                else:
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] + dp[i - 1][j + 1]) % mod
        
        
        return dp[steps][0]


# 优化空间后的动态规划
class Solution:
    def numWays(self, steps: int, arrLen: int) -> int:
        edge = min(steps, arrLen - 1)

        dp = [0] * (edge + 1)
        dp[0] = 1

        mod = 10 ** 9 + 7

        for i in range(1, steps + 1):
            dpNext = [0] * (edge + 1)
            for j in range(0, edge + 1):
                if j == 0:
                    dpNext[j] = (dp[j] + dp[j + 1]) % mod
                elif j == edge:
                    dpNext[j] = (dp[j] + dp[j - 1]) % mod
                else:
                    dpNext[j] = (dp[j - 1] + dp[j] + dp[j + 1]) % mod
            dp = dpNext

        return dp[0]