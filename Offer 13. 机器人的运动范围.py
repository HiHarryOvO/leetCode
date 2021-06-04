# DFS
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def dfs(i, j, si, sj):
            if i >= m or j >= n or (si + sj) > k or (i, j) in visited:
                return 0
            visited.add((i, j))
            return 1 + dfs(i + 1, j, si + 1 if (i + 1) % 10 else si - 8, sj) + dfs(i, j + 1, si, sj + 1 if (j + 1) % 10 else sj - 8)

        visited = set()
        return dfs(0, 0, 0, 0)

# BFS
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        queue, ans = [(0, 0, 0, 0)], 0
        visited = set()

        while queue:
            i, j, si, sj = queue.pop(0)
            if i >= m or j >= n or (si + sj) > k or (i, j) in visited:
                continue
            visited.add((i, j))
            ans += 1

            queue.append((i + 1, j, si + 1 if (i + 1) % 10 else si - 8, sj))
            queue.append((i, j + 1, si, sj + 1 if (j + 1) % 10 else sj - 8))

        return ans