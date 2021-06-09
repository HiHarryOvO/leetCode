class Solution:
    def profitableSchemes(self, n: int, minProfit: int, group: List[int], profit: List[int]) -> int:
        num = len(profit)
        dp = [[[0] * (minProfit + 1) for j in range(n + 1)] for i in range(num + 1)]

        dp[0][0][0] = 1
        mod = 1000000007

        for i in range(num):
            g, p = group[i], profit[i]
            for j in range(n + 1):
                for k in range(minProfit + 1):
                    if j < g:
                        dp[i + 1][j][k] = dp[i][j][k]
                    else:
                        dp[i + 1][j][k] += (dp[i][j][k] + dp[i][j - g][max(0, k - p)]) % mod

        ans = 0
        for j in range(n + 1):
            ans = (ans + dp[num][j][minProfit]) % mod
        return ans