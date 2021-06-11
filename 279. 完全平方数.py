# DP
class Solution:
    def numSquares(self, n: int) -> int:
        square_num = [i ** 2 for i in range(1, int(n ** 0.5) + 1)]
        dp = [0] + [1] * n

        for i in range(2, n + 1):
            dp[i] = min([dp[i - j] for j in square_num if j <= i]) + 1
        return dp[n]

# BFS
class Solution:
    def numSquares(self, n: int) -> int:
        squares = [i * i for i in range(int(n ** 0.5) + 1, 0, -1)]
        memo = set(squares)

        q = collections.deque([n])
        ans = 1
        while True:
            # 遍历一层
            for _ in range(len(q)):
                num = q.popleft()
                if num in memo:
                    return ans

                for square in squares:
                    if square < num:
                        q.append(num - square)
            # 一层后ans需要+1
            ans += 1

# 贪心
class Solution:
    def numSquares(self, n: int) -> int:
        square_num = [i * i for i in range(1, int(n ** 0.5) + 1)]
        
        # 查看n是不是count个平方数的和，如果是，返回False
        def divisible(n, count):
            if count == 1:
                return n in square_num
        
            for num in square_num:
                if divisible(n - num, count - 1):
                    return True
            return False
        
        # 遍历查看n是不是1,2,...,n个平方数的和
        for i in range(1, n + 1):
            if divisible(n, i):
                return i