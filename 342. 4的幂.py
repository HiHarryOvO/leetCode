class Solution:
    def isPowerOfFour(self, n: int) -> bool:
        # 二进制(10101010101010101010101010101010)，16进制表达为0xaaaaaaaa（0x表示16进制）
        # 二进制(01010101010101010101010101010101)，16进制表达为0x55555555
        return n > 0 and (n & (n - 1)) == 0 and (n & 0xaaaaaaaa) == 0

class Solution:
    def isPowerOfFour(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0 and (n & 0x55555555) != 0

class Solution:
    def isPowerOfFour(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0 and n % 3 == 1