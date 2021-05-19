# 朴素解法
class Solution:
    def kthLargestValue(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        pre = [[0] * (n + 1) for _ in range(m + 1)]

        res = []
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                pre[i][j] = pre[i-1][j] ^ pre[i][j-1] ^ pre[i-1][j-1] ^ matrix[i-1][j-1]
                res.append(pre[i][j])
        
        res.sort()
        return res[-k]

# 滚动数组 + 最小堆
import heapq
class Solution:
    def kthLargestValue(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        pre = [0] * n
        hp = []

        for i in range(m):
            # p 表示matrix[i][0] ^... ^ matrix[i][j] 的结果
            p = 0
            for j in range(n):
                p ^= matrix[i][j]
                pre[j] ^= p
                # 堆内元素个数小于k时，直接添加
                if len(hp) < k:
                    heapq.heappush(hp, pre[j])
                # 堆内元素个数为k，并且当前元素大于堆顶时，pop堆顶，push当前元素
                elif pre[j] >= hp[0]:
                    heapq.heapreplace(hp, pre[j])
        
        return hp[0]