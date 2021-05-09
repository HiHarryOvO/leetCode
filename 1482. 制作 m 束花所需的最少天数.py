class Solution:
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        if m * k > len(bloomDay):
            return -1

        def canMake(days: int) -> bool:
            flowers = 0
            bouquets = 0

            for i, bloom in enumerate(bloomDay):
                if bloom <= days:
                    flowers += 1
                    if flowers == k:
                        bouquets += 1
                        flowers = 0
                else:
                    # 出现不连续的情况，重新计数
                    flowers = 0
            return bouquets >= m

        # 二分查找
        low, high = min(bloomDay), max(bloomDay)
        while low < high:
            mid = (low + high) // 2
            if canMake(mid):
                high = mid
            else:
                low = mid + 1
        return low