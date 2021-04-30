class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [0] * (amount + 1)
        for i in range(1, amount + 1):
            # 计算可能的组合时，排除 硬币面值 > 金额 以及 该硬币面值无法实现的
            combo_ls = [dp[i - coin] for coin in coins if coin <= i and dp[i - coin] != -1]
            if len(combo_ls) == 0:
                dp[i] = -1
            else:
                dp[i] = min(combo_ls) + 1
        return dp[amount]