class Solution:
    def strToInt(self, str: str) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        bound = INT_MAX // 10

        res, i = 0, 1
        sign = 1
        
        # 删除多余空格
        str = str.strip()
        if not str:
            return 0

        # 确定符号位；如果没有符号位，从第0位开始
        if str[0] == '-':
            sign = -1
        elif str[0] != '+':
            i = 0

        for c in str[i:]:
            # 非数字则终止
            if not '0' <= c <= '9':
                break
            # 判断是否越界
            # 这里c > '7'值得回味，使得代码非常简洁
            if res > bound or res == bound and c > '7':
                return INT_MAX if sign == 1 else INT_MIN
            res = res * 10 + ord(c) - ord('0')
        
        return res * sign


# 优化空间
class Solution:
    def strToInt(self, str: str) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        bound = INT_MAX // 10

        res, i, n = 0, 0, len(str)
        sign = 1
        
        if not str:
            return 0
        while str[i] == ' ':
            i += 1
            if i == n:
                return 0

        # 确定符号位；如果没有符号位，从第0位开始
        if str[i] == '-':
            sign = -1
            i += 1
        elif str[i] == '+':
            i += 1

        for c in str[i:]:
            # 非数字则终止
            if not '0' <= c <= '9':
                break
            # 判断是否越界
            # 这里c > '7'值得回味，使得代码非常简洁
            if res > bound or res == bound and c > '7':
                return INT_MAX if sign == 1 else INT_MIN
            res = res * 10 + ord(c) - ord('0')
        
        return res * sign