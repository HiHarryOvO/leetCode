class Solution:
    def constructArr(self, a: List[int]) -> List[int]:   
        n = len(a)
        b, start = [1] * n, 1

        # 左边的部分
        for i in range(1, n):
            b[i] = b[i-1] * a[i-1]

        # 右边的部分
        for i in range(n - 2, -1, -1):
            start *= a[i+1]
            b[i] *= start

        return b