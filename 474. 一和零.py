class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        # 获取字符串中0和1的数量
        def getZerosOnes(s: str) -> List[int]:
            zeros, ones = 0, 0
            for ch in s:
                if ch == '1':
                    ones += 1
                else:
                    zeros += 1
            return [zeros, ones]

        length = len(strs)
        dp = [[[0] * (n + 1) for i in range(m + 1)] for j in range(length + 1)]

        for i in range(1, length + 1):
            zeros, ones = getZerosOnes(strs[i - 1])
            for j in range(m + 1):
                for k in range(n + 1):
                    # DP方程部分
                    if j >= zeros and k >= ones:
                        dp[i][j][k] = max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1)
                    else:
                        dp[i][j][k] = dp[i - 1][j][k]
        return dp[length][m][n]