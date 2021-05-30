class Solution:
    def strangePrinter(self, s: str) -> int:
        n = len(s)
        dp = [[1] * n for _ in range(n)]

        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                # 根据s[i]和s[j]关系判断
                if s[i] == s[j]:
                    dp[i][j] = dp[i][j-1]
                else:
                    dp[i][j] = min([dp[i][k] + dp[k+1][j] for k in range(i, j)])

        return dp[0][n-1]
                    