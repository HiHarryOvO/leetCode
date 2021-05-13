class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        # dp[i][j]表示前i个骰子投出j点的概率
        dp = [[0] * (6 * n + 1) for _ in range(n + 1)]

        for j in range(1, 7):
            dp[1][j] = 1 / 6
        
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                # 注意要加条件j >= k防止越界
                dp[i][j] = sum([dp[i - 1][j - k] for k in range(1, 7) if j > k]) / 6

        return dp[n][n:]


# 优化空间
class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        dp = [0] * (6 * n + 1)
        for j in range(1, 7):
            dp[j] = 1 / 6
        
        for i in range(2, n + 1):
            dpNext = [0] * (6 * n + 1)
            for j in range(i, 6 * i + 1):
                dpNext[j] = sum([dp[j - k] for k in range(1, 7) if j > k]) / 6
            dp = dpNext

        return dp[n:]