class Solution:
    def myPow(self, x: float, n: int) -> float:
        if x == 0:
            return 0

        if n < 0:
            x, n = 1 / x, -n

        # 快速幂
        rem = 1
        while n:
            if n & 1:
                rem *= x
            x *= x
            n >>= 1

        return rem