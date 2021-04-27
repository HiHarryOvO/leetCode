class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        m, n = len(matrix), len(matrix[0])
        if m == 0 or n == 0:
            return 0
		
		# 注意：这里写 dp = [[0] * n] * m 就错了
        dp = [[0] * n for _ in range(m)]
        max_edge = 0

        for i in range(0, m):
            for j in range(0, n):
                if matrix[i][j] == "1":
                    if i == 0 or j == 0:
                        dp[i][j] = 1
                    else:
                        dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
                max_edge = max(max_edge, dp[i][j])

        max_square = max_edge * max_edge
        return max_square