class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        _sum = sum(stones)
        n, m = len(stones), _sum // 2

        dp = [[False] * (m + 1) for _ in range(n + 1)]
        dp[0][0] = True

        for i in range(n):
            for j in range(m + 1):
                if j < stones[i]:
                    dp[i + 1][j] = dp[i][j]
                else:
                    dp[i + 1][j] = dp[i][j] or dp[i][j - stones[i]]
        
        for j in range(m, -1, -1):
            if dp[n][j]:
                return _sum - 2 * j

        return 0


# DP优化空间
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        _sum = sum(stones)
        n, m = len(stones), _sum // 2

        dp = [False] * (m + 1)
        dp[0] = True

        for i in range(n):
            for j in range(m, -1, -1):
                if j >= stones[i]:
                    dp[j] |= dp[j - stones[i]]
        
        for j in range(m, -1, -1):
            if dp[j]:
                return _sum - 2 * j

        return 0