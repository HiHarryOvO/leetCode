# 递归
class Solution:
    def lastRemaining(self, n: int, m: int) -> int:
        def f(n, m):
            if n == 0:
                return 0
            return (f(n - 1, m) + m) % n
        
        return f(n, m)

# 迭代
class Solution:
    def lastRemaining(self, n: int, m: int) -> int:
        last = 0
        for i in range(1, n + 1):
            last = (last + m) % i
        return last