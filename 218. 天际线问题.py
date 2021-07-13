import heapq

class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        ps, ans = [], []
        for b in buildings:
            ps.append([b[0], - b[2]])
            ps.append([b[1], b[2]])
        ps.sort()

        prev, hp = 0, []
        heapq.heappush(hp, 0)
        for p in ps:
            point, height = p[0], p[1]
            if height < 0:
                heapq.heappush(hp, height)
            else:
                hp.remove(- height)
                heapq.heapify(hp)

            cur = hp[0]
            if cur != prev:
                ans.append([point, - cur])
                prev = cur
        return ans

# 改用SortedList（底层红黑树？效率显著更高）
from sortedcontainers import SortedList

class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        ps, ans = [], []
        for b in buildings:
            ps.append([b[0], - b[2]])
            ps.append([b[1], b[2]])
        ps.sort()

        prev = 0
        ls = SortedList([0])
        for p in ps:
            point, height = p[0], p[1]
            if height < 0:
                ls.add(- height)
            else:
                ls.remove(height)

            cur = ls[-1]
            if cur != prev:
                ans.append([point, cur])
                prev = cur
        return ans