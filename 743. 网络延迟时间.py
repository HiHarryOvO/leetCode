# Dijkstra 算法
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        g = [[float("inf")] * n for _ in range(n)]
        for u, v, w in times:
            g[u - 1][v - 1] = w

        dist = [float("inf")] * n
        dist[k - 1] = 0
        used = [False] * n

        for i in range(n):
            x = -1
            # 找到下一个要访问的节点
            # 没有访问过节点中，dist 最小的
            for y, u in enumerate(used):
                if not u and (x == -1 or dist[y] < dist[x]):
                    x = y
            used[x] = True
            for v, w in enumerate(g[x]):
                dist[v] = min(dist[v], dist[x] + w)
        
        return max(dist) if max(dist) < float("inf") else -1


# Dijkstra + 堆优化
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        g = [[] for _ in range(n)]

        for u, v, w in times:
            g[u - 1].append((v - 1, w))

        dist = [float("inf")] * n
        dist[k - 1] = 0

        q = [(0, k - 1)]
        while q:
            time, node = heapq.heappop(q)
            if time > dist[node]:
                continue
            for y, w in g[node]:
                if (d := dist[node] + w) < dist[y]:
                    dist[y] = d
                    heapq.heappush(q, (d, y))
        
        ans = max(dist)
        return ans if ans < float("inf") else -1