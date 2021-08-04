class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        def bin_search(row):
            l, r = 0, len(row) - 1
            while l <= r:
                mid = (l + r) // 2
                if row[mid] == 1:
                    l = mid + 1
                else:
                    r = mid - 1
            return l

        hp = []
        for i, row in enumerate(mat):
            hp.append((bin_search(row), i))

        # 先全部插入再建堆，O(m)
        heapq.heapify(hp)
        res = []
        for i in range(k):
            num, ind = heapq.heappop(hp)
            res.append(ind)
        return res
        