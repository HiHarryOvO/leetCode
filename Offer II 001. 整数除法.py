# 自己写的
class Solution:
    def divide(self, a: int, b: int) -> int:
        def form(x, sign):
            INT_MIN, INT_MAX = -2 ** 31, 2 ** 31 - 1
            x = x if sign else -x
            return x if x >= INT_MIN and x <= INT_MAX else INT_MAX
        
        if a == 0:
            return 0       
        sign = True if (a < 0 and b < 0) or (a > 0 and b > 0) else False 
        a, b = abs(a), abs(b)

        if a < b:
            return 0
        elif a == b:
            return form(1, sign)

        nums = [(1, b)]
        i, k = 1, b
        while k < a:
            i, k = i << 1, k << 1
            nums.append((i, k))

        li, ln = nums[-2]
        mi, mn = nums[-2]
        ri, rn = nums[-1]

        for j in range(len(nums) - 3, -1, -1):
            curr_i, curr_n = nums[j]
            mi, mn = li + curr_i, ln + curr_n
            if mn == a:             
                return form(mi, sign)
            elif mn < a:
                li, ln = mi, mn
            else:
                ri, rn = mi, mn

        if a < mn:
            mi = mi - 1
        elif a == rn:
            mi = mi + 1
        return form(mi, sign)


# 简化代码
class Solution:
    def divide(self, a: int, b: int) -> int:
        ret = 0     
        sign = True if (a < 0 and b < 0) or (a > 0 and b > 0) else False 
        a, b = abs(a), abs(b)

        # 返回刚好小于 x 的那个 y 和对应的倍数 n
        def calc(x, y):
            n = 1
            while x > y << 1:
                n <<= 1
                y <<= 1
            return n, y

        while a >= b:
            count, val = calc(a, b)
            ret += count
            a -= val
        ret = ret if sign else -ret
        INT_MIN, INT_MAX = -2 ** 31, 2 ** 31 - 1
        return ret if ret >= INT_MIN and ret <= INT_MAX else INT_MAX