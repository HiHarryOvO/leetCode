# DP，测试用例 100000 超时
class Solution:
    def checkRecord(self, n: int) -> int:
        dp = [[[0] * 3 for j in range(2)] for i in range(n + 1)]
        MOD = int(1e9 + 7)

        dp[0][0][0] = 1
        for i in range(1, n + 1):
            # 第 i 天为 P
            for j in range(2):
                for k in range(3):
                    dp[i][j][0] += dp[i - 1][j][k]

            # 第 i 天为 A
            for k in range(3):
                dp[i][1][0] += dp[i - 1][0][k]

            # 第 i 天为 L
            for j in range(2):
                for k in range(1, 3):
                    dp[i][j][k] += dp[i - 1][j][k - 1]

        return sum([dp[n][j][k] for j in range(2) for k in range(3)]) % MOD

# 简化后的 DP
class Solution:
    def checkRecord(self, n: int) -> int:
        p, a, l, pa, la, lla, ll = 1, 1, 1, 0, 0, 0, 0
        MOD = int(1e9 + 7)

        for i in range(2, n + 1):
            # 这状态转移...慢慢悟吧
            p, a, l, pa, la, lla, ll = (l+ll+p) % MOD, (p+l+ll) % MOD, p % MOD, (a+lla+la+pa) % MOD, (a+pa) % MOD, la % MOD, l % MOD

        return (p + a + l + pa + la + lla + ll) % MOD


# DP + 快速幂
class Solution:
    def checkRecord(self, n: int) -> int:
        MOD = int(1e9 + 7)

        # 矩阵乘法
        def multiply(A, B):
            row, col, tmp = len(A), len(B[0]), len(B)
            ans = [[0] * col for _ in range(row)]
            for i in range(row):
                for j in range(col):
                    for k in range(tmp):
                        ans[i][j] += A[i][k] * B[k][j]
                        ans[i][j] %= MOD
            return ans

        
        def matrixPow(M, n):
            # 一般来说，如果只求 M 的 n 次幂，这里 ret 初始化为单位矩阵
            # 在 DP 中，ret 初始化为 DP 的初始状态
            ret = [[1, 0, 0, 0, 0, 0]]
            while n > 0:
                if n & 1 == 1:
                    ret = multiply(ret, M)
                M = multiply(M, M)
                n >>= 1
            return ret

        M = [
            [1, 1, 0, 1, 0, 0],
            [1, 0, 1, 1, 0, 0],
            [1, 0, 0, 1, 0, 0],
            [0, 0, 0, 1, 1, 0],
            [0, 0, 0, 1, 0, 1],
            [0, 0, 0, 1, 0, 0],
        ]
        # res 维度和 ret 相同
        res = matrixPow(M, n)
        return sum(res[0]) % MOD