class Solution:
    def translateNum(self, num: int) -> int:
        ch = list(str(num))
        n = len(ch)
        dp = [0] * (n + 1)

        dp[0], dp[1] = 1, 1
        for i in range(1, n):
            dp[i + 1] = dp[i]
            if 10 <= int(ch[i - 1]) * 10 + int(ch[i]) <= 25:
                dp[i + 1] += dp[i - 1]
        return dp[n]