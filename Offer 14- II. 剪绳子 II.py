class Solution:
    def cuttingRope(self, n: int) -> int:
        if n <= 3:
            return n - 1
        
        mod = int(1e9 + 7)
        a, b = n // 3 - 1, n % 3
        x, rem = 3, 1
        # 快速幂
        while a > 0:
            if a % 2:
                rem = (rem * x) % mod
            x = x ** 2 % mod
            a //= 2

        if b == 0:
            return (rem * 3) % mod
        if b == 1:
            return (rem * 4) % mod
        return (rem * 6) % mod