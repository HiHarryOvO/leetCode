class Solution:
    def add(self, a: int, b: int) -> int:
        x = 0xffffffff
        a, b = a & x, b & x
        while b != 0:
            # 异或运算，求非进位和
            # 与运算，求进位
            a, b = a ^ b, (a & b) << 1 & x
        return a if a <= 0x7fffffff else ~(a ^ x)