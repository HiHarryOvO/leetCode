# 模拟
class Solution:
    def xorOperation(self, n: int, start: int) -> int:
        ans = 0
        for i in range(n):
            num = start + 2 * i
            ans ^= num
        return ans

# 数学
class Solution:
    def xorOperation(self, n: int, start: int) -> int:
        def xorSum(k):
            if k % 4 == 0:
                return k
            elif k % 4 == 1:
                return 1
            elif k % 4 == 2:
                return k + 1
            else:
                return 0
        
        # 注意这里最后一位 e 的计算方法
        s, e = start >> 1, start & 1 & n
        ret = xorSum(s - 1) ^ xorSum(s + n - 1)
        return ret << 1 | e