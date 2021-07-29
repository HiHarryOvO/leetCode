class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        n = len(target)
        mp = {}
        # 将 target 内的值与下标对应起来
        for i, num in enumerate(target):
            mp[num] = i

        # 寻找最长递增子序列
        d = []
        for num in arr:
            if num not in mp:
                continue
            pos = mp[num]
            idx = self.bin_search(d, pos)

            if idx == len(d):
                d.append(pos)
            else:
                d[idx] = pos

        return n - len(d)

    def bin_search(self, d, pos):
        if not d or pos > d[-1]:
            return len(d)

        l, r = 0, len(d) - 1
        while l <= r:
            mid = (l + r) // 2
            if d[mid] >= pos:
                r = mid - 1
            else:
                l = mid + 1
        return l