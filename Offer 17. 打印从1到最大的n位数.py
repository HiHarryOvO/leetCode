class Solution:
    def printNumbers(self, n: int) -> List[int]:
        def dfs(x):
            # 一个数字生成
            if x == n:
                # 从self.start开始切片
                s = ''.join(num[self.start:])
                # 注意：0不应该被加入结果中
                if s != "0":
                    res.append(int(s))
                # 当9的数量已经满了，下一个数字需要进一位（即self.start减一）
                if n - self.start == self.nine:
                    self.start -= 1
                return

            for i in range(10):
                if i == 9:
                    self.nine += 1
                num[x] = str(i)
                dfs(x + 1)
            # 回溯
            self.nine -= 1

        self.start, self.nine = n - 1, 0
        num, res = ['0'] * n, []
        dfs(0)
        return res