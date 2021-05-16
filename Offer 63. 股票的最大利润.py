class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        profit = 0
        rmin = int(1e9)

        for price in prices:
            rmin = min(rmin, price)
            profit = max(profit, price - rmin)

        return profit