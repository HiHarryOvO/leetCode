# 常规做法，可以解决任意出现m次的问题，只需要把3换成m
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        count = [0] * 32
        for num in nums:
            for j in range(32):
                count[j] += num & 1
                num >>= 1

        res = 0
        for i in range(32):
            res <<= 1
            res |= count[31 - i] % 3
        return res if count[31] % 3 == 0 else ~(res ^ 0xffffffff)

# 有限状态自动机做法
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ones, twos = 0, 0
        for num in nums:
            ones = ones ^ num & ~twos
            twos = twos ^ num & ~ones
        return ones